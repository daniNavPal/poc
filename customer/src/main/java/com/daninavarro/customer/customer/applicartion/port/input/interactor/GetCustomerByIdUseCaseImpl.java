package com.daninavarro.customer.customer.applicartion.port.input.interactor;

import com.daninavarro.customer.customer.applicartion.port.input.GetCustomerByIdUseCase;
import com.daninavarro.customer.customer.applicartion.port.input.exception.CustomerNotFoundException;
import com.daninavarro.customer.customer.applicartion.port.output.CustomerPort;
import com.daninavarro.customer.customer.domain.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCustomerByIdUseCaseImpl implements GetCustomerByIdUseCase {

  private final CustomerPort customerPort;

  @Override
  public Customer execute(InputValues input) {
    return customerPort.findById(input.getCustomerId()).orElseThrow(CustomerNotFoundException::new);
  }
}
