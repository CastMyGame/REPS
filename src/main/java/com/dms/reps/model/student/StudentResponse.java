package com.dms.reps.model.student;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResponse {
    private String error;
    private Student student;

}
