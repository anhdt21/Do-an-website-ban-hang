package com.cnpm.doanwebbanhang.repository;



import com.cnpm.doanwebbanhang.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findAllByNameContaining(String s, Pageable pageable);

    User findUserByEmail(String name);

    User findUserByUserName(String name);
}