package com.dms.hims.controller;

import com.dms.hims.model.Student;
import com.dms.hims.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/student/v1")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/studentid")
    public ResponseEntity<Optional<Student>> getStudentById(@RequestBody Integer studentId) {
       var message = studentService.requestStudentId(studentId);

       return ResponseEntity
               .accepted()
               .body(message);
    }

    @GetMapping("/lastname")
    public ResponseEntity<Student> getStudentByLastName (@RequestBody String lastName) {
        var message = studentService.requestStudentLastName(lastName);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/email")
    public ResponseEntity<Student> getStudentByEmail (@RequestBody String email) {
        var message = studentService.requestStudentEmail(email);

        return ResponseEntity
                .accepted()
                .body(message);
    }
}
