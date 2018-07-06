package com.freelancer.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import com.freelancer.model.JobEntity;
import com.freelancer.model.UserEntity;
import com.freelancer.services.JobServices;

@Controller
@RequestMapping
public class JobController {
	
	@Autowired
	JobServices jobServicos;

	//salva um serviço passando o email de um usuario
	@RequestMapping(value="protected/job/save{email}", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  JobEntity saveAndUpdate(@RequestBody JobEntity job, @Param("email") String email, Authentication authenticatioToken, HttpServletResponse response) {
		
		if (authenticatioToken.getName().equals(email)) {
			
			response.setStatus(201);
			return jobServicos.saveAndUpdate(job,email);
			
		}else {
			response.setStatus(403);
			return null;
		}
		
				
	}
	
	//lista todos os serviços 
	@GetMapping(value= "/job")
	@ResponseBody
	public List<JobEntity> listaTodosJobs(){
		return jobServicos.listarServicos();
	}
	
	//ENCONTRA SERVIÇO PELO ID
	@RequestMapping(value="admin/job/find{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Optional<JobEntity> encontrarJobEspecifico(@Param("id") Integer id){
		return jobServicos.findById(id);
	}
	
	//DELETA SERVIÇO PASSANDO UM ID
	@RequestMapping(value="admin/job/deletar{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void deletarJob(@Param("id") Integer id){
		 jobServicos.deletar(id);
	}
	
	//busca todos os jobs a qual um usuario e dono
	@RequestMapping(value="protected/job/all/{email}/{page}/{nitens}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<JobEntity> listarUserJobs(@PathVariable("email") String email,@PathVariable("page") int page, @PathVariable("nitens") int nitens){
		Page<JobEntity> pageRequest = jobServicos.findAllUserJobs(email, PageRequest.of(page, nitens));
		return pageRequest.getContent();
	}
	
	//encontra por titulo
	@RequestMapping(value="job/findtitulo/{titulo}/{page}/{nitens}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<JobEntity> encontrarJobPorTitulo(@PathVariable("titulo") String titulo, @PathVariable("page") int page, @PathVariable("nitens") int nitens ){
		Page<JobEntity> jobPagelist = jobServicos.findByTitulo(titulo, PageRequest.of(page, nitens));
		return jobPagelist.getContent();
	}
	
	//CANDIDATAR USUARIO A VAGA
	@RequestMapping(value="protected/job/candidatar/{email}/{idjob}", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void candidatarUser(@PathVariable("email") String email, @PathVariable("idjob") int jobId, Authentication authenticationToken, HttpServletResponse response) throws  Exception{
		if (authenticationToken.getName().equals(email)) {
			response.setStatus(200);
			jobServicos.candidatar(email, jobId);
		}else {
			response.setStatus(403);
		}
		
	}
	
	//LISTAR CANDIDATOS
	@RequestMapping(value="protected/job/candidatos/{jobid}/{page}/{nitens}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<UserEntity> listarCandidatos(@PathVariable("jobid") Integer jobid, @PathVariable("page") Integer page, @PathVariable("nitens") Integer nitens, Authentication authenticationToken, HttpServletResponse response){
		Optional<JobEntity> jobOptional = jobServicos.findById(jobid);
		JobEntity job = jobOptional.get();
		if (authenticationToken.getName().equals(job.getOwner().getEmail())) {
			 return job.getCandidatos();
		}else {
			response.setStatus(403);
			return null;
		}
	}
	
}
