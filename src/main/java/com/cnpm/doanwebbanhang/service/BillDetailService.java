package com.cnpm.doanwebbanhang.service;

import com.cnpm.doanwebbanhang.model.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BillDetailService {
    Page<BillDetail> findByUnitPriceContaining(String s, Pageable pageable);

    Page<BillDetail> findAll(Pageable pageable);

    Optional<BillDetail> findById(Long id);

    void save(BillDetail BillDetail);

    void remove(Long id);
}
