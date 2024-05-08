package com.daninavarro.policy.infraestructure.rest.mapper;

import com.daninavarro.policy.domain.entity.Policy;
import com.daninavarro.policy.infraestructure.rest.dto.request.PolicyRequest;
import com.daninavarro.policy.infraestructure.rest.dto.response.PolicyResponse;
import java.util.UUID;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PolicyMapper {

  PolicyResponse toPolicyResponse(Policy domain);

  @Mapping(target = "id", expression = "java(computePolicyId())")
  @Mapping(target = "asyncSetup", constant = "false")
  Policy toPolicy(PolicyRequest dto);

  default UUID computePolicyId() {
    return UUID.randomUUID();
  }


}
