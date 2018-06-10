package com.freelancer.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.freelancer.model.JobEntity;
import com.freelancer.model.UserEntity;

public interface JobRepository extends JpaRepository<JobEntity, Integer> {
	Page<JobEntity> findByOwner(UserEntity user, Pageable page);
	
}
