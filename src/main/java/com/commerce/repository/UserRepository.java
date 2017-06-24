package com.commerce.repository;

import com.commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
