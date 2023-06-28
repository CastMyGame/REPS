package com.dms.reps.model.student;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private Student student;
    private String error;

}
