package com.eliminator.api;

import com.eliminator.exception.AuthorizationFailureException;
import com.eliminator.model.ErrorrRS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ResponseControllerAdvice {

  public static final String ERR_01 = "ERR01";
  private static final String ERR_02 = "ERR02";

  @ExceptionHandler(AuthorizationFailureException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorrRS handleAuthorizationFailureException(){
    ErrorrRS errorrRS = new ErrorrRS(ERR_01, "Authentication Failed");
    return errorrRS;
  }

  @ExceptionHandler(Throwable.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorrRS handleAnyThrowable(){
    ErrorrRS errorrRS = new ErrorrRS(ERR_02, "Server Side Error");
    return errorrRS;
  }
}
