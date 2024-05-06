package com.daninavarro.customer.customer.infraestructure.rest.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
//import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomerResponse {

  //@Schema(description = "Unique identifier of the customer.") -> TODO: swagger integration
  private String id;
  private String name;
  private String lastName;
  private String city;

}
