package com.commerce.entity;

import com.commerce.enumeration.OrderStatus;
import com.commerce.enumeration.PayMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private User user;

    private String recipientName;

    private String recipientTel;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;




}
