package com.cnpm.doanwebbanhang.service.impl;


import com.cnpm.doanwebbanhang.model.Customer;
import com.cnpm.doanwebbanhang.repository.CustomerRepository;
import com.cnpm.doanwebbanhang.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
;

import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void remove(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> findAllByNameContaining(String s, Pageable pageable) {
        return customerRepository.findAllByNameContaining(s, pageable);
    }
}