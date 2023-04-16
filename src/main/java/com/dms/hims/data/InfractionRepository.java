package com.dms.hims.data;

import com.dms.hims.model.Infraction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InfractionRepository extends MongoRepository<Infraction, String> {
    Optional<Infraction> findByInfractionId(String infractionId);
    Optional<Infraction> findByStatus(String status);
    Optional<Infraction> findByInfractionLevel (int level);
    Optional<Infraction> findByInfractionType (String type);
}
