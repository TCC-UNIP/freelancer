package com.freelancer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.freelancer.model.JobEntity;
import com.freelancer.model.UserEntity;
import com.freelancer.repository.JobRepository;
import com.freelancer.repository.UserRepository;

@Service
public class JobServices {
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public JobEntity saveAndUpdate(JobEntity job, Integer userID) {
		Optional<UserEntity> userOptional = userRepository.findById(userID);
		if (userOptional.isPresent()) {
			UserEntity user= userOptional.get();
			job.setOwner(user);
			jobRepository.save(job);
		}
		
		return job;
		
		
	}
	
	//usuario com permissão de administrador buscar todos usuarios
	public List<JobEntity> listarServicos(){
		return jobRepository.findAll();
	}
	
	//DELETAR BY ID
	public void deletar(Integer id) {
		 jobRepository.deleteById(id);;
	}
		
	//ENCONTRAR POR ID
	public Optional<JobEntity> findOne(Integer id) {
		
		return jobRepository.findById(id);
	}
	
	//ENCONTRAR POR TITULO
	public Page<JobEntity> findByTitulo(String titulo, PageRequest page) {
		
		return jobRepository.findByTituloContainingIgnoreCase(titulo, page);
	}
	
	//ENCONTAR TODOS OS JOBS DE UM DETERMINADO USUARIO
	public Page<JobEntity> findAllUserJobs(Integer id, PageRequest page){
		Optional <UserEntity> userOptional = userRepository.findById(id);
		return jobRepository.findByOwner(userOptional.get(), page);
		
	}

}
