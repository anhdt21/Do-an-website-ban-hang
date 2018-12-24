package com.cnpm.doanwebbanhang.repository;


import com.cnpm.doanwebbanhang.model.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {

    Page<Bill> findByDateOrderContaining(String s, Pageable pageable);
}
