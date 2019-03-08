package com.cnpm.doanwebbanhang.repository;

import com.cnpm.doanwebbanhang.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String s, Pageable pageable);

    Page<Product> findAllByProducer_Id(Long id, Pageable pageable);

    Page<Product> findAllByProductType_Id(Long id, Pageable pageable);

    Page<Product> findAllByUnitPriceLessThan(Integer number, Pageable pageable);

    Page<Product> findAllByUnitPriceBetween(Integer pri1,Integer pri2,Pageable pageable);

    Page<Product> findAllByHotContaining(String value, Pageable pageable);

}
