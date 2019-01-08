package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.Producer;
import com.cnpm.doanwebbanhang.model.Product;
import com.cnpm.doanwebbanhang.model.ProductType;
import com.cnpm.doanwebbanhang.service.ProducerService;
import com.cnpm.doanwebbanhang.service.ProductService;
import com.cnpm.doanwebbanhang.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class BeginController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public ModelAndView index(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<Product> products;
        if (s == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findAllByNameContaining(s, pageable);
        }
        long id1 = 1, id2 = 2, id3 = 3, id4 = 4;
        Page<Product> productType1 = productService.findAllByProductType_Id(id1, pageable);
        Page<Product> productType2 = productService.findAllByProductType_Id(id2, pageable);
        Page<Product> productType3 = productService.findAllByProductType_Id(id3, pageable);
        Page<Product> productType4 = productService.findAllByProductType_Id(id4, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);

        ModelAndView modelAndView = new ModelAndView("UI/index");
        modelAndView.addObject("products", products);
        modelAndView.addObject("productType1", productType1);
        modelAndView.addObject("productType2", productType2);
        modelAndView.addObject("productType3", productType3);
        modelAndView.addObject("productType4", productType4);
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không tìm thấy sản phẩm");
        }
        return modelAndView;
    }

    @GetMapping("/view-product/{id}")
    public ModelAndView showViewProduct(@PageableDefault(size = 10) Pageable pageable ,@PathVariable Integer id) {
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        Optional<Product> product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("UI/view_product");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("product", product.get());
        return modelAndView;

    }

    @GetMapping("/choice-product-type/{id}")
    public ModelAndView showFormProductType(@PageableDefault(size = 15) Pageable pageable, @PathVariable Long id) {
        Page<Product> products = productService.findAllByProductType_Id(id, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/product_type");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/choice-producer/{id}")
    public ModelAndView showFormProducer(@PageableDefault(size = 15) Pageable pageable, @PathVariable Long id) {
        Page<Product> products = productService.findAllByProducer_Id(id, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/product_type");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/cost5")
    public ModelAndView showProductcostless5(@PageableDefault(size = 20) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceLessThan( 5000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/product_type");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/cost10")
    public ModelAndView showProductcostless10(@PageableDefault(size = 20) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceBetween( 5000000, 10000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/product_type");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/cost15")
    public ModelAndView showProductcostless15(@PageableDefault(size = 20) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceBetween( 10000000, 15000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/product_type");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/cost20")
    public ModelAndView showProductcostless50(@PageableDefault(size = 20) Pageable pageable) {
        Page<Product> products = productService.findAllByUnitPriceBetween( 15000000, 50000000, pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/product_type");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        if (products.isEmpty()) {
            modelAndView.addObject("message", "Không có sản phẩm nào");
        }
        return modelAndView;
    }

    @GetMapping("/hot-product")
    public ModelAndView showHotProduct(@PageableDefault(size = 20) Pageable pageable) {
        Page<Product> products = productService.findAllByHotContaining( "1", pageable);
        Page<Producer> producers = producerService.findAll(pageable);
        Page<ProductType> productTypes = productTypeService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("UI/hot_product");
        modelAndView.addObject("producers", producers);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
