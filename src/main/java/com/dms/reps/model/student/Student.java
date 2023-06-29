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
    private ArrayList<Integer> studentPunishments;

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getGuidanceEmail() {
        return guidanceEmail;
    }

    public void setGuidanceEmail(String guidanceEmail) {
        this.guidanceEmail = guidanceEmail;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getParentPhoneNumber() {
        return parentPhoneNumber;
    }

    public void setParentPhoneNumber(Integer parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }

    public Integer getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(Integer studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public ArrayList<Integer> getStudentPunishments() {
        return studentPunishments;
    }

    public void setStudentPunishments(ArrayList<Integer> studentPunishments) {
        this.studentPunishments = studentPunishments;
    }

    // if multiple levels are open, automatically email guidance/admin (2 in a day or > 3)
    // Track how many times they have had this opened,
}
