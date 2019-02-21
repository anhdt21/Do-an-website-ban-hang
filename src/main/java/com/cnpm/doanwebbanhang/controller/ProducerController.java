package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.Producer;
import com.cnpm.doanwebbanhang.model.Product;
import com.cnpm.doanwebbanhang.service.ProducerService;
import com.cnpm.doanwebbanhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductService productService;

    @GetMapping("create-producer")
    public ModelAndView showCreateProducer() {
        ModelAndView modelAndView = new ModelAndView("/producer/create");
        modelAndView.addObject("producer", new Producer());
        return modelAndView;
    }

    @PostMapping("create-producer")
    public ModelAndView saveProducer(@ModelAttribute("producer") Producer producer) {
        producerService.save(producer);
        ModelAndView modelAndView = new ModelAndView("producer/create");
        modelAndView.addObject("producer", producer);
        modelAndView.addObject("message","New producer created successfully");
        return modelAndView;
    }

    @GetMapping("producers")
    public ModelAndView showProducer(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<Producer> producers;
        if (s != null) {
            producers = producerService.findByNameContaining(s , pageable);
        } else {
            producers = producerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("producer/list");
        modelAndView.addObject("producers", producers);
        return modelAndView;
    }

    @GetMapping("edit-producer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Producer> producer = producerService.findById(id);
        if (producer != null) {
            ModelAndView modelAndView = new ModelAndView("/producer/edit");
            modelAndView.addObject("producer", producer);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-producer")
    public ModelAndView updateProducer(@ModelAttribute("producer") Producer producer) {
        producerService.save(producer);
        ModelAndView modelAndView = new ModelAndView("/producer/edit");
        modelAndView.addObject("producer", producer);
        modelAndView.addObject("message","Producer update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-producer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Producer> producer = producerService.findById(id);
        if (producer != null) {
            ModelAndView modelAndView = new ModelAndView("/producer/delete");
            modelAndView.addObject("producer", producer);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-producer")
    public String deleteProducer(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("producer") Producer producer){
        Page<Product> products = productService.findAllByProducer_Id(producer.getId(), pageable);
        for (Product product : products) {
            productService.remove(product.getId());
        }
        producerService.remove(producer.getId());
        return "redirect:producers";
    }
}
