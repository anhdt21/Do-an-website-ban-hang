package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.*;
import com.cnpm.doanwebbanhang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class BeginController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView show(@PageableDefault(size = 10000) Pageable pageable) {
        int count1 = 0, count2 = 0, count3 = 0, count4 = 0;
        Page<Product> products = productService.findAll(pageable);
        for (Product product : products) {
            count1++;
        }
        Page<Producer> producers = producerService.findAll(pageable);
        for (Producer producer : producers) {
            count2++;
        }
        Page<User> users = userService.findAll(pageable);
        for (User user : users) {
            count3++;
        }
        Page<Order> orders = orderService.findAll(pageable);
        for (Order order : orders) {
            count4++;
        }
        ModelAndView modelAndView = new ModelAndView("admin/index");
        modelAndView.addObject("count1", count1);
        modelAndView.addObject("count2", count2);
        modelAndView.addObject("count3", count3);
        modelAndView.addObject("count4", count4);
        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView index(@PageableDefault(size = 15) Pageable pageable, @ModelAttribute("s") String s, HttpServletRequest request) {
        Page<Product> products;
        if (s == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllByNameContaining(s, pageable);
        }

        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);

        ModelAndView modelAndView = new ModelAndView("UI/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/checkout")
    public ModelAndView showShop(@PageableDefault(size = 20) Pageable pageable, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("order") == null) {
            Page<Producer> producers = producerService.findAll(pageable);
            Page<ProductType> productTypes = productTypeService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("UI/index", "message", "Giỏ hàng trống !");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("size", getSize(request));
            return modelAndView;
        } else {
            Page<Product> products = productService.findAll(pageable);
            Page<Producer> producers = producerService.findAll(pageable);
            Page<ProductType> productTypes = productTypeService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("UI/checkout");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("size", getSize(request));
            modelAndView.addObject("products", products);
            return modelAndView;
        }
    }

    @GetMapping("/save-customer")
    public ModelAndView newRegister(@PageableDefault(size = 20) Pageable pageable ,
                                    @ModelAttribute("customer") Customer customer,
                                    @ModelAttribute("order") Order order, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("order") == null) {
            Page<Producer> producers = producerService.findAll(pageable);
            Page<ProductType> productTypes = productTypeService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("UI/index", "message", "Giỏ hàng trống !");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("size", getSize(request));
            return modelAndView;
        } else {
            Page<Producer> producers = producerService.findAll(pageable);
            Page<ProductType> productTypes = productTypeService.findAll(pageable);
            User user = userService.findByUserName(getUserName());
            ModelAndView modelAndView = new ModelAndView("UI/buy_products");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("user", user);
            modelAndView.addObject("size", getSize(request));
            return modelAndView;
        }
    }

    @GetMapping("/view-product/{id}")
    public ModelAndView showViewProduct(@PageableDefault Pageable pageable ,@PathVariable Integer id, HttpServletRequest request) {
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        Optional<Product> product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("UI/view_product");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("product", product.get());
        modelAndView.addObject("size", getSize(request));
        return modelAndView;

    }

    @GetMapping("/choice-product-type/{id}")
    public ModelAndView showFormProductType(@PageableDefault Pageable pageable, @PathVariable Long id, HttpServletRequest request) {
        Page<Product> products = productService.findAllByProductType_Id(id, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        if (id == 1) {
            ModelAndView modelAndView = new ModelAndView("UI/product_type1");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        } else if (id == 2) {
            ModelAndView modelAndView = new ModelAndView("UI/product_type2");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        } else if (id == 3) {
            ModelAndView modelAndView = new ModelAndView("UI/product_type3");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("UI/product_type");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        }
    }

    @GetMapping("/choice-producer/{id}")
    public ModelAndView showFormProducer(@PageableDefault Pageable pageable, @PathVariable Long id, HttpServletRequest request) {
        Page<Product> products = productService.findAllByProducer_Id(id, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        if (id == 1) {
            ModelAndView modelAndView = new ModelAndView("UI/producer1");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        } else if (id == 2) {
            ModelAndView modelAndView = new ModelAndView("UI/producer2");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        } else if (id == 3) {
            ModelAndView modelAndView = new ModelAndView("UI/producer3");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        } else if (id == 4) {
            ModelAndView modelAndView = new ModelAndView("UI/producer4");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("UI/producer5");
            modelAndView.addObject("producers", producers);
            modelAndView.addObject("productTypes", productTypes);
            modelAndView.addObject("products", products);
            modelAndView.addObject("size", getSize(request));
            if (products.isEmpty()) {
                modelAndView.addObject("message", "Không có sản phẩm nào");
            }
            return modelAndView;
        }
    }

    @GetMapping("/cost5")
    public ModelAndView showProductcostless5(@PageableDefault Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceLessThan( 5000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/cost5");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/cost10")
    public ModelAndView showProductcostless10(@PageableDefault Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween( 5000000, 10000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/cost10");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/cost15")
    public ModelAndView showProductcostless15(@PageableDefault Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween( 10000000, 15000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/cost15");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/cost20")
    public ModelAndView showProductcostless500(@PageableDefault Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByUnitPriceBetween( 15000000, 500000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/cost_max");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        modelAndView.addObject("size", getSize(request));
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/hot-product")
    public ModelAndView showHotProduct(@PageableDefault Pageable pageable, HttpServletRequest request) {
        Page<Product> products = productService.findAllByHotContaining( "1", pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/hot_product");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        modelAndView.addObject("size", getSize(request));
        return modelAndView;
    }

    public long getSize(HttpServletRequest request) {
        long size = 0;
        HttpSession session = request.getSession();
        if (session.getAttribute("order") == null) {
            return size;
        } else {
            Order order = (Order) session.getAttribute("order");
            List<Item> items = order.getItems();
            for (Item item : items) {
                size+= item.getQuantity();
            }
            return size;
        }
    }

    public String getUserName() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}
