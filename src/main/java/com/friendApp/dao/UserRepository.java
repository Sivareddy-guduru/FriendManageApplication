package com.friendApp.dao;

import com.friendApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by sivareddy.guduru on 30/1/18.
 */
@Component
public interface UserRepository  extends JpaRepository<User,String>{


     public User findByName(String name);
}
