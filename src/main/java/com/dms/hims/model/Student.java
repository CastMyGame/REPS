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
    private String parentEmail;
    private String studentEmail;
    private String guidanceEmail;
    private String adminEmail;
    private String address;
    private String grade;
    private Integer parentPhoneNumber;
    private Integer studentPhoneNumber;

    // if multiple levels are open, automatically email guidance/admin (2 in a day or > 3)
    // Track how many times they have had this opened,
}
