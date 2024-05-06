package com.daninavarro.policy.application.port.input.interactor;

import com.daninavarro.policy.application.port.input.StorePolicyUseCase;
import com.daninavarro.policy.domain.entity.Policy;
import com.daninavarro.policy.infraestructure.database.mapper.PolicyRepositoryMapper;
import com.daninavarro.policy.infraestructure.database.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorePolicyUseCaseImpl implements StorePolicyUseCase {

  private final PolicyRepository repository;
  private final PolicyRepositoryMapper mapper;

  @Override
  public Policy execute(Policy input) {
    return mapper.toDomain(repository.save(mapper.toDocument(input)));
  }
}
