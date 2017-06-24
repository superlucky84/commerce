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
public class CartProduct {


    @EmbeddedId
    private CartProduct.Id id = new CartProduct.Id();


    @ManyToOne
    @MapsId("cartId")
    private Cart cart;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    private Long buyCount;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    @Getter
    @Setter
    @EqualsAndHashCode
    @Generated
    public static class Id implements Serializable {

        private Long cartId;

        private Long productId;
    }

}
