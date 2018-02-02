package com.friendApp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by sivareddy.guduru on 30/1/18.
 */
@Entity
public class Subscribed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String subcribed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubcribed() {
        return subcribed;
    }

    public void setSubcribed(String subcribed) {
        this.subcribed = subcribed;
    }
}
