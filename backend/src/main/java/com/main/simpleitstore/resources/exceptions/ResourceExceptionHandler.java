package com.main.simpleitstore.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.main.simpleitstore.services.exceptions.DatabaseException;
import com.main.simpleitstore.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler  {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest req) {
		StandardError err = new StandardError();
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		
		err.setTimestamp(Instant.now());
		err.setStatus(notFound.value());
		err.setError("Resource not found");
		err.setMsg(e.getMessage());
		err.setPath(req.getRequestURI());
		
		return ResponseEntity.status(notFound).body(err);
				
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest req) {
		StandardError err = new StandardError();
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		
		err.setTimestamp(Instant.now());
		err.setStatus(badRequest.value());
		err.setError("Database Exception");
		err.setMsg(e.getMessage());
		err.setPath(req.getRequestURI());
		return ResponseEntity.status(badRequest).body(err);
				
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validate(MethodArgumentNotValidException e, HttpServletRequest req) {
		ValidationError err = new ValidationError();
		HttpStatus badRequest = HttpStatus.UNPROCESSABLE_ENTITY; // 422 Não foi possível validar alguma entidade
		
		err.setTimestamp(Instant.now());
		err.setStatus(badRequest.value());
		err.setError("Validation Exception");
		err.setMsg(e.getMessage());
		err.setPath(req.getRequestURI());
		
		for(FieldError field : e.getBindingResult().getFieldErrors()) {
			err.addError(field.getField(), field.getDefaultMessage());
		}
		
		return ResponseEntity.status(badRequest).body(err);
				
	}
}
