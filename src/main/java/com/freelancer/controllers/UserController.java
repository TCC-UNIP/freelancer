package com.freelancer.controllers;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value="cliente")
public class UserController {
	
	@Autowired
	UserServices userServ;
	
	
	@RequestMapping(method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public  UserEntity salvar(@RequestBody UserEntity user) {
		
		return userServ.saveAndUpdate(user) ;
		   
	}
	
	
	
}
