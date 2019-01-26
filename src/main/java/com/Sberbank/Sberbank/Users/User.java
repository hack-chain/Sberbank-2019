package com.Sberbank.Sberbank.Users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String name;
    private String vk;
    private String phoneNumber;
    private String photoUrl;
    private ArrayList<Long> buyList;

    public User() {
        this.name = "";
        this.vk = "";
        this.phoneNumber = "";
        this.photoUrl = "";
        this.buyList = new ArrayList<Long>();
    }

    public User(String name, String vk) {
        this.name = name;
        this.vk = vk;
    }
}


