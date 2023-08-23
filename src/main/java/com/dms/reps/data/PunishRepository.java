package com.dms.reps.data;

import com.dms.reps.model.infraction.Infraction;
import com.dms.reps.model.punishment.Punishment;
import com.dms.reps.model.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PunishRepository extends MongoRepository<Punishment, String> {
    //May need PunishResponse if that gets made for record keeping instead
    List<Punishment> findByStudent (Student student);
    List<Punishment> findByInfraction (Infraction infraction);
    List<Punishment> findByStatus (String status);
    Punishment findByPunishmentId (String punishId);
}
