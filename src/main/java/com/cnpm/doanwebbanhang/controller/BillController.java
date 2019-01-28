package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.Order;
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


import java.util.Optional;

@Controller
public class BillController {

    @Autowired
    private OrderService orderService;

    @GetMapping("bills")
    public ModelAndView showBill(@PageableDefault(size = 10) Pageable pageable) {
        Page<Order> orders = orderService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("bill/list");
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @GetMapping("edit-bill/{id}")
    public ModelAndView editBill(@PathVariable Integer id) {
        Optional<Order> order = orderService.findById(id);
        if (order.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("bill/edit");
            modelAndView.addObject("order", order);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @GetMapping("/delete-bill/{id}")
    public ModelAndView showDeleteBillForm(@PageableDefault(size = 10) Pageable pageable, @PathVariable Integer id) {
            orderService.remove(id);
            Page<Order> orders = orderService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("/bill/list");
            modelAndView.addObject("orders", orders);
            return modelAndView;

    }

    @GetMapping("processing/{id}")
    public ModelAndView showProcessing(@PageableDefault(size = 10) Pageable pageable, @PathVariable Integer id) {
        Page<Order> orders = orderService.findAll(pageable);
        Optional<Order> bill = orderService.findById(id);
        bill.get().setStatus("Chưa xử lý");
        if (bill != null) {
            ModelAndView modelAndView = new ModelAndView("bill/list");
            modelAndView.addObject("bills", orders);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-bill")
    public ModelAndView editBill(@ModelAttribute("order") Order order) {
        orderService.save(order);
        ModelAndView modelAndView = new ModelAndView("bill/edit");
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @PostMapping("processed/")
    public ModelAndView processed(@ModelAttribute("bill") Order order) {
        orderService.save(order);
        ModelAndView modelAndView = new ModelAndView("bill/edit");
        modelAndView.addObject("bill", order);
        return modelAndView;
    }

    @GetMapping("processed/{id}")
    public ModelAndView showProcessed(@PageableDefault(size = 10) Pageable pageable, @PathVariable Integer id) {
        Page<Order> orders = orderService.findAll(pageable);
        Optional<Order> bill = orderService.findById(id);
        bill.get().setStatus("Chưa xử lý");
        if (bill != null) {
            ModelAndView modelAndView = new ModelAndView("bill/list");
            modelAndView.addObject("bills", orders);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

}
