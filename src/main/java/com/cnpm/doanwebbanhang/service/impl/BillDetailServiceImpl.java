package com.cnpm.doanwebbanhang.service.impl;


import com.cnpm.doanwebbanhang.model.BillDetail;
import com.cnpm.doanwebbanhang.repository.BillDetailRepository;
import com.cnpm.doanwebbanhang.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class BillDetailServiceImpl implements BillDetailService {
    @Autowired
    private com.cnpm.doanwebbanhang.repository.BillDetailRepository BillDetailRepository;

    @Override
    public Page<BillDetail> findByUnitPriceContaining(String s, Pageable pageable) {
        return BillDetailRepository.findByUnitPriceContaining(s, pageable);
    }

    @Override
    public Page<BillDetail> findAll(Pageable pageable) {
        return BillDetailRepository.findAll(pageable);
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return BillDetailRepository.findById(id);
    }

    @Override
    public void save(BillDetail BillDetail) {
        BillDetailRepository.save(BillDetail);

    }

    @Override
    public void remove(Long id) {
        BillDetailRepository.deleteById(id);

    }
}
