package com.daninavarro.customer.customer.applicartion.port.output;

import com.daninavarro.customer.customer.domain.entity.Customer;
import java.util.Optional;
import java.util.UUID;

public interface CustomerPort {

  Optional<Customer> findById(String id);

}
