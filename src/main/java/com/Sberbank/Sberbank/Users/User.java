package com.Sberbank.Sberbank.Users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String name;
    private String phone;

    public User() {
        this.name = "";
        this.phone = "";
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}

