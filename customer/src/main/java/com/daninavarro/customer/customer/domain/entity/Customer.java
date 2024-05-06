package com.daninavarro.customer.customer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Customer {

  private String id;
  private String name;
  private String lastName;
  private String city;

}
