package com.commerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

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

    @OneToMany(mappedBy="cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartProduct> cartProducts;

    @OneToOne
    @CreatedBy
    private User user;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date createdDate;

    @CreatedBy
    private String updatedBy;

    @LastModifiedBy
    private Date updatedDate;


}
