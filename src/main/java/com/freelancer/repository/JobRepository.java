package com.freelancer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.freelancer.model.JobEntity;
import com.freelancer.model.UserEntity;

public interface JobRepository extends JpaRepository<JobEntity, Integer> {
	List<JobEntity> findByOwner(UserEntity user);
	
}
