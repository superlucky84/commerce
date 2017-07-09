package com.commerce.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.tree.AbstractEntity;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by jinwoo on 2017. 6. 25..
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Authority extends AbstractEntity implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    private String authority;

    @CreatedBy
    private Long createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private Long updatedBy;

    @LastModifiedDate
    private Date updatedDate;

}
