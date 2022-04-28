package com.suportehe.exception;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.suportehe.dto.ObjetoErroDTO;

@RestControllerAdvice
@ControllerAdvice
public class ControllerException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ExceptionSistemaHepp.class)
	public ResponseEntity<Object> handleExceptionCustom(ExceptionSistemaHepp ex) {
		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
		
		objetoErroDTO.setError(ex.getMessage());
		objetoErroDTO.setCode(HttpStatus.OK.toString());
		
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.OK);
	}

	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
		
		String msg = "";
		
		if (ex instanceof MethodArgumentNotValidException) {
			
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			
			for (ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() + "\n";
			}
		} else if(ex instanceof HttpMessageNotReadableException) {
			msg = "Cadastro está vazio, preencha os dados e complete o cadastro!";
		} else {
			msg = "Erro no servidor" + ex.getMessage();
		}
		
		objetoErroDTO.setError(msg);
		objetoErroDTO.setCode(status.value() + " ==>" + status.getReasonPhrase());
		
		ex.printStackTrace();
		
		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class, ConstraintViolationException.class,
			SQLIntegrityConstraintViolationException.class })
	protected ResponseEntity<Object> handlerExceptionDataIntegry(Exception ex) {

		ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();

		String msg = "";

		if (ex instanceof DataIntegrityViolationException) {
			msg = "Erro de integridade no banco: "
					+ ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof ConstraintViolationException) {
			msg = "Erro de Chave estrangeira no banco: "
					+ ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		} else {
			if (ex instanceof SQLException) {
				msg = "Erro de SQL no banco: " + ((SQLException) ex).getCause().getCause().getMessage();
			} else {
				ex.getMessage();
			}
		}

		objetoErroDTO.setError(msg);
		objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

		return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
