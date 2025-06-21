package br.imd.ufrn.feirinhas_ufrn.exception;

import java.nio.file.AccessDeniedException;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class DefaultExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiError> businessExceptionHandler(BusinessException exception, HttpServletRequest httpRequest) {
    final ApiError error = new ApiError(
      httpRequest.getRequestURI(),
      HttpStatus.BAD_REQUEST.value(),
      exception.getMessage(),
      OffsetDateTime.now()
    );

    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(AuthFailException.class)
  public ResponseEntity<ApiError> authFailHandler(AuthFailException exception, HttpServletRequest httpRequest) {
    final ApiError error = new ApiError(
        httpRequest.getRequestURI(),
        HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(),
        OffsetDateTime.now());

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);    
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiError> permissionDeniedError(AccessDeniedException exception, HttpServletRequest httpRequest) {
    final ApiError error = new ApiError(
        httpRequest.getRequestURI(),
        HttpStatus.FORBIDDEN.value(),
        exception.getMessage(),
        OffsetDateTime.now());

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);    
  }  

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> validationBodyHandler(MethodArgumentNotValidException exception,
      HttpServletRequest httpRequest) {
    // Extrai apenas as mensagens de erro padrão de cada campo
    final String errors = exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getDefaultMessage())
        .collect(Collectors.joining(" | "));

    final ApiError error = new ApiError(
        httpRequest.getRequestURI(),
        HttpStatus.BAD_REQUEST.value(),
        errors,
        OffsetDateTime.now());

    return ResponseEntity.badRequest().body(error);
  } 
  
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ApiError> validationPathOrParamHandler(ConstraintViolationException exception,
      HttpServletRequest httpRequest) {
    final ApiError error = new ApiError(
        httpRequest.getRequestURI(),
        HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(),
        OffsetDateTime.now());

    return ResponseEntity.badRequest().body(error);
  }  

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiError> runtimeExceptionHandler(RuntimeException exception,
      HttpServletRequest httpRequest) {
    final ApiError error = new ApiError(
        httpRequest.getRequestURI(),
        HttpStatus.BAD_REQUEST.value(),
        exception.getMessage(),
        OffsetDateTime.now());

    return ResponseEntity.internalServerError().body(error);
  }  

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> genericExceptionHandler(Exception exception,
      HttpServletRequest httpRequest) {
    final ApiError error = new ApiError(
        httpRequest.getRequestURI(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        exception.getMessage(),
        OffsetDateTime.now());

    return ResponseEntity.internalServerError().body(error);
  }  

}
