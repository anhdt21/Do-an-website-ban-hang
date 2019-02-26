package com.cnpm.doanwebbanhang.service;

import com.cnpm.doanwebbanhang.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> findAllByNameContaining(String s, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    void save(User user);

    void remove(Long id);

    User findByName(String username);

    Page<User> findAllByPhoneContaining(String s, Pageable pageable);

    User findByUserName(String username);

}
