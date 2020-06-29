package com.eliminator.interceptor;

import com.eliminator.exception.AuthorizationFailureException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class SecurityInterceptor extends HandlerInterceptorAdapter {

  public static final String AUTHORIZATION = "authorization";
  public static final String TOKEN = "Token";

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof HandlerMethod){

      //Dummy Authorization Validation, Jwt can be used fot user authentication
      if(StringUtils.equals(request.getHeader(AUTHORIZATION), (TOKEN))){
        return Boolean.TRUE;
      }else{
        log.error("Authorization Failed");
        throw new AuthorizationFailureException("Invalid Token");
      }
    }
    return super.preHandle(request, response, handler);
  }
}
