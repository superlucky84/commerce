package com.commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
public class CartProduct {


    @EmbeddedId
    private CartProduct.Id id = new CartProduct.Id();


    @ManyToOne
    @MapsId("cartId")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @MapsId("productId")
    @JsonIgnore
    private Product product;

    private Long buyCount;

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

        private Long cartId;

        private Long productId;
    }

}
