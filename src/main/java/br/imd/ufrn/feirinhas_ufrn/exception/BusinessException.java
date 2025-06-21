package br.imd.ufrn.feirinhas_ufrn.exception;

public class BusinessException extends Exception{
  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }  
}
