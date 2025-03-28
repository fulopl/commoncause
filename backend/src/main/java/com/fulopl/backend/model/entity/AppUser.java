package com.fulopl.backend.model.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "appuser_seq", initialValue = 101, allocationSize = 1)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "appuser_role",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "appuser_usergroup",  // Name of the join table
            joinColumns = @JoinColumn(name = "appuser_id"),  // Foreign key for Student
            inverseJoinColumns = @JoinColumn(name = "usergroup_id") // Foreign key for Course
    )
    private Set<UserGroup> groups;

    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return this.id;
    }

    public Collection<UserGroup> getGroups() {
        return this.groups;
    }

}
