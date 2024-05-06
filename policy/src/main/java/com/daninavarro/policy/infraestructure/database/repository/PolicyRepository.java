package com.daninavarro.policy.infraestructure.database.repository;

import com.daninavarro.policy.infraestructure.database.document.PolicyDocument;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PolicyRepository extends MongoRepository<PolicyDocument, UUID> {

}
