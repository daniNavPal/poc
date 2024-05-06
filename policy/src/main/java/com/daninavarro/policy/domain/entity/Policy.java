package com.daninavarro.policy.domain.entity;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Policy {

  private UUID id;
  private String policyHolderId;
  private String productName;
  private Integer premium;
  private Boolean active;
  private Boolean asyncSetup;

}
