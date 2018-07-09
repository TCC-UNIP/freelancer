package com.freelancer.model;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class JobEntity {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Integer id;
	
	@Column(nullable=false, length=60)
	private String titulo;
	
	@Column(nullable=false)
	private long salario;
	
	@ManyToOne
	@JoinColumn(name="owner_id")
	@JsonIgnore
	private UserEntity owner;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "candidatoAsVagas",fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<UserEntity> candidatos;
	
	private String categoria;
	
	
	@Column(length=50)
	private String proprietarioNome;
	
	@Column(nullable=false, length=300)
	private String descricao;
	
	@Column(length=50)
	private String localizacao;
	
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getProprietarioNome() {
		return proprietarioNome;
	}

	public void setProprietarioNome(String proprietarioNome) {
		this.proprietarioNome = proprietarioNome;
	}

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

	public long getSalario() {
		return salario;
	}

	public void setSalario(long salario) {
		this.salario = salario;
	}

	public UserEntity getOwner() {
		return owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<UserEntity> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(List<UserEntity> candidatos) {
		this.candidatos = candidatos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
