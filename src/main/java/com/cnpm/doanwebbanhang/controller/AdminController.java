package com.cnpm.doanwebbanhang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping("/")
    public ModelAndView show() {
        return new ModelAndView("admin/index");
    }
}
