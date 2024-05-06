package com.daninavarro.policy.infraestructure.database.document;

import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("platform_policies")
public class PolicyDocument {

  @Id
  private UUID id;
  @Field(name = "policy_holder_id")
  private String policyHolderId;
  @Field(name = "product_name")
  private String productName;
  @Field(name = "premium")
  private Integer premium;
  @Field(name = "active")
  private Boolean active;
  @Field(name = "async_setup")
  private Boolean asyncSetup;
}
