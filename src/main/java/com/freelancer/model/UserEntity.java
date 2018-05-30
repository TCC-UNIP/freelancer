package com.freelancer.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class UserEntity {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Integer id;
	
	@Column(length=20, nullable=false, unique=true)
	private String nickname;
	
	@Column(length=50, nullable=false)
	private String nome;
	
	private Date datanascimento;
	
	 @OneToMany( targetEntity=JobOfferEntity.class )
	private List<JobOfferEntity> ofertasVagas;

}
