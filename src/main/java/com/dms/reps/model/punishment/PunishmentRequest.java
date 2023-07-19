package com.dms.reps.model.punishment;

import com.dms.reps.model.infraction.Infraction;
import com.dms.reps.model.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PunishmentRequest {
    private Student student;
    private Infraction infraction;
    private String error;
}
