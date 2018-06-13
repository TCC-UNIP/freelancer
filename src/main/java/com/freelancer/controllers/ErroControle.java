package com.freelancer.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.freelancer.tratamentoErros.Resposta;

@ControllerAdvice
public class ErroControle {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Resposta> exeptionGenerica(Exception e) throws Exception{
		Resposta resposta = new Resposta();
		resposta.setDescricao("ERRO, tarefa não realizada");
		System.out.println(e);
		return new ResponseEntity<Resposta> (resposta, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Resposta> exeptionGenerica(DataIntegrityViolationException e) throws Exception{
		Resposta resposta = new Resposta();
		resposta.setDescricao("Usuario já existe");
		System.out.println(e);
		return new ResponseEntity<Resposta> (resposta, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
}
