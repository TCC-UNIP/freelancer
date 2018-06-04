package com.freelancer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelancer.model.JobEntity;
import com.freelancer.model.UserEntity;
import com.freelancer.repository.JobRepository;

@Service
public class JobServices {
	
	@Autowired
	JobRepository jobRepository;
	
	public JobEntity saveAndUpdate(JobEntity job) {
		return jobRepository.save(job);
	}
	
	//usuario com permissão de administrador buscar todos usuarios
	public List<JobEntity> listarServicos(){
		return jobRepository.findAll();
	}
	
	public void deletar(Integer id) {
		 jobRepository.deleteById(id);;
	}
	
	public Optional<JobEntity> findOne(Integer id) {
		
		return jobRepository.findById(id);
	}
	

}
