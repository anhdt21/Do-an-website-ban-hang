package com.cnpm.doanwebbanhang.service.impl;

import com.cnpm.doanwebbanhang.model.User;
import com.cnpm.doanwebbanhang.repository.UserRepository;
import com.cnpm.doanwebbanhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAllByNameContaining(String s, Pageable pageable) {
        return userRepository.findAllByNameContaining(s, pageable);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);

    }

    @Override
    public User findByName(String username) {
        return userRepository.findUserByUserName(username);
    }

    @Override
    public Page<User> findAllByPhoneContaining(String s, Pageable pageable) {
        return userRepository.findAllByPhoneContaining(s, pageable);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }


}
