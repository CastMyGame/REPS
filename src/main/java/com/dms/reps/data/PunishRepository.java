package com.dms.reps.data;

import com.dms.reps.model.infraction.Infraction;
import com.dms.reps.model.punishment.Punishment;
import com.dms.reps.model.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PunishRepository extends MongoRepository<Punishment, String> {
    //May need PunishResponse if that gets made for record keeping instead
    Optional<Punishment> findByStudent (Student student);
    Optional<Punishment> findByInfraction (Infraction infraction);
    Optional<Punishment> findByStatus (String status);
    Optional<Punishment> findByPunishId (Integer punishId);
}
