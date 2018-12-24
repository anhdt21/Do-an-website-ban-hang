package com.cnpm.doanwebbanhang.repository;


import com.cnpm.doanwebbanhang.model.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BillDetailRepository extends PagingAndSortingRepository<BillDetail, Long> {
    Page<BillDetail> findByUnitPriceContaining(String s, Pageable pageable);
}
