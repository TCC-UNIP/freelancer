package com.freelancer.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
		resposta.setDescricao("Registro já existe");
		System.out.println(e);
		return new ResponseEntity<Resposta> (resposta, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Resposta> registroInexistente(EmptyResultDataAccessException e) throws  Exception{
		Resposta resposta = new Resposta();
		resposta.setDescricao("Registro ou parametros inexistentes/incorretos");
		return new ResponseEntity<Resposta> (resposta, HttpStatus.BAD_REQUEST);
	}
	
}