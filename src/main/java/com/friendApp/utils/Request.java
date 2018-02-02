package com.friendApp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sivareddy.guduru on 30/1/18.
 */
public class Request {
    private static Logger logger = LoggerFactory.getLogger(Request.class);

    @Size(min = 2, max = 2)
    private List<String> friends;

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Request(){

    }

    public Request(String friend1, String friend2){
        this.friends = new ArrayList<>();
        friends.add(friend1);
        friends.add(friend2);

    }


}
