package com.cnpm.doanwebbanhang.service.impl;

import com.cnpm.doanwebbanhang.model.Order;
import com.cnpm.doanwebbanhang.repository.OrderRepository;
import com.cnpm.doanwebbanhang.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Integer id) {
        orderRepository.deleteById(id);

    }

    @Override
    public Page<Order> findAllByDateOrderContaining(String s, Pageable pageable) {
        return orderRepository.findAllByDateOrderContaining(s, pageable);
    }

    @Override
    public Page<Order> findAllByStatus(String value, Pageable pageable) {
        return orderRepository.findAllByStatus(value, pageable);
    }
}
