package com.freelancer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancer.model.UserEntity;
import com.freelancer.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository userRepository;
	
	public UserEntity saveAndUpdate(UserEntity user) {
		return userRepository.save(user);
	}
	
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	public void delete(UserEntity user) {
		userRepository.delete(user);
	}
	
	public List<UserEntity> listAllUsers(){
		return userRepository.findAll();
	}
	
	public Optional<UserEntity> findbyid(Integer id) {
		return userRepository.findById(id);		
	}
}
