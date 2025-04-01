package com.fulopl.backend.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PaymentList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paymentlist_seq")
    @SequenceGenerator(name = "paymentlist_seq", sequenceName = "paymentlist_seq", initialValue = 101, allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne
    private UserGroup userGroup;

    @ManyToMany
    private Set<AppUser> contributors;
}
