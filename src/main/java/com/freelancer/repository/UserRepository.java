package com.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancer.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
}
