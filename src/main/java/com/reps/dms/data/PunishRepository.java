package com.reps.dms.data;

import com.reps.dms.model.infraction.Infraction;
import com.reps.dms.model.punishment.Punishment;
import com.reps.dms.model.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PunishRepository extends JpaRepository<Punishment, String> {
    //May need PunishResponse if that gets made for record keeping instead
    List<Punishment> findByStudent (Student student);
    List<Punishment> findByInfraction (Infraction infraction);
    List<Punishment> findByStatus (String status);
    @Query(value = "SELECT * FROM punisher_list WHERE punishment_id = ?1", nativeQuery = true)
    Punishment findByPunishmentId (String punishId);
    @Query(value = "SELECT * FROM student_list WHERE student_email = ?1", nativeQuery = true)
    Student findByStudentEmail(String email);
    @Query(value = "SELECT * FROM punisher_list WHERE student_student_id = ?1 AND infraction_infraction_id = ?2 AND status='OPEN'", nativeQuery = true)
    Punishment findOpenByName(String email, String infractionName);
    }
