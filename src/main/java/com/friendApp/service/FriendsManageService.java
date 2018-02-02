package com.friendApp.service;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.friendApp.utils.FriendApiException;

/**
 * Created by sivareddy.guduru on 30/1/18.
 */

public interface FriendsManageService {

    public JSONPObject makefriends(String email1, String email2) throws FriendApiException;

    public JSONPObject getMutualFriends(String email1, String email2) throws FriendApiException;

    public JSONPObject subscribe(String email1, String email2) throws FriendApiException;

    public JSONPObject block(String email1, String email2) throws FriendApiException;

    public JSONPObject willgetUpdates(String email) throws FriendApiException;

    public JSONPObject retrieveFriends(String email) throws FriendApiException;



}
