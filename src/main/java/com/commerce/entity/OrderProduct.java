package com.commerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by jinwoo on 2017. 6. 24..
 */
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class OrderProduct {

    @EmbeddedId
    private OrderProduct.Id id = new OrderProduct.Id();

    @ManyToOne
    @MapsId("orderId")
    @JsonIgnore
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    private Integer orderCount;

    @CreatedBy
    private Long createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private Long updatedBy;

    @LastModifiedDate
    private Date updatedDate;



    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Id implements Serializable {

        private Long orderId;

        private Long productId;

    }

}
