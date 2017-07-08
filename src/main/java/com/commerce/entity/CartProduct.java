package com.commerce.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

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

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date createdDate;

    @CreatedDate
    private String updatedBy;

    @LastModifiedBy
    private Date updatedDate;

    @Getter
    @Setter
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class Id implements Serializable {

        private Long cartId;

        private Long productId;
    }

}
