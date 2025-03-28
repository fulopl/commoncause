package com.fulopl.backend.model.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Set;

@Entity
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "usergroup_seq", initialValue = 101, allocationSize = 1)
    private Long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private AppUser owner;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<AppUser> members;


    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(AppUser user) {
        this.owner = user;
    }

    public AppUser getOwner() {
        return this.owner;
    }

    public Collection<AppUser> getMembers() {
        return this.members;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
