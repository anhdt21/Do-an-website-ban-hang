package com.cnpm.doanwebbanhang.service.impl;

import com.cnpm.doanwebbanhang.model.UserRole;
import com.cnpm.doanwebbanhang.repository.UserRoleRepository;
import com.cnpm.doanwebbanhang.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Optional<UserRole> findById(Integer id) {
        return userRoleRepository.findById(id);
    }
}
