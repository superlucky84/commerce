package com.commerce.repository;

import com.commerce.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
public interface CartProductRepository extends JpaRepository<CartProduct, CartProduct.Id > {
}
