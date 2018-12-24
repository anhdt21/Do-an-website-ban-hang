package com.cnpm.doanwebbanhang.service;



import com.cnpm.doanwebbanhang.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findById(Long id);

    void save(Customer customer);

    void remove(Long id);

    Page<Customer> findAllByNameContaining(String s, Pageable pageable);
}
