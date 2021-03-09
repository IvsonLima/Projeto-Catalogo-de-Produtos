package com.catalogodeprodutos.catalogodeprodutos.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class CatalogoExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String message = messageSource.getMessage("formato.invalido", null, LocaleContextHolder.getLocale());
		String status_code = "400"; 
		List<Erro> erros = Arrays.asList(new Erro(message, status_code));
		return handleExceptionInternal(ex, erros, headers,HttpStatus.BAD_REQUEST, request);

	}
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccessException (EmptyResultDataAccessException ex, WebRequest request) {
		String message = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String status_code = "404";
		List<Erro> erros = Arrays.asList(new Erro(message, status_code));
		return handleExceptionInternal (ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}
	
	public static class Erro {
		private String status_code;
		private String message;

		public Erro(String message, String status_code) {

			this.status_code = status_code;
			this.message = message;
		}

		public String getStatus_code() {
			return status_code;
		}

		public String getMessage() {
			return message;
		}



	}

}