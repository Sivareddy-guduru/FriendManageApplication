package com.friendApp.dao;

import com.friendApp.entities.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sivareddy.guduru on 30/1/18.
 */
public interface FriendsRepository extends JpaRepository<Friends,Integer>{
}
