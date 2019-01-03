package com.cnpm.doanwebbanhang.service;

import com.cnpm.doanwebbanhang.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAllByNameContaining(String s, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Integer id);

    void save(Product product);

    void remove(Integer id);

    Page<Product> findAllByProducer_Id(Long id,Pageable pageable);

    Page<Product> findAllByProductType_Id(Long id, Pageable pageable);

}
