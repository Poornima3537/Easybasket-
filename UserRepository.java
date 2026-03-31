package com.codespace.easybasket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespace.easybasket.model.User;

public interface  UserRepository extends JpaRepository<User, Long>{
    
}
