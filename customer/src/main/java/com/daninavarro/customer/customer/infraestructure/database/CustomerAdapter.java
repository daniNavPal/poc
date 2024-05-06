package com.daninavarro.customer.customer.infraestructure.database;

import com.daninavarro.customer.customer.applicartion.port.output.CustomerPort;
import com.daninavarro.customer.customer.domain.entity.Customer;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerPort {

  @Override
  public Optional<Customer> findById(String id) {
    return Optional.of(
        Customer.builder().id(id).name("customer_name").lastName("customer last name")
            .city("customer city").build());
  }
}
