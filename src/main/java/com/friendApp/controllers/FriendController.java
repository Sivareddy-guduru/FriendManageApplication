package com.friendApp.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.friendApp.service.FriendsManageService;
import com.friendApp.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sivareddy.guduru on 30/1/18.
 */
@RestController
@RequestMapping("api/managefriends/")
public class FriendController {

    @Autowired
    FriendsManageService friendsManageService;

    @PostMapping("/retrievefriends")
    public JSONPObject findByName(@Validated @RequestBody final RetrieveFriendsRequest request) throws FriendApiException {
        EmailValidation.validate(request.getEmail());
        return friendsManageService.retrieveFriends(request.getEmail());
    }


    @PostMapping("/mutualfriends")
    public JSONPObject getMutualFriends(@Validated @RequestBody final Request request) throws FriendApiException {
        EmailValidation.validate(request.getFriends().get(0));
        EmailValidation.validate(request.getFriends().get(1));
        String email1 = request.getFriends().get(0);
        String email2 = request.getFriends().get(1);
        return friendsManageService.getMutualFriends(email1, email2);
    }

    @PostMapping("/makefriends")
    public JSONPObject makeFriends(@Validated @RequestBody final Request request) throws FriendApiException {
        EmailValidation.validate(request.getFriends().get(0));
        EmailValidation.validate(request.getFriends().get(1));
        return friendsManageService.makefriends(request.getFriends().get(0), request.getFriends().get(1));

    }

    @PostMapping("/subscribe")
    public JSONPObject subscribe(@Validated @RequestBody final Request request) throws FriendApiException {
        EmailValidation.validate(request.getFriends().get(0));
        EmailValidation.validate(request.getFriends().get(1));
        return friendsManageService.subscribe(request.getFriends().get(0), request.getFriends().get(1));

    }

    @PostMapping("/block")
    public JSONPObject block(@Validated @RequestBody final Request request) throws FriendApiException {
        EmailValidation.validate(request.getFriends().get(0));
        EmailValidation.validate(request.getFriends().get(1));

        return friendsManageService.block(request.getFriends().get(0), request.getFriends().get(1));


    }

    @PostMapping("/recipients")
    public JSONPObject getRecipients(@Validated @RequestBody final RecipientsRequest recipientsRequest) throws FriendApiException {
        String sender = recipientsRequest.getSender();
        EmailValidation.validate(sender);
        return friendsManageService.willgetUpdates(sender);

    }

}
