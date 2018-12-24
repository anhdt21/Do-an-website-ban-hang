package com.cnpm.doanwebbanhang.service;

import com.cnpm.doanwebbanhang.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface BillService {

    Page<Bill> findByDateOrderContaining(String s, Pageable pageable);

    Page<Bill> findAll(Pageable pageable);

    Optional<Bill> findById(Long id);

    void save(Bill bill);

    void remove(Long id);
}
