package com.Sberbank.Sberbank.Users;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "users")
public class User {
    private @Id @GeneratedValue Long id;
    private String name;
    private String vk;
    private String phoneNumber;
    private String photoUrl;
    private ArrayList<Long> buyList;

    public User() {
        this.name = "";
        this.phoneNumber = "";
        this.photoUrl = "";
        this.buyList = new ArrayList<Long>();
    }

    public User(String name, String phoneNumber, String photoUrl) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
    }

    public User(String name, String phoneNumber, String photoUrl, ArrayList<Long> buyList) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
        this.buyList = buyList;
    }
}


