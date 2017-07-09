package com.commerce.entity;

import com.commerce.enumeration.OrderStatus;
import com.commerce.enumeration.PayMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Getter
@Setter
@Entity
@Table(name = "ordertable")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private Date orderDate;

    private OrderStatus orderStatus;

    private PayMethod payMethod;

    private Double orderPrice;

    private String deliveryAddress;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProduct> orderProducts;

    @ManyToOne(fetch=FetchType.EAGER)
    @JsonIgnore
    private User user;

    private String recipientName;

    private String recipientTel;

    @CreatedBy
    private Long createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private Long updatedBy;

    @LastModifiedDate
    private Date updatedDate;




}
