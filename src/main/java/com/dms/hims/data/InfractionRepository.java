package com.dms.hims.data;

import com.dms.hims.model.Infraction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface InfractionRepository extends MongoRepository<Infraction, Integer> {
    Optional<Infraction> findByInfractionId(Integer infractionId);
    Optional<Infraction> findByInfractionType (String type);
    Optional<Infraction> findByInfractionName (String infractionName);
}
