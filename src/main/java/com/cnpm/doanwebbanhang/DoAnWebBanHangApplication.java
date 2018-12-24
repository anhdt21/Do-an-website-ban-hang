package com.cnpm.doanwebbanhang;

import com.cnpm.doanwebbanhang.service.*;
import com.cnpm.doanwebbanhang.service.impl.*;
import com.cnpm.doanwebbanhang.storage.StorageProperties;
import com.cnpm.doanwebbanhang.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DoAnWebBanHangApplication {

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
        };
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean
    UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public BillService billService() {
        return new BillServiceImpl();
    }

    @Bean
    public ProductTypeService productTypeService() {
        return new ProductTypeServiceImpl();
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl();
    }

    @Bean
    public BillDetailService billDetailService() {
        return new BillDetailServiceImpl();
    }

    @Bean
    public ProducerService producerService() {
        return new ProducerServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(DoAnWebBanHangApplication.class, args);
    }

}

