package com.commerce.repository;

import com.commerce.entity.Cart;
import com.commerce.entity.CartProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
public interface CartProductRepository extends JpaRepository<CartProduct, CartProduct.Id > {
  Page<CartProduct> findByCart(Cart cart, Pageable pageable);
}
