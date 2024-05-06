package com.daninavarro.customer.customer.applicartion.port.input;

import com.daninavarro.customer.customer.domain.entity.Customer;
import lombok.Builder;
import lombok.Data;

public interface GetCustomerByIdUseCase extends UseCase<GetCustomerByIdUseCase.InputValues, Customer> {

  @Data
  @Builder
  class InputValues {
    private final String customerId;
  }

}
