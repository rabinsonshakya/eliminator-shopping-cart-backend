package com.eliminator.exception;

public class AuthorizationFailureException extends RuntimeException {
  public AuthorizationFailureException() {

  }

  public AuthorizationFailureException(String message) {
    super(message);
  }
}
