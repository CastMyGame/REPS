package com.dms.hims.data;

import com.dms.hims.event.PunishRequestCommand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunishRepository extends MongoRepository<PunishRequestCommand, Integer> {
}
