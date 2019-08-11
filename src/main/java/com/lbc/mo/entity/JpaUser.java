package com.lbc.mo.entity;


import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
public class JpaUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public JpaUser() {
    }

    public JpaUser(Integer id) {
        this.id = id;
    }

    public JpaUser(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
