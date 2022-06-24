package com.example.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.hotel.entity.*;

public interface UserRep extends JpaRepository<User, Integer>{
User findByEmail(String email);
	
	User findByUsername(String username);
}
