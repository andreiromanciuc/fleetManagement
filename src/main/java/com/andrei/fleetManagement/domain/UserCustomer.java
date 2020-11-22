package com.andrei.fleetManagement.domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCustomer {

    @Id
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
