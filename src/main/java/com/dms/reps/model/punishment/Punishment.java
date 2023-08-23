package com.dms.reps.model.punishment;

import com.dms.reps.model.infraction.Infraction;
import com.dms.reps.model.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Punishments")
public class Punishment {
    @Id
    private String id;
    private String punishmentId;
    private Student student;
    private Infraction infraction;
    private String status;
//    private int closedInfraction;
//    private int infractionTimes;
//    private String timeCreated;
//    private String classPeriod;
}
