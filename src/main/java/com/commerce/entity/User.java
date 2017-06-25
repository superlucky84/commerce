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
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy="user")
    private Cart cart;

    @OneToMany(mappedBy="user")
    private Set<Order> orders = new HashSet<Order>();

    private String name;

    @Column(unique=true)
    private String username;

    private String password;

    private String email;

    private String mobile;

    private String createdBy;


    @Column(insertable = true,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String updatedBy;

    @Column(insertable = true,updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authoritys;


}
