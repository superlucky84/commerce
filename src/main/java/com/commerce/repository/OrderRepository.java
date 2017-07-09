package com.commerce.repository;

import com.commerce.entity.Order;
import com.commerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
  Page<Order> findByUser(User user,Pageable pageable);
}
