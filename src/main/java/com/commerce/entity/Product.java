package com.commerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by jinwoo on 2017. 6. 24..
 */
@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String color;

    private String name;

    private String description;

    private Double price;

    private String imageFileName;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;
}
