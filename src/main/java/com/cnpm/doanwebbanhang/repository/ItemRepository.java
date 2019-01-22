package com.cnpm.doanwebbanhang.repository;

import com.cnpm.doanwebbanhang.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
}
