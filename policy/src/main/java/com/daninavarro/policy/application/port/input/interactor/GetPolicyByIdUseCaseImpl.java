package com.daninavarro.policy.application.port.input.interactor;

import com.daninavarro.policy.application.port.input.GetPolicyByIdUseCase;
import com.daninavarro.policy.application.port.input.StorePolicyUseCase;
import com.daninavarro.policy.domain.entity.Policy;
import com.daninavarro.policy.domain.exception.PolicyNotFoundException;
import com.daninavarro.policy.infraestructure.database.mapper.PolicyRepositoryMapper;
import com.daninavarro.policy.infraestructure.database.repository.PolicyRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPolicyByIdUseCaseImpl implements GetPolicyByIdUseCase {

  private final PolicyRepository repository;
  private final PolicyRepositoryMapper mapper;

  @Override
  public Policy execute(String input) {
    return mapper.toDomain(repository.findById(UUID.fromString(input)).orElseThrow(
        PolicyNotFoundException::new));
  }
}
