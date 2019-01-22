package com.cnpm.doanwebbanhang.repository;

import com.cnpm.doanwebbanhang.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
}
