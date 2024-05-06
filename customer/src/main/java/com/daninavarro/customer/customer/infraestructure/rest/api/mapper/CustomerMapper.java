package com.daninavarro.customer.customer.infraestructure.rest.api.mapper;

import com.daninavarro.customer.customer.domain.entity.Customer;
import com.daninavarro.customer.customer.infraestructure.rest.api.dto.response.CustomerResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

  CustomerResponse toCustomerResponse(Customer domain);

}
