package com.commerce.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.tree.AbstractEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
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
public class Authority extends AbstractEntity implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Long id;

    private String authority;


    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

}
