package com.dms.hims.data;

import com.dms.hims.model.Infraction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface InfractionRepository extends MongoRepository<Infraction, Integer> {
    Optional<Infraction> findByInfractionId(Integer infractionId);
    Optional<Infraction> findByInfractionType (String type);
    Optional<Infraction> findByInfractionName (String name);
}
