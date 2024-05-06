package com.daninavarro.policy.infraestructure.kafka.subscriber;

import com.daninavarro.policy.application.port.input.StorePolicyUseCase;
import com.daninavarro.policy.avro.PolicyAvro;
import com.daninavarro.policy.infraestructure.kafka.mapper.ProcessPolicyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PolicyRequestSubscriber {

  private final ProcessPolicyMapper mapper;
  private final StorePolicyUseCase storePolicyUseCase;

  @KafkaListener(
      topics = "${spring.kafka.consumer.topics.process-policy-request-v1.name}",
      concurrency = "${spring.kafka.consumer.topics.process-policy-request-v1.concurrency}")
  public void consume(final ConsumerRecord<String, PolicyAvro> message) {
    log.info(
        "PolicyRequestSubscriber#consumer, key={}, value={}",
        message.key(),
        message.value());
    storePolicyUseCase.execute(mapper.toDomain(message.value()));
  }


}
