package com.freelancer.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancer.www.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
}
