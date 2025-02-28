package com.fulopl.backend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Set;

@Entity
public class UserGroup {
    @Id
    private Long id;

    private String name;

    @ManyToOne
    private AppUser owner;

    @ManyToMany
    private Set<AppUser> members;


}
