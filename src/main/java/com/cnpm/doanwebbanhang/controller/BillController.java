package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.Bill;
import com.cnpm.doanwebbanhang.service.BillService;
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
    private BillService billService;

    @GetMapping("create-bill")
    public ModelAndView createBill() {
        ModelAndView modelAndView = new ModelAndView("bill/createw");
        modelAndView.addObject("bill", new Bill());
        return modelAndView;
    }

    @PostMapping("create-bill")
    public ModelAndView saveBill(@ModelAttribute("bill") Bill bill) {
        billService.save(bill);
        ModelAndView modelAndView = new ModelAndView("bill/createw");
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("message", "Thành Công");
        return modelAndView;

    }

    @GetMapping("bills")
    public ModelAndView showBill(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<Bill> bills;
        if (s != null) {
            bills = billService.findByDateOrderContaining(s, pageable);
        } else {
            bills = billService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("bill/list");
        modelAndView.addObject("bills", bills);
        return modelAndView;
    }

    @GetMapping("edit-bill/{id}")
    public ModelAndView editBill(@PathVariable Long id) {
        Optional<Bill> bill = billService.findById(id);
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
    public ModelAndView showEditBill(@ModelAttribute("bill") Bill bill) {
        billService.save(bill);
        ModelAndView modelAndView = new ModelAndView("bill/edit");
        modelAndView.addObject("bill", bill);
        modelAndView.addObject("message", "Thành Công");
        return modelAndView;
    }

    @GetMapping("delete-bill/{id}")
    public ModelAndView showDeleteBill(@PathVariable Long id) {
        Optional<Bill> bill = billService.findById(id);
        if (bill != null) {
            ModelAndView modelAndView = new ModelAndView("bill/delete");
            modelAndView.addObject("bill", bill);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("delete-bill")
    public String deleteBill(@ModelAttribute("bill") Bill bill) {
        billService.remove(bill.getId());
        return "redirect:bills";

    }



}
