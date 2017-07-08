package com.commerce.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jinwoo on 2017. 6. 22..
 */
@Entity
@Getter
@Setter
@ToString(exclude = "orders")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy="user",cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Order> orders = new HashSet<Order>();

    private String name;

    @Column(unique=true)
    private String username;

    private String password;

    private String email;

    private String mobile;

    private String createdBy;


    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorities;


}
