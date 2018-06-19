package com.freelancer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import com.freelancer.model.JobEntity;
import com.freelancer.services.JobServices;

@Controller
@RequestMapping
public class JobController {
	
	@Autowired
	JobServices jobServ;

	//salva um serviço passando o nickname de um usuario
	@RequestMapping(value="protected/job/save{nickname}", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  JobEntity saveAndUpdate(@RequestBody JobEntity job, @Param("nickname") String nickname, Authentication auth, HttpServletResponse response) {
		if (auth.getName().equals(nickname)) {
			response.setStatus(201);
			return jobServ.saveAndUpdate(job,nickname);
		}else {
			response.setStatus(403);
			return null;
		}
		
				
	}
	
	//lista todos os serviços 
	@GetMapping(value= "/job")
	@ResponseBody
	public List<JobEntity> listaTodosJobs(){
		return jobServ.listarServicos();
	}
	
	//ENCONTRA SERVIÇO PELO ID
	@RequestMapping(value="admin/job/find{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Optional<JobEntity> findOne(@Param("id") Integer id){
		return jobServ.findById(id);
	}
	
	//DELETA SERVIÇO PASSANDO UM ID
	@RequestMapping(value="admin/job/deletar{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void deletar(@Param("id") Integer id){
		 jobServ.deletar(id);
	}
	
	//busca todos os jobs a qual um usuario e dono
	@RequestMapping(value="protected/job/all/{nickname}/{page}/{nitens}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<JobEntity> allUserJobs(@PathVariable("nickname") String nickname,@PathVariable("page") int page, @PathVariable("nitens") int nitens){
		Page<JobEntity> pageRequest = jobServ.findAllUserJobs(nickname, PageRequest.of(page, nitens));
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
	@RequestMapping(value="protected/job/candidatar/{nickname}/{idjob}", method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void candidatarUser(@PathVariable("nickname") String nickname, @PathVariable("idjob") int jobId, Authentication auth, HttpServletResponse response) throws  Exception{
		if (auth.getName().equals(nickname)) {
			response.setStatus(200);
			jobServ.candidatar(nickname, jobId);
		}else {
			response.setStatus(403);
		}
		
	}	
	
}
