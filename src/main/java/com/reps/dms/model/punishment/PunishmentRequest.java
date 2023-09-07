package com.reps.dms.model.punishment;

import com.reps.dms.model.infraction.Infraction;
import com.reps.dms.model.student.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PunishmentRequest {
    private Student student;
    private Infraction infraction;
    private String error;
}
