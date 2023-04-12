package com.dms.hims.controller;

import com.dms.hims.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/student/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;
}
