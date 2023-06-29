package com.dms.reps.service;

import com.dms.reps.data.StudentRepository;
import com.dms.reps.model.student.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> requestStudentId(String studentId) {
       var findMe = studentRepository.findByStudentIdNumber(studentId);

       if (findMe.isEmpty()) {
           throw new ResourceNotFoundException("No student with that Id exists");
       }
       logger.debug(String.valueOf(findMe));
       return findMe;
    }
    public List<Student> requestStudentLastName(String lastName) {
        var findMe = studentRepository.findByLastName(lastName);

        if (findMe.isEmpty()) {
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

    public Student createNewStudent (Student student ) {
        return studentRepository.save(student);
    }

    public String deleteStudent ( Student student ) throws ResourceNotFoundException {
        try{studentRepository.delete(student);}
        catch (Exception e) {
            throw new ResourceNotFoundException("That infraction does not exist");
        } return "${infraction} has been deleted";
    }
}
