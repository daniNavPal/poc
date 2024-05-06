package com.daninavarro.customer.customer.infraestructure.rest.api.controller;

import com.daninavarro.customer.customer.applicartion.port.input.GetCustomerByIdUseCase;
import com.daninavarro.customer.customer.applicartion.port.input.GetCustomerByIdUseCase.InputValues;
import com.daninavarro.customer.customer.domain.entity.Customer;
import com.daninavarro.customer.customer.infraestructure.rest.api.dto.response.CustomerResponse;
import com.daninavarro.customer.customer.infraestructure.rest.api.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

  private final GetCustomerByIdUseCase getCustomerByIdUseCase;
  private final CustomerMapper customerMapper;

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> getById(@PathVariable("id") String id)
      throws InterruptedException {
    var customerDtoResponse = customerMapper.toCustomerResponse(
        getCustomerByIdUseCase.execute(InputValues.builder().customerId(id).build()));
    return new ResponseEntity<>(customerDtoResponse, HttpStatus.OK);
  }

}
