package com.dms.reps.controller;

import com.dms.reps.model.student.Student;
import com.dms.reps.model.student.StudentRequest;
import com.dms.reps.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/student/v1")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/studentid")
    public ResponseEntity<Optional<Student>> getStudentById(@RequestBody String studentId) {
       var message = studentService.requestStudentId(studentId);

       return ResponseEntity
               .accepted()
               .body(message);
    }

    @GetMapping("/lastname")
    public ResponseEntity<List<Student>> getStudentByLastName (@RequestBody String lastName) {
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

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent (@RequestBody Student student) {
        var delete = studentService.deleteStudent(student);
        return ResponseEntity
                .accepted()
                .body(delete);
    }

    @PutMapping("/edit")
    public ResponseEntity<Student> editInfraction (@RequestBody Student student) {
        var edit = studentService.createNewStudent(student);
        return ResponseEntity
                .accepted()
                .body(edit);
    }

    @PostMapping("/newStudent")
    public ResponseEntity<Student> createStudent (@RequestBody StudentRequest studentRequest) {
        var newStudent = studentService.createNewStudent(studentRequest.getStudent());
        return ResponseEntity
                .accepted()
                .body(newStudent);
    }
}
