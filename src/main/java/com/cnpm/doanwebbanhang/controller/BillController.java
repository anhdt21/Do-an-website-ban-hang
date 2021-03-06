package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.Item;
import com.cnpm.doanwebbanhang.model.Order;
import com.cnpm.doanwebbanhang.service.CustomerService;
import com.cnpm.doanwebbanhang.service.ItemService;
import com.cnpm.doanwebbanhang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Optional;

@Controller
public class BillController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("bills")
    public ModelAndView showBill(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {

        if (s == null) {
            Page<Order> orders = orderService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("bill/list");
            modelAndView.addObject("orders", orders);
            return modelAndView;
        } else {
            Page<Order> orders = orderService.findAllByDateOrderContaining(s, pageable);
            ModelAndView modelAndView = new ModelAndView("bill/list");
            modelAndView.addObject("orders", orders);
            return modelAndView;
        }

    }

    @GetMapping("edit-bill/{id}")
    public ModelAndView editBill(@PathVariable Integer id,@PageableDefault(size = 30) Pageable pageable) {
        Optional<Order> order = orderService.findById(id);
        Page<Item> items = itemService.findAllByOrder_Id(id, pageable);
        if (order.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("bill/edit");
            modelAndView.addObject("order", order);
            modelAndView.addObject("items", items);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @GetMapping("print-bill/{id}")
    public ModelAndView printBill(@PathVariable Integer id,@PageableDefault(size = 30) Pageable pageable) {
        Optional<Order> order = orderService.findById(id);
        Page<Item> items = itemService.findAllByOrder_Id(id, pageable);
        if (order.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("bill/print_bill");
            modelAndView.addObject("order", order);
            modelAndView.addObject("items", items);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-bill")
    public ModelAndView editBill(@PageableDefault Pageable pageable, @ModelAttribute("order") Order order) {
        orderService.save(order);
        Page<Order> orders = orderService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("bill/list");
        modelAndView.addObject("order", order);
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("/delete-bill/{id}")
    public ModelAndView showDeleteBillForm(@PageableDefault Pageable pageable, @PathVariable Integer id) {
        Optional<Order> order = orderService.findById(id);
        List<Item> items = order.get().getItems();
        for (Item item : items) {
            itemService.remove(item.getId());
        }
        customerService.remove(order.get().getCustomer().getId());
        orderService.remove(id);
        Page<Order> orders = orderService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/bill/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;

    }

    @GetMapping("/process1")
    public ModelAndView showBillDoneForm(@PageableDefault Pageable pageable) {
        String value = "Đã xử lý";
        Page<Order> orders = orderService.findAllByStatus(value, pageable);
        ModelAndView modelAndView = new ModelAndView("/bill/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("/process2")
    public ModelAndView showBillNotProcessForm(@PageableDefault Pageable pageable) {
        String value = "Chưa xử lý";
        Page<Order> orders = orderService.findAllByStatus(value, pageable);
        ModelAndView modelAndView = new ModelAndView("/bill/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("/process3")
    public ModelAndView showBillCancelForm(@PageableDefault Pageable pageable) {
        String value = "Đã bị hủy";
        Page<Order> orders = orderService.findAllByStatus(value, pageable);
        ModelAndView modelAndView = new ModelAndView("/bill/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

}
