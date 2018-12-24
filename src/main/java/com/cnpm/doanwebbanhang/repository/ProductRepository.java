package com.cnpm.doanwebbanhang.repository;

import com.cnpm.doanwebbanhang.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String s, Pageable pageable);
}
