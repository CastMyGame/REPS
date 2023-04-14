package com.dms.hims.controller;

import com.dms.hims.model.Student;
import com.dms.hims.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/student/v1")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/studentid")
    public ResponseEntity<Optional<Student>> index(@RequestBody Integer studentId) {
       var message = studentService.requestStudentId(studentId);

       return ResponseEntity
               .accepted()
               .body(message);
    }
}
