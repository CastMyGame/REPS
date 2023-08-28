package com.reps.dms.controller;

import com.reps.dms.model.student.Student;
import com.reps.dms.model.student.StudentRequest;
import com.reps.dms.model.student.StudentResponse;
import com.reps.dms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<?> getHome() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/studentid")
    public ResponseEntity<Student> getStudentByIdNumber(@RequestBody StudentRequest studentRequest) throws Exception {
       var message = studentService.requestStudentIdNumber(studentRequest.getStudent().getStudentIdNumber());

       return ResponseEntity
               .accepted()
               .body(message);
    }

    @GetMapping("/lastname")
    public ResponseEntity<List<Student>> getStudentByLastName (@RequestBody StudentRequest studentRequest) throws Exception {
        var message = studentService.requestStudentLastName(studentRequest);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/email")
    public ResponseEntity<Student> getStudentByEmail (@RequestBody StudentRequest studentRequest) throws Exception {
        var message = studentService.requestStudentEmail(studentRequest);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent (@RequestBody StudentRequest studentRequest) throws Exception {
        var delete = studentService.deleteStudent(studentRequest);
        return ResponseEntity
                .accepted()
                .body(delete);
    }
    
//    @PutMapping("/edit")
//    public ResponseEntity<Student> editInfraction (@RequestBody Student student) {
//        var edit = studentService.createNewStudent(student);
//        return ResponseEntity
//                .accepted()
//                .body(edit);
//    }

    @PostMapping("/newStudent")
    public ResponseEntity<StudentResponse> createStudent (@RequestBody StudentRequest studentRequest) {
        StudentResponse studentResponse = studentService.createNewStudent(studentRequest);
        return studentResponse.getStudent() == null
                ? new ResponseEntity<>(studentResponse, HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(studentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/allStudents")
    public ResponseEntity<List<Student>> findAllStudents () {
        var findAll = studentService.getAllStudents();

        return ResponseEntity
                .accepted()
                .body(findAll);
    }
}
