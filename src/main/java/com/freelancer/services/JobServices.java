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

@Service
public class JobServices {
	
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	UserServices userServices;
	
	//SAVA UM SERVIÇO A UM USUARIO,
	public JobEntity saveAndUpdate(JobEntity job, Integer userID) {
		Optional<UserEntity> userOptional = userServices.findbyid(userID);
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
	public Optional<JobEntity> findById(Integer id) {
		
		return jobRepository.findById(id);
	}
	
	//ENCONTRAR POR TITULO
	public Page<JobEntity> findByTitulo(String titulo, PageRequest page) {
		
		return jobRepository.findByTituloContainingIgnoreCase(titulo, page);
	}
	
	//ENCONTAR TODOS OS JOBS DE UM DETERMINADO USUARIO
	public Page<JobEntity> findAllUserJobs(Integer id, PageRequest page){
		Optional <UserEntity> userOptional = userServices.findbyid(id);
		return jobRepository.findByOwner(userOptional.get(), page);
	}
	
	//Candidatar um usuario a vaga
		public void candidatar(int userId, int jobId) throws  Exception{
			Optional<UserEntity> userOptional = userServices.findbyid(userId);
			Optional<JobEntity> jobOptional = jobRepository.findById(jobId);
			

			if (jobOptional.isPresent() && userOptional.isPresent()) {
				JobEntity job = jobOptional.get();
				UserEntity user = userOptional.get();
				
				if (job.getOwner() != user) {
					List<UserEntity> candidatos = job.getCandidatos();
					List<JobEntity> candidatandoSe = user.getCandidatoAsVagas();
					candidatos.add(user);
					job.setCandidatos(candidatos);
					candidatandoSe.add(job);
					jobRepository.save(job);
					userServices.save(user);
				}else {
					throw new Exception();
					
				}
			}		
		}
	

}
