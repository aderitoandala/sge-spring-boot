package com.mz.sge.auth.exception;
import com.mz.sge.auth.dto.AuthErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import jakarta. servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages= "com.mz.sge.auth")
public class AuthExceptionHandler{

@ExceptionHandler(UserAlreadyExistsException.class)
public ResponseEntity<AuthErrorResponse> userAlreadyExistsExceptionHandler(UserAlreadyExistsException ex, HttpServletRequest request){

	log.warn("Tentativa de duplicar usuário.path:{}",request.getRequestURI(),ex);

	HttpStatus status= HttpStatus.CONFLICT;

	AuthErrorResponse response= new AuthErrorResponse(status.value(),status.getReasonPhrase(),ex.getMessage(),request.getServletPath());

	return ResponseEntity.status(status).body(response);
}



}
