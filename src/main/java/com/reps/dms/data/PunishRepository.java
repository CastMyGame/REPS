package com.reps.dms.data;

import com.reps.dms.model.infraction.Infraction;
import com.reps.dms.model.punishment.Punishment;
import com.reps.dms.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PunishRepository extends JpaRepository<Punishment, String> {
    //May need PunishResponse if that gets made for record keeping instead
    List<Punishment> findByStudent (Student student);
    List<Punishment> findByInfraction (Infraction infraction);
    List<Punishment> findByStatus (String status);
    Punishment findByPunishmentId (String punishId);
}
