package com.freelancer.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.freelancer.tratamentoErros.Resposta;

@ControllerAdvice
public class ExceptionControle {
	//CONTROLA TODAS AS EXCPTIONS
	
	
	//CONTROLA AS EXCEPTIONS NÃO TRATADAS
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Resposta> exceptionGenerica(Exception exception) throws Exception{
		Resposta resposta = new Resposta();
		resposta.setDescricao("ERRO, tarefa não realizada");
		System.out.println(exception);
		return new ResponseEntity<Resposta> (resposta, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//CONTROLA EXCEPTIONS DO TIPO DataIntegrityViolationException (violação de primarykey and others)
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Resposta> violationSqlException(DataIntegrityViolationException exception) throws Exception{
		Resposta resposta = new Resposta();
		resposta.setDescricao("Registro já existe");
		System.out.println(exception);
		return new ResponseEntity<Resposta> (resposta, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	//CONTROLA EXCEPTIONS DO TIPO EmptyResultDataAccessException, registros inexistentes.
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Resposta> registroInexistente(EmptyResultDataAccessException exception) throws  Exception{
		Resposta resposta = new Resposta();
		resposta.setDescricao("Registro ou parametros inexistentes/incorretos");
		System.out.println(exception);
		return new ResponseEntity<Resposta> (resposta, HttpStatus.BAD_REQUEST);
	}
	
	////CONTROLA EXCEPTIONS DO TIPO HttpRequestMethodNotSupportedException
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Resposta> metodoRequisicaoNaoSuportado(HttpRequestMethodNotSupportedException exception) throws  Exception{
		Resposta resposta = new Resposta();
		resposta.setDescricao("Metodo de requisição não suportado");
		System.out.println(exception);
		return new ResponseEntity<Resposta> (resposta, HttpStatus.BAD_REQUEST);
	}		
}
