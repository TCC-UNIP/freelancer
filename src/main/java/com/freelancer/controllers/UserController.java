package com.freelancer.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freelancer.model.UserEntity;
import com.freelancer.services.UserServices;

@Controller
@RequestMapping(value="/cliente")
public class UserController {
	
	@Autowired
	UserServices userServ;
	
	
	@RequestMapping(method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  UserEntity salvar(@RequestBody UserEntity user) {
		
		return userServ.saveAndUpdate(user) ;
	}
	
	
	@RequestMapping(value="/all", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  List<UserEntity> listar() {
		return userServ.listAllUsers() ;
	}
	
	@RequestMapping(value="/buscar{id}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  Optional<UserEntity> findbyid(@Param("id") Integer id) {
		return userServ.findbyid(id) ;
	}
	
}
