package com.freelancer.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freelancer.model.JobEntity;
import com.freelancer.model.UserEntity;
import com.freelancer.services.UserServices;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	UserServices userServ;
	
	
	@PostMapping
	@ResponseBody
	public  UserEntity saveAndUpdate(@RequestBody UserEntity user) {
		
		return userServ.saveAndUpdate(user) ;
		
	}
	
	@GetMapping
	@ResponseBody
	public  List<UserEntity> listar() {
		
		return userServ.listAllUsers() ;
		
	}
	
	@RequestMapping(value="/buscar{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  Optional<UserEntity> findbyid(@Param("id") Integer id) {	
		
		return userServ.findbyid(id) ;
		
	}

	@RequestMapping(value="/deletar{id}", method= RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public void deleteById(@Param("id") Integer id) {	
		
		userServ.delete(id);
		
	}
	
	
}
