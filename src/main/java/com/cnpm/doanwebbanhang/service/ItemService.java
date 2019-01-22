package com.cnpm.doanwebbanhang.service;

import com.cnpm.doanwebbanhang.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ItemService {
    Page<Item> findAll(Pageable pageable);

    Optional<Item> findById(Integer id);

    Item save(Item item);

    void remove(Integer id);
}
