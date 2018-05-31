package com.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancer.model.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, Integer> {

}
