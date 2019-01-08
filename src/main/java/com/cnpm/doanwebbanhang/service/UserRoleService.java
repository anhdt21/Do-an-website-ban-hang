package com.cnpm.doanwebbanhang.service;


import com.cnpm.doanwebbanhang.model.UserRole;

import java.util.Optional;

public interface UserRoleService {

    Optional<UserRole> findById(Integer id);
}
