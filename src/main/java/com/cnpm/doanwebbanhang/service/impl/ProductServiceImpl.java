package com.cnpm.doanwebbanhang.service.impl;

import com.cnpm.doanwebbanhang.model.Product;
import com.cnpm.doanwebbanhang.repository.ProductRepository;
import com.cnpm.doanwebbanhang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAllByNameContaining(String s, Pageable pageable) {
        return productRepository.findAllByNameContaining(s, pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Integer id) {
    productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByProducer_Id(Long id, Pageable pageable) {
        return productRepository.findAllByProducer_Id(id, pageable);
    }

    @Override
    public Page<Product> findAllByProductType_Id(Long id, Pageable pageable) {
        return productRepository.findAllByProductType_Id(id, pageable);

    }
}
