package com.commerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by jinwoo on 2017. 6. 22..
 */
@Entity
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy="cart")
    private Set<CartProduct> cartProducts;

    @OneToOne
    private User user;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;


}
