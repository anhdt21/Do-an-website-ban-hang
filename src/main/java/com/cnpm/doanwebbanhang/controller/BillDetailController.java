package com.cnpm.doanwebbanhang.controller;


import com.cnpm.doanwebbanhang.model.BillDetail;
import com.cnpm.doanwebbanhang.service.BillDetailService;
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
public class BillDetailController {
    @Autowired
    private BillDetailService billDetailService;

    @GetMapping("create-billDetail")
    public ModelAndView showCreateDetail() {
        ModelAndView modelAndView = new ModelAndView("billDetail/create");
        modelAndView.addObject("billDetail", new BillDetail());
        return modelAndView;
    }

    @PostMapping("create-billDetail")
    public ModelAndView createDetail(@ModelAttribute("detail") BillDetail billDetail) {
        billDetailService.save(billDetail);
        ModelAndView modelAndView = new ModelAndView("billDetail/create");
        modelAndView.addObject("billDetail", billDetail);
        modelAndView.addObject("message", "Thành Công");
        return modelAndView;
    }

    @GetMapping("billDetails")
    public ModelAndView showListDetail(@PageableDefault(size = 10)Pageable pageable, @ModelAttribute("s") String s) {
        Page<BillDetail> details;
        if (s != null) {
            details = billDetailService.findByUnitPriceContaining(s, pageable);

        } else {
            details = billDetailService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("billDetail/list");
        modelAndView.addObject("billDetails", details);
        return modelAndView;
    }

    @GetMapping("edit-billDetail/{id}")
    public ModelAndView showEditDetail(@PathVariable Long id) {
        Optional<BillDetail> billDetail = billDetailService.findById(id);
        if (billDetail != null) {
            ModelAndView modelAndView = new ModelAndView("billDetail/edit");
            modelAndView.addObject("billDetail", billDetail);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("edit-billDetail")
    public ModelAndView editDetail(@ModelAttribute("BillDetail") BillDetail billDetail) {
        billDetailService.save(billDetail);
        ModelAndView modelAndView = new ModelAndView("billDetail/edit");
        modelAndView.addObject("BillDetail", billDetail);
        modelAndView.addObject("message", "Thành công ");
        return modelAndView;
    }

    @GetMapping("delete-billDetail/{id}")
    public ModelAndView showDeleteDetail(@PathVariable Long id) {
        Optional<BillDetail> billDetail = billDetailService.findById(id);
        if (billDetail != null) {
            ModelAndView modelAndView = new ModelAndView("BillDetail/delete");
            modelAndView.addObject("BillDetail", billDetail);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("delete-billDetail")
    public String Delete(@ModelAttribute("billDetail") BillDetail billDetail) {
        billDetailService.remove(billDetail.getId());
        return "redirect:BillDetails";
    }
}
