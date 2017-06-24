package com.commerce.entity;

import lombok.Getter;
import lombok.Setter;

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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy="user")
    private Cart cart;

    @OneToMany(mappedBy="user")
    private Set<Order> orders = new HashSet<Order>();

    private String name;

    private String username;

    private String password;

    private String email;

    private String mobile;

    private String createdBy;

    private Date createdDate;

    private String updatedBy;

    private Date updatedDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authoritys;


}
