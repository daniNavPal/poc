package com.daninavarro.policy.infraestructure.rest.controller;

import com.daninavarro.policy.infraestructure.kafka.publisher.PolicyPublisher;
import com.daninavarro.policy.infraestructure.rest.dto.request.PolicyRequest;
import com.daninavarro.policy.infraestructure.rest.dto.response.PolicyResponse;
import com.daninavarro.policy.infraestructure.rest.mapper.PolicyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/policies")
@RequiredArgsConstructor
@Slf4j
public class PolicyController {

  private final PolicyMapper mapper;
  @Autowired
  private PolicyPublisher kafkaPublisher;

  @PostMapping("/async")
  public ResponseEntity<PolicyResponse> publish(@RequestBody PolicyRequest policyRequest) {
    var policy = mapper.toPolicy(policyRequest);
    kafkaPublisher.publishMessage(policy.getId().toString(), policy);
    log.info(String.format("Policy Async creation with ID %s", policy.getId().toString()));
    return ResponseEntity.ok(mapper.toPolicyResponse(policy));
  }

}
