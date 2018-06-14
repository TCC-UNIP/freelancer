package com.freelancer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import com.freelancer.model.JobEntity;
import com.freelancer.services.JobServices;



@Controller
@RequestMapping
public class JobController {
	
	@Autowired
	JobServices jobServ;

	//salva passando o id de um usuario
	@RequestMapping(value="protected/job/save{id}", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  JobEntity saveAndUpdate(@RequestBody JobEntity job, @Param("id") Integer id) {
		return jobServ.saveAndUpdate(job,id);		
	}
	
	@GetMapping()
	@ResponseBody
	public List<JobEntity> listaTodosJobs(){
		return jobServ.listarServicos();
	}
	
	//encontra por id
	@RequestMapping(value="admin/job/find{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Optional<JobEntity> findOne(@Param("id") Integer id){
		return jobServ.findById(id);
	}
	
	//deleta por id
	@RequestMapping(value="admin/job/deletar{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void deletar(@Param("id") Integer id){
		 jobServ.deletar(id);
	}
	
	//busca todos os jobs relacionada de um usuario
	@RequestMapping(value="protected/job/all/{id}/{page}/{nitens}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<JobEntity> allUserJobs(@PathVariable("id") Integer id,@PathVariable("page") int page, @PathVariable("nitens") int nitens){
		Page<JobEntity> pageRequest = jobServ.findAllUserJobs(id, PageRequest.of(page, nitens));
		return pageRequest.getContent();
	}
	
	//encontra por titulo
	@RequestMapping(value="job/findtitulo/{titulo}/{page}/{nitens}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<JobEntity> findTitulo(@PathVariable("titulo") String titulo, @PathVariable("page") int page, @PathVariable("nitens") int nitens ){
		Page<JobEntity> jobPage = jobServ.findByTitulo(titulo, PageRequest.of(page, nitens));
		return jobPage.getContent();
	}
	
	//CANDIDATAR USUARIO A VAGA
	@RequestMapping(value="protected/job/candidatar/{userid}/{idjob}", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void candidatarUser(@PathVariable("userid") int userId, @PathVariable("idjob") int jobId) throws  Exception{
		jobServ.candidatar(userId, jobId);
	}	
	
}
