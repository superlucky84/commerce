package com.commerce.repository;

import com.commerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
    User findByUsername(String username);
}
