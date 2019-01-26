package com.Sberbank.Sberbank.Users;

import com.Sberbank.Sberbank.Util.PhoneNumber;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;

@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String name;
    private String vk;
    private PhoneNumber phoneNumber;
    private String photoUrl;
    private ArrayList<String> buyList;

    public User() {
        this.name = "";
        this.vk = "";
        this.phoneNumber = new PhoneNumber(0, 0, 0, 0);
        this.photoUrl = "";
        this.buyList = new ArrayList<String>();
    }

    public User(String name, String vk) {
        this.name = name;
        this.vk = vk;
    }
}


