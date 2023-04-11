package com.endtoend.bfit.models;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private UUID id;
    @Column
    private String username;
    @Column
    private String password;

    public User(){}

    public User(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
