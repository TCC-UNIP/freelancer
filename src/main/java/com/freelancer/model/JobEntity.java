package com.freelancer.model;
import java.beans.Transient;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;




@Entity
public class JobEntity {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Integer id;
	
	@Column(nullable=false, length=60)
	private String titulo;
	
	@Column(nullable=false)
	private int salario;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	private UserEntity owner;
	
	
	@ManyToMany(mappedBy = "candidatoAsVagas",fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
	private List<UserEntity> candidatos;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getSalario() {
		return salario;
	}


	public void setSalario(int salario) {
		this.salario = salario;
	}

	@Transient
	public UserEntity getOwner() {
		return owner;
	}


	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	@Transient
	public List<UserEntity> getCandidatos() {
		return candidatos;
	}


	public void setCandidatos(List<UserEntity> candidatos) {
		this.candidatos = candidatos;
	}

	
}
