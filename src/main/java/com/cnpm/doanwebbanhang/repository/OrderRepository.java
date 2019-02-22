package com.cnpm.doanwebbanhang.repository;

import com.cnpm.doanwebbanhang.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
    Page<Order> findAllByStatus(String value, Pageable pageable);

    Page<Order> findAllByDateOrderContaining(String s, Pageable pageable);
}
