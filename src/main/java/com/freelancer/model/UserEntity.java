package com.freelancer.model;
import java.beans.Transient;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class UserEntity  {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Integer id;
	
	@Column(length=20, nullable=false, unique=true)
	private String nickname;
	
	@Column(length=50, nullable=false)
	private String nome;
	
	private Date datanascimento;
	
    @OneToMany(targetEntity= JobEntity.class, mappedBy = "owner" )
	private List<JobEntity> ofertasVagas;
    
    @ManyToMany()
    @JoinTable(name="candidatos_vagas", joinColumns = @JoinColumn(name= "user_id"),inverseJoinColumns = @JoinColumn(name="job_id"))
    
    private List<JobEntity> candidatoAsVagas;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	@Transient
	public List<JobEntity> getOfertasVagas() {
		return ofertasVagas;
	}

	public void setOfertasVagas(List<JobEntity> ofertasVagas) {
		this.ofertasVagas = ofertasVagas;
	}

	public List<JobEntity> getCandidatoAsVagas() {
		return candidatoAsVagas;
	}

	public void setCandidatoAsVagas(List<JobEntity> candidatoAsVagas) {
		this.candidatoAsVagas = candidatoAsVagas;
	}

    
}
