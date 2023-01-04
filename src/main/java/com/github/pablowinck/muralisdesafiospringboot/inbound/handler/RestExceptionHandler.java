package com.github.pablowinck.muralisdesafiospringboot.inbound.handler;

import com.github.pablowinck.muralisdesafiospringboot.core.domain.exception.DomainException;
import com.github.pablowinck.muralisdesafiospringboot.inbound.handler.dto.ResponseWithMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

	private final MessageSource messageSource;

	public RestExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ResponseWithMessage> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex) {
		String message = "O campo " + ex.getParameterName() + " é obrigatório.";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseWithMessage.builder().mensagem(message).build());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ResponseWithMessage> handleMessageNotReadable() {
		String message = "O corpo da requisição não pôde ser lido.";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseWithMessage.builder().mensagem(message).build());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<ResponseWithMessage> handleNoHandlerFoundException() {
		String message = "Recurso não encontrado.";
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ResponseWithMessage.builder().mensagem(message).build());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<ResponseWithMessage> handleHttpRequestMethodNotSupported(
			) {
		String message = "O método HTTP na solicitação não é permitido no resurso.";
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(ResponseWithMessage.builder().mensagem(message).build());
	}

	@ExceptionHandler(DomainException.class)
	protected ResponseEntity<ResponseWithMessage> handleDomainException(DomainException ex) {
		log.error(ex.getMessage(), ex);
		return ResponseEntity.status(ex.getStatus())
				.body(ResponseWithMessage.builder().mensagem(ex.getMessage()).codigo(ex.getCode()).build());
	}

	@ExceptionHandler
	protected ResponseEntity<ResponseWithMessage> handleAnything(Exception ex) {
		String message = "Ocorreu um erro ao tentar processar sua solicitação.";
		log.error(message, ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ResponseWithMessage.builder().mensagem(message).build());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ResponseWithMessage> handleAnything(MethodArgumentNotValidException ex) {
		log.info(ex.getMessage());
		FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
		String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResponseWithMessage.builder().codigo("Campo invalido").mensagem(message).build());
	}

}
