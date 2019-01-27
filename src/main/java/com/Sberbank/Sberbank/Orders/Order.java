package com.Sberbank.Sberbank.Orders;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;

@Entity
@Data
@Table(name = "orders")
public class Order {

    private @Id @GeneratedValue Long id;
    private Long author;
    private Integer cost;
    private HashMap<Long, Status> map;

    public Order() {
        this.author = Long.valueOf(1);
        this.cost = 0;
        this.map = new HashMap<Long, Status> ();
    }

    public Order(Long author, Integer cost, HashMap<Long, Status> map) {
        this.author = author;
        this.cost = cost;
        this.map = map;
    }
}