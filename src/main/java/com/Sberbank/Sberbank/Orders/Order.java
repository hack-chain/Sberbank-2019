package com.Sberbank.Sberbank.Orders;

import lombok.Data;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.HashMap;


@Entity
@Data
@Table(name = "orders")
public class Order {

    private @Id @GeneratedValue Long id;
    private Long author;
    private String description;
    private Integer cost;
    @Column(length = 100000)
    private HashMap<Long, Status> map;

    public Order() {
        this.author = Long.valueOf(1);
        this.description = "";
        this.cost = 0;
        this.map = new HashMap<Long, Status> ();
    }

    public Order(Long author, String description, Integer cost, HashMap<Long, Status> map) {
        this.author = author;
        this.description = description;
        this.cost = cost;
        this.map = map;
    }
}