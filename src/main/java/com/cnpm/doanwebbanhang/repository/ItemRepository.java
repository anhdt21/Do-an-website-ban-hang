package com.cnpm.doanwebbanhang.repository;

import com.cnpm.doanwebbanhang.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
    Page<Item> findAllByOrder_Id(Integer id, Pageable pageable);
}
