package com.freelancer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class JobOfferEntity {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Integer id;
	
	@ManyToOne
	private UserEntity owner;
	
}
