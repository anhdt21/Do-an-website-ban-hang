package com.cnpm.doanwebbanhang;

import com.cnpm.doanwebbanhang.formatter.UserRoleFormatter;
import com.cnpm.doanwebbanhang.service.*;
import com.cnpm.doanwebbanhang.service.impl.*;
import com.cnpm.doanwebbanhang.storage.StorageProperties;
import com.cnpm.doanwebbanhang.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

    @Configuration
    static class MyConfig extends WebMvcConfigurerAdapter {
        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addFormatter(new UserRoleFormatter());
        }
    }
    @Bean
    public UserRoleService userRoleService() {
        return new UserRoleServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
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
    UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
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
    public ProducerService producerService() {
        return new ProducerServiceImpl();
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceImpl();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(DoAnWebBanHangApplication.class, args);
    }

}

