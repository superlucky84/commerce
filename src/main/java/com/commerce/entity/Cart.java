package com.commerce.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by jinwoo on 2017. 6. 22..
 */
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
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
    private Long createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private Long updatedBy;

    @LastModifiedDate
    private Date updatedDate;


}
