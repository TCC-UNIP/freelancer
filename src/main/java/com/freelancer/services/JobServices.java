package com.freelancer.services;

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

}
