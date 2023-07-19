package com.dms.reps.model.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Students")
public class Student {
    @Id
    private String studentIdNumber;
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
    private ArrayList<String> studentPunishments;

    public Student(StudentRequest studentRequest) {
        this.studentIdNumber = studentRequest.getStudent().getStudentIdNumber();
        this.firstName = studentRequest.getStudent().getFirstName();
        this.lastName = studentRequest.getStudent().getLastName();
        this.parentEmail = studentRequest.getStudent().getParentEmail();
        this.studentEmail = studentRequest.getStudent().getStudentEmail();
        this.guidanceEmail = studentRequest.getStudent().getGuidanceEmail();
        this.adminEmail = studentRequest.getStudent().getAdminEmail();
        this.address = studentRequest.getStudent().getAddress();
        this.grade = studentRequest.getStudent().getGrade();
        this.parentPhoneNumber = studentRequest.getStudent().getParentPhoneNumber();
        this.studentPhoneNumber = studentRequest.getStudent().getStudentPhoneNumber();
        this.studentPunishments = studentRequest.getStudent().getStudentPunishments();
    }

    // if multiple levels are open, automatically email guidance/admin (2 in a day or > 3)
    // Track how many times they have had this opened,
}
