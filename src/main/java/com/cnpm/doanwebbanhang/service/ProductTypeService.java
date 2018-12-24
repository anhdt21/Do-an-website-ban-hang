package com.cnpm.doanwebbanhang.service;


import com.cnpm.doanwebbanhang.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductTypeService {
    Page<ProductType> findByNameContaining(String name, Pageable pageable);

    Page<ProductType> findAll(Pageable pageable);

    Optional<ProductType> findById(Long id);

    void save(ProductType productType);

    void remove(Long id);
}
