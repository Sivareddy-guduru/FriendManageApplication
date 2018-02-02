package com.friendApp.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sivareddy.guduru on 30/1/18.
 */
@Entity
public class User {
     @Id
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "name",referencedColumnName = "name")
    private Set<Friends> friends = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "name",referencedColumnName = "name")
    private Set<Subscribed> subscribed = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "name",referencedColumnName = "name")
    private Set<Blocked> blocked = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Friends> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friends> friends) {
        this.friends = friends;
    }

    public Set<Subscribed> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Set<Subscribed> subscribed) {
        this.subscribed = subscribed;
    }

    public Set<Blocked> getBlocked() {
        return blocked;
    }

    public void setBlocked(Set<Blocked> blocked) {
        this.blocked = blocked;
    }
}
