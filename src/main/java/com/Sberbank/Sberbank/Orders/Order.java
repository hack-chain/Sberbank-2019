package com.Sberbank.Sberbank.Orders;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;

@Entity
@Data
@Table(name = "CUSTOMER_ORDER")
public class Order {

    private @Id @GeneratedValue Long id;
    private Integer cost;
    private HashMap<Long, Status> map;

    public Order() {
        this.cost = 1;
        this.map = new HashMap<Long, Status> ();
    }

    public Order(Integer cost, HashMap<Long, Status> map) {
        this.cost = cost;
        this.map = map;
    }
}