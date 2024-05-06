package com.daninavarro.policy.infraestructure.database.mapper;

import com.daninavarro.policy.domain.entity.Policy;
import com.daninavarro.policy.infraestructure.database.document.PolicyDocument;
import com.daninavarro.policy.infraestructure.rest.dto.request.PolicyRequest;
import com.daninavarro.policy.infraestructure.rest.dto.response.PolicyResponse;
import java.util.UUID;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PolicyRepositoryMapper {

  Policy toDomain(PolicyDocument document);

  PolicyDocument toDocument(Policy domain);


}
