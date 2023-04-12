package com.dms.hims.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Students")
public class Student {
    @Id
    private Integer studentIdNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String grade;
    private Integer phoneNumber;
    private String level;
}
