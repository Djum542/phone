package com.gdu.nhom1.shopproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    public Role() {
    }
    public Role(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
//        return "Role{" +
//                "name='" + name + '\'' +
//                '}';
        return this.name;
    }
}
