package com.freelancer.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import com.freelancer.model.JobEntity;
import com.freelancer.model.UserEntity;
import com.freelancer.services.JobServices;
import com.freelancer.services.UserServices;

@Controller
@RequestMapping(value="/job")
public class JobController {
	
	@Autowired
	JobServices jobServ;
	@Autowired
	UserServices userServ;
	
	@PostMapping
	@ResponseBody
	public  JobEntity saveAndUpdate(@RequestBody JobEntity job) {
	
		return jobServ.saveAndUpdate(job);
		
	}
}
