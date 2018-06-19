package com.freelancer.model;
import java.beans.Transient;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserEntity  {
	
	@Id
	@GeneratedValue( strategy=GenerationType.AUTO )
	private Integer id;
	
	@Column(length=20, nullable=false, unique=true)
	@NotEmpty
	private String email;
	
	@Column(length=50, nullable=false)
	private String nome;
	

	@Column(nullable=false)
	@NotEmpty
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	private Date datanascimento;
	
	private boolean admin;
	
	@JsonIgnore
    @OneToMany(targetEntity= JobEntity.class, mappedBy = "owner" )
	private List<JobEntity> ofertasVagas;
    
    @ManyToMany()
    @JoinTable(name="candidatos_vagas", joinColumns = @JoinColumn(name= "user_id"),inverseJoinColumns = @JoinColumn(name="job_id"))
    @JsonIgnore
    private List<JobEntity> candidatoAsVagas;

    @Transient
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}    
}
