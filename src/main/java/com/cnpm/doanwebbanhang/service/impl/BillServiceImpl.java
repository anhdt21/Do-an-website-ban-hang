package com.cnpm.doanwebbanhang.service.impl;

import com.cnpm.doanwebbanhang.model.Bill;
import com.cnpm.doanwebbanhang.repository.BillRepository;
import com.cnpm.doanwebbanhang.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;


    @Override
    public Page<Bill> findByDateOrderContaining(String s, Pageable pageable) {
        return billRepository.findByDateOrderContaining(s, pageable);
    }

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }

    @Override
    public void remove(Long id) {
        billRepository.deleteById(id);

    }
}
