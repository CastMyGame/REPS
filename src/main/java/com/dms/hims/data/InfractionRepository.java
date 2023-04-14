package com.dms.hims.data;

import com.dms.hims.model.Infraction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface InfractionRepository extends CrudRepository<Infraction, String> {
}
