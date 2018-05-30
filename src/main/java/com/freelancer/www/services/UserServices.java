package com.freelancer.www.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancer.www.model.UserEntity;
import com.freelancer.www.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository userRepository;
	
	public UserEntity saveAndUpdate(UserEntity user) {
		return userRepository.save(user);
	}
	
	public void delete(UserEntity user) {
		userRepository.delete(user);
	}
	
}
