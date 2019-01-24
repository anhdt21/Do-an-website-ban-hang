package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.Customer;
import com.cnpm.doanwebbanhang.model.Item;
import com.cnpm.doanwebbanhang.model.Order;
import com.cnpm.doanwebbanhang.model.Product;
import com.cnpm.doanwebbanhang.service.CustomerService;
import com.cnpm.doanwebbanhang.service.ItemService;
import com.cnpm.doanwebbanhang.service.OrderService;
import com.cnpm.doanwebbanhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AddToCardController {
    private CustomerService customerService;
    private OrderService orderService;
    private ItemService itemService;
    private ProductService productService;


    @Autowired
    public void setUpController(ProductService productService,
                                OrderService orderService,
                                ItemService itemService,
                                CustomerService customerService) {
        this.productService = productService;
        this.orderService = orderService;
        this.customerService = customerService;
        this.itemService = itemService;
    }

    @GetMapping("/addtocard/{id}")
    public ModelAndView addToCard(@PathVariable("id") Integer id, HttpServletRequest request) {
        long quantity = 1;
        Optional<Product> product = productService.findById(id);
        HttpSession session = request.getSession();
        if (session.getAttribute("order") == null) {
            Order order = new Order();
            List<Item> items = new ArrayList<Item>();
            Item item = new Item();
            item.setId(id);
            item.setProduct(product.get());
            item.setQuantity(quantity);
            item.setPrice(product.get().getUnitPrice());
            items.add(item);
            order.setItems(items);
            session.setAttribute("order", order);

        } else {
            Order order = (Order) session.getAttribute("order");
            List<Item> items = order.getItems();
            boolean check = false;
            for (Item item : items) {
                if (item.getProduct().getId() == product.get().getId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    check = true;
                }
            }
            if (check == false) {
                Item item = new Item();
                item.setId(id);
                item.setQuantity(quantity);
                item.setPrice(product.get().getUnitPrice());
                item.setProduct(product.get());
                items.add(item);
            }
            session.setAttribute("order", order);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        modelAndView.addObject("session", session);
        return modelAndView;
    }

    @GetMapping("/remove-item/{id}")
    public ModelAndView removeItem(HttpServletRequest request, @PathVariable("id") Integer id) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Item> items = order.getItems();
        if (items.size() != 0) {
            try {
                for (Item item : items) {
                    if (item.getId() == id) {
                        items.remove(item);
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Ơ. Lỗi");
            }
        }
        order.setItems(items);
        session.setAttribute("order", order);
        ModelAndView modelAndView = new ModelAndView("redirect:/checkout");
//        modelAndView.addObject("session", session);
        return modelAndView;
    }

    @PostMapping("/save-order")
    public ModelAndView saveOrder(HttpServletRequest request, @ModelAttribute Customer customer) {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");
        List<Item> items = (List<Item>) ((Order) session.getAttribute("order")).getItems();
        order.setItems(items);
        customerService.save(customer);
        order.setCustomer(customer);
        Date date = new Date();
        String currentTime = new SimpleDateFormat("dd/MM/yyyy").format(date);
        order.setDateOrder(currentTime);
        orderService.save(order);

        for (Item item : items) {
            item.setOrder(order);
            itemService.save(item);
        }
        session.invalidate();

        ModelAndView modelAndView = new ModelAndView("/UI/index");
        modelAndView.addObject("message", "Mua hàng thành công. Cửa hàng sẽ liên hệ cho bạn trong thời gian sớm nhất.");
        return modelAndView;
    }
}
