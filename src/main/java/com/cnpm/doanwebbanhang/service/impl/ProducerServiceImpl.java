package com.cnpm.doanwebbanhang.service.impl;


import com.cnpm.doanwebbanhang.model.Producer;
import com.cnpm.doanwebbanhang.repository.ProducerRepository;
import com.cnpm.doanwebbanhang.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public class ProducerServiceImpl implements ProducerService {
@Autowired
    private ProducerRepository producerRepository;

    @Override
    public Page<Producer> findByNameContaining(String name, Pageable pageable) {
        return producerRepository.findByNameContaining(name, pageable);
    }

    @Override
    public Page<Producer> findAll(Pageable pageable) {
        return producerRepository.findAll(pageable);
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public void save(Producer producer) {
        producerRepository.save(producer);
    }

    @Override
    public void remove(Long id) {
        producerRepository.deleteById(id);
    }
}
