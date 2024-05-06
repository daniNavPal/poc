package com.daninavarro.policy.infraestructure.kafka.mapper;

import com.daninavarro.policy.avro.PolicyAvro;
import com.daninavarro.policy.domain.entity.Policy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProcessPolicyMapper {

  PolicyAvro toCommand(Policy domain);

  Policy toDomain(PolicyAvro avro);

}
