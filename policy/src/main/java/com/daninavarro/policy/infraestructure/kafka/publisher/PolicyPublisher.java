package com.daninavarro.policy.infraestructure.kafka.publisher;

import com.daninavarro.policy.avro.PolicyAvro;
import com.daninavarro.policy.domain.entity.Policy;
import com.daninavarro.policy.infraestructure.kafka.mapper.ProcessPolicyMapper;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PolicyPublisher {

  @Value("${spring.kafka.producer.process-policy-request-v1}")
  private String topic;

  private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
  private static final String TIMESTAMP_HEADER = "x-timestamp";

  private final KafkaTemplate<String, PolicyAvro> kafkaTemplate;
  private final ProcessPolicyMapper mapper;

  public void publishMessage(final String key, final Policy event) {
    log.info(String.format("Message sent -> %s to topic -> %s", event.toString(), topic));

    final ProducerRecord<String, PolicyAvro> producerRecord = new ProducerRecord<>(topic, key, mapper.toCommand(event));

    producerRecord.headers()
        .add(TIMESTAMP_HEADER, DATE_FORMAT.format(OffsetDateTime.now()).getBytes());

    kafkaTemplate.send(producerRecord);
  }
}
