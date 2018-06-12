package com.freelancer.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.freelancer.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	Page<UserEntity> findAll(Pageable page);
	Page<UserEntity> findByNomeIgnoreCaseContaining(String nome, Pageable page);
}
