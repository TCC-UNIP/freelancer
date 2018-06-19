package com.freelancer.services;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.freelancer.model.UserEntity;
import com.freelancer.repository.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	UserRepository userRepository;
	
	//SALVAR USUARIOS
	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}
	//ATUALIZAR USUARIO
	public UserEntity update(UserEntity user) {
		UserEntity userEntity = userRepository.findByEmail(user.getEmail());
		if (userEntity.getDatanascimento()!=null) {
			userEntity.setDatanascimento((user.getDatanascimento()));
		}
		if (userEntity.getNome()!=null) {
			userEntity.setNome(user.getNome());
		}
		if (userEntity.getPassword()!=null) {
			userEntity.setPassword(encpritografarBcripty(user.getPassword()));
		}
		
		return userRepository.save(userEntity);
	}
	
	
	//ENCONTRAR USUARIO POR NICKNAME
	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	//DELETAR USUARIO
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
	//DELETAR USUARIO
	public void delete(UserEntity user) {
		userRepository.delete(user);
	}
	//LISTAR TODOS OS USUARIOS
	public Page<UserEntity> listAllUsers(PageRequest page){
		return userRepository.findAll(page);
	}
	//ENCONTRAR USUARIO POR ID
	public Optional<UserEntity> findbyid(Integer id) {
		return userRepository.findById(id);		
	}
	//ENCONTRAR USUARIO POR NOME
	public Page<UserEntity> findByNome(String nome, PageRequest page){
		return userRepository.findByNomeIgnoreCaseContaining(nome, page);
	}
	//METODO DE ENCRIPTO PARA SENHAS
	public String encpritografarBcripty(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);	
	}
	
	
}
