package com.freelancer.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;

import com.freelancer.model.JobEntity;
import com.freelancer.services.JobServices;


@Controller
@RequestMapping(value="/job")
public class JobController {
	
	@Autowired
	JobServices jobServ;

	
	@PostMapping
	@ResponseBody
	public  JobEntity saveAndUpdate(@RequestBody JobEntity job) {
		
		return jobServ.saveAndUpdate(job);
		
	}
	
	@GetMapping
	@ResponseBody
	public List<JobEntity> listaTodosJobs(){
		return jobServ.listarServicos();
	}
	
	@RequestMapping(value="/dono{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Optional<JobEntity> listByOwner(@Param("id") Integer id){
		return jobServ.listByOwner(id);
		
	}
}
