package com.friendApp.dao;

import com.friendApp.entities.Blocked;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sivareddy.guduru on 31/1/18.
 */
public interface BlockedRepository extends JpaRepository<Blocked,Integer> {
}
