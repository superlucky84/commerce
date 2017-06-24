package com.commerce.repository;

import com.commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
