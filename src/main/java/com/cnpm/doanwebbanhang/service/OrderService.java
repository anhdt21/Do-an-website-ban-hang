package com.cnpm.doanwebbanhang.service;

import com.cnpm.doanwebbanhang.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {
    Page<Order> findAll(Pageable pageable);

    Optional<Order> findById(Integer id);

    Order save(Order order);

    void remove(Integer id);

    Page<Order> findAllByDateOrderContaining(String s, Pageable pageable);

    Page<Order> findAllByStatus(String value, Pageable pageable);

}
