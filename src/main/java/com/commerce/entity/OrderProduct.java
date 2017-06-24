package com.commerce.entity;

import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jinwoo on 2017. 6. 24..
 */
@Entity
@Getter
@Setter
public class OrderProduct {

    @EmbeddedId
    private OrderProduct.Id id = new OrderProduct.Id();

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    private Integer orderCount;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;



    @Getter
    @Setter
    @EqualsAndHashCode
    @Generated
    public static class Id implements Serializable {

        private Long orderId;

        private Long productId;

    }

}
