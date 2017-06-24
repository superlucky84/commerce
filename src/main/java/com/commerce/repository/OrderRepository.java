package com.commerce.repository;

import com.commerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
