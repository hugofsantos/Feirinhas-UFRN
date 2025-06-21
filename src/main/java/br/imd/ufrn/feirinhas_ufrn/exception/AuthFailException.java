package br.imd.ufrn.feirinhas_ufrn.exception;

public class AuthFailException extends Exception{
  public AuthFailException(String message) {
    super(message);
  }

  public AuthFailException(String message, Throwable cause) {
    super(message, cause);
  }  
}
