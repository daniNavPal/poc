package com.daninavarro.policy.infraestructure.rest.controller;

import com.daninavarro.policy.domain.exception.PolicyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
// import io.swagger.v3.oas.annotations.Hidden;

//@Hidden # Swagger
@ControllerAdvice
@RequiredArgsConstructor
public class PolicyCoreControllerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler({
      IllegalArgumentException.class
  })
  public void internalBadRequest() {
  }


  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  @ExceptionHandler({
      PolicyNotFoundException.class
  })
  public void mainBrokerNotFoundException() {}
}
