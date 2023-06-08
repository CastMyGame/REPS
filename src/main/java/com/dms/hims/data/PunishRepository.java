package com.dms.hims.data;

import com.dms.hims.event.PunishRequestCommand;
import com.dms.hims.model.Infraction;
import com.dms.hims.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PunishRepository extends MongoRepository<PunishRequestCommand, Integer> {
    //May need PunishResponse if that gets made for record keeping instead
    Optional<PunishRequestCommand> findByStudent (Student student);
    Optional<PunishRequestCommand> findByInfraction (Infraction infraction);
    Optional<PunishRequestCommand> findByStatus (String status);
    Optional<PunishRequestCommand> findByPunishId (Integer punishId);
}
