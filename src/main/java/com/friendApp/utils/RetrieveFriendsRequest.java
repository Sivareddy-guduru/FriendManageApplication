package com.friendApp.utils;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.*;

/**
 * Created by sivareddy.guduru on 1/2/18.
 */
public class RetrieveFriendsRequest {
    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "^[a-z0-9._-]+@[a-z0-9-]+(?:\\.[a-z0-9-]+)*$")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RetrieveFriendsRequest(){

    }
    public RetrieveFriendsRequest(String email){
      this.email = email;
    }

}
