package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.Bill;
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
        modelAndView.addObject("bills", orders);
        return modelAndView;
    }

    @GetMapping("edit-bill/{id}")
    public ModelAndView editBill(@PathVariable Integer id) {
        Optional<Order> bill = orderService.findById(id);
        if (bill != null) {
            ModelAndView modelAndView = new ModelAndView("bill/edit");
            modelAndView.addObject("bill", bill);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("edit-bill")
    public ModelAndView showEditBill(@ModelAttribute("bill") Order order) {
        orderService.save(order);
        ModelAndView modelAndView = new ModelAndView("bill/edit");
        modelAndView.addObject("bill", order);
        modelAndView.addObject("message", "Thành Công");
        return modelAndView;
    }


}
