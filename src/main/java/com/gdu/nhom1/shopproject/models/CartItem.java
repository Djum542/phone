package com.gdu.nhom1.shopproject.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private Double price;
    private int quantity;
    private String image;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
