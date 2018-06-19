package com.freelancer.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	public JobEntity saveAndUpdate(JobEntity job, String nickname) {
			UserEntity user = userServices.findByNickName(nickname);
			job.setOwner(user);
			job.setProprietarioNome(user.getNome());
			jobRepository.save(job);
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
	public Page<JobEntity> findAllUserJobs(String nickname, PageRequest page){
		UserEntity user = userServices.findByNickName(nickname);
		return jobRepository.findByOwner(user, page);
	}
	
	//Candidatar um usuario a vaga
		public void candidatar(String nickname, int jobId) throws  Exception{
			UserEntity user = userServices.findByNickName(nickname);
			Optional<JobEntity> jobOptional = jobRepository.findById(jobId);
			
			if (jobOptional.isPresent() && (user !=null)) {
				JobEntity job = jobOptional.get();
			
				if (job.getOwner() != user) {
					List<UserEntity> candidatos = job.getCandidatos();
					if (candidatos.contains(user)) {
						throw new DataIntegrityViolationException("Usuario ja existe");
					}else {
					List<JobEntity> candidatandoSe = user.getCandidatoAsVagas();
					candidatos.add(user);
					job.setCandidatos(candidatos);
					candidatandoSe.add(job);
					jobRepository.save(job);
					userServices.save(user);
					}
				}else {
					throw new Exception();
					
				}
			}		
		}
	

}
