package com.cnpm.doanwebbanhang.service.impl;

import com.cnpm.doanwebbanhang.model.Item;
import com.cnpm.doanwebbanhang.repository.ItemRepository;
import com.cnpm.doanwebbanhang.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Optional<Item> findById(Integer id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void remove(Integer id) {
        itemRepository.deleteById(id);
    }
}
