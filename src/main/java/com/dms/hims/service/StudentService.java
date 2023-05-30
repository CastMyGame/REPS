package com.dms.hims.service;

import com.dms.hims.data.StudentRepository;
import com.dms.hims.model.Student;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> requestStudentId(Integer studentId) {
       var findMe = studentRepository.findById(studentId);

       if (findMe.isEmpty()) {
           throw new ResourceNotFoundException("No student with that Id exists");
       }
       logger.debug(String.valueOf(findMe));
       return findMe;
    }
    public Student requestStudentLastName(String lastName) {
        var findMe = studentRepository.findByLastName(lastName);

        if (findMe.getLastName().isEmpty()) {
            throw new ResourceNotFoundException("No student with that Last Name exists");
        }

        return findMe;
    }

    public Student requestStudentEmail(String email) {
        var findMe = studentRepository.findByStudentEmail(email);

        if (findMe.getStudentEmail().isEmpty()) {
            throw new ResourceNotFoundException("No student with that email exists");
        }

        return findMe;
    }

}
