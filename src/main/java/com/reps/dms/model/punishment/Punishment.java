package com.reps.dms.model.punishment;

import com.reps.dms.model.infraction.Infraction;
import com.reps.dms.model.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "punishment_list")
public class Punishment {

    @Id
    @Column(name = "punishment_id")
    private String punishmentId;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Infraction infraction;
    @Column(name = "status")
    private String status;
    @Column(name = "closed_times")
    private int closedTimes;
//    private int infractionTimes;
    @Column(name = "time_created")
    private String timeCreated;
    @Column (name = "class_period")
    private String classPeriod;
}
