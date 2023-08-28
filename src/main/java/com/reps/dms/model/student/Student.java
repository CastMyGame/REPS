package com.reps.dms.model.student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_list")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentIdNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "parent_email")
    private String parentEmail;
    @Column(name = "student_email")
    private String studentEmail;
    @Column(name = "guidance_email")
    private String guidanceEmail;
    @Column(name = "admin_email")
    private String adminEmail;
    @Column(name = "address")
    private String address;
    @Column(name = "grade")
    private String grade;
    @Column(name = "parent_phone")
    private String parentPhoneNumber;
    @Column(name = "student_phone")
    private String studentPhoneNumber;

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
    }
    // if multiple levels are open, automatically email guidance/admin (2 in a day or > 3)
    // Track how many times they have had this opened,
}
