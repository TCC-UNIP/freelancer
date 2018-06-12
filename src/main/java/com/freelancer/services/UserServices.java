package com.freelancer.services;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.freelancer.model.JobEntity;
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
	
	public Page<UserEntity> listAllUsers(PageRequest page){
		return userRepository.findAll(page);
	}
	
	public Optional<UserEntity> findbyid(Integer id) {
		return userRepository.findById(id);		
	}
	
	public Page<UserEntity> findByNome(String nome, PageRequest page){
		return userRepository.findByNomeIgnoreCaseContaining(nome, page);
	}
	
	
	
}
