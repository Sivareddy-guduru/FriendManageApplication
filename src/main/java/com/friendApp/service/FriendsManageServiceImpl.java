package com.friendApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.friendApp.dao.UserRepository;
import com.friendApp.entities.Blocked;
import com.friendApp.entities.Friends;
import com.friendApp.entities.Subscribed;
import com.friendApp.entities.User;
import com.friendApp.utils.FriendApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sivareddy.guduru on 30/1/18.
 */
@Service
public class FriendsManageServiceImpl implements FriendsManageService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public JSONPObject makefriends(String email1, String email2) throws FriendApiException {
        Map<String, Object> response = new HashMap<>();

        if (isOneBlockedAnother(email1, email2)) {
            response.put("success", false);
            response.put("message", "One Blocked Another");
        } else if (isAlreadyFriend(email1, email2)) {
            response.put("success", false);
            response.put("message", "Already a friend");
        } else {

            User user = userRepository.findByName(email1);
            if (user != null) {
                Friends friends = new Friends();
                friends.setFriend(email2);
                user.getFriends().add(friends);
            } else {
                user = new User();
                user.setName(email1);
                Friends friends = new Friends();
                friends.setFriend(email2);
                user.getFriends().add(friends);
            }

            User u1 = userRepository.findByName(email2);
            if (u1 != null) {
                Friends friends = new Friends();
                friends.setFriend(email1);
                u1.getFriends().add(friends);
            } else {
                u1 = new User();
                u1.setName(email2);
                Friends friends = new Friends();
                friends.setFriend(email1);
                u1.getFriends().add(friends);
            }
            userRepository.save(user);
            userRepository.save(u1);

            response.put("success", "true");
        }
        JSONPObject result = new JSONPObject("response", response);
        return result;
    }

    @Override
    public JSONPObject getMutualFriends(String email1, String email2) throws FriendApiException {
        List<String> mutualFriends = new ArrayList<>();
        User user1 = userRepository.findByName(email1);

        User user2 = userRepository.findByName(email2);

        if(user1 != null && user2 !=null){
            List<String> friends = user2.getFriends().stream().map(friends1 -> friends1.getFriend()).collect(Collectors.toList());

            mutualFriends = user1.getFriends().stream().map(friends1 -> friends1.getFriend()).collect(Collectors.toList()).stream().filter(s -> friends.contains(s)).collect(Collectors.toList());
            String result = null;
            try {
                result = new ObjectMapper().writeValueAsString(mutualFriends);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("friends", mutualFriends);
        response.put("count", String.valueOf(mutualFriends.size()));

        JSONPObject json = new JSONPObject("response", response);
        return json;
    }

    @Override
    @Transactional
    public JSONPObject subscribe(String email1, String email2) {
        User user = userRepository.findByName(email2);

        User user1 = userRepository.findByName(email1);
        if (user1 == null) {
            user1 = new User();
            user1.setName(email1);
            userRepository.save(user1);
        }

        if (user != null) {
            Subscribed subscribed = new Subscribed();
            subscribed.setSubcribed(email1);
            user.getSubscribed().add(subscribed);
        } else {
            user = new User();
            user.setName(email2);
            Subscribed subscribed = new Subscribed();
            subscribed.setSubcribed(email1);
            user.getSubscribed().add(subscribed);
            userRepository.save(user);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return new JSONPObject("response", response);

    }


    @Override
    @Transactional
    public JSONPObject block(String email1, String email2) throws FriendApiException {

        boolean areSubcribed = false;
        boolean areFriends = false;
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findByName(email1);

        User user2 = userRepository.findByName(email2);
        if(user == null || user2 == null){
            response.put("success", false);
            response.put("message"," Cannot block as they are neither friends nor subscribed");
            return new JSONPObject("response", response);
        }


            Blocked blocked = new Blocked();
            blocked.setBlocked(email2);
            user.getBlocked().add(blocked);


        Set<Subscribed> subscribedList = user2.getSubscribed();
        Set<Friends> friendsList = user2.getFriends();
        Iterator<Subscribed> iter = subscribedList.iterator();
        while (iter.hasNext()) {
            String email = iter.next().getSubcribed();
            if (email.equals(email1)) {
                areSubcribed = true;
                iter.remove();
            }
        }
        Iterator<Friends> friendsIterator = friendsList.iterator();
        while (friendsIterator.hasNext()) {
            String email = friendsIterator.next().getFriend();
            if (email.equals(email1)) {
                areFriends = true;
                friendsIterator.remove();
            }
        }

        if(areFriends || areSubcribed){
            response.put("success", true);
        }else{
            response.put("success", false);
            response.put("message"," Cannot block as they are neither friends nor subscribed");
        }

        return new JSONPObject("response", response);
    }

    @Override
    public JSONPObject willgetUpdates(String email) throws FriendApiException {
        User user = userRepository.findByName(email);
        List<String> result = new ArrayList<>();
        if (user != null) {
            List<String> friends = user.getFriends().stream().map(friends1 -> friends1.getFriend()).collect(Collectors.toList());
            List<String> subscribed = user.getSubscribed().stream().map(friends1 -> friends1.getSubcribed()).collect(Collectors.toList());
            List<String> blocked = user.getBlocked().stream().map(friends1 -> friends1.getBlocked()).collect(Collectors.toList());
            result = Stream.concat(friends.stream(), subscribed.stream())
                    .distinct()
                    .collect(Collectors.toList());

            result.removeAll(blocked);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("recipients", result);
        response.put("count", result.size());
        return new JSONPObject("response", response);
    }

    @Override
    public JSONPObject retrieveFriends(String email) throws FriendApiException {
        List<String> friends = new ArrayList<>();
        User user = userRepository.findByName(email);
        if (user != null) {
            Set<Friends> friendsList = user.getFriends();
            friends = friendsList.stream().map(friends1 -> friends1.getFriend()).collect(Collectors.toList());
        }
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("friends", friends);
        response.put("count", friends.size());

        return new JSONPObject("response", response);
    }


    public boolean isOneBlockedAnother(String email1, String email2) {
        User user1 = userRepository.findByName(email1);
        User user2 = userRepository.findByName(email2);
        if (user1 != null) {
            List<String> blocked = user1.getBlocked().stream().map(friends1 -> friends1.getBlocked()).collect(Collectors.toList());
            if (!blocked.contains(email2)) {
                if (user2 != null) {
                    blocked = user2.getBlocked().stream().map(friends1 -> friends1.getBlocked()).collect(Collectors.toList());
                    return blocked.contains(email1);
                }
            } else
                return true;
        }
        return false;
    }

    public boolean isAlreadyFriend(String email1, String email2) {
        User user1 = userRepository.findByName(email1);
        if (user1 != null) {
            return user1.getFriends().stream().anyMatch(friends -> friends.getFriend().equals(email2));
        }
        return false;
    }
}
