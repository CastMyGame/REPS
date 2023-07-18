package com.dms.reps.service;

import com.dms.reps.data.StudentRepository;
import com.dms.reps.model.student.Student;
import com.dms.reps.model.student.StudentRequest;
import com.dms.reps.model.student.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;
    private final MongoTemplate mongoTemplate;

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

    public Optional<Student> requestStudentEmail(String email) {
        var findMe = studentRepository.findByStudentEmail(email);

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No student with that email exists");
        }

        return findMe;
    }

    public StudentResponse createNewStudent (StudentRequest studentRequest ) {
        try {
            return new StudentResponse("", studentRepository.save(studentRequest.getStudent()));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return new StudentResponse(e.getMessage(), null);
        }
    }

    public String deleteStudent ( Student student ) throws ResourceNotFoundException {
        try{studentRepository.delete(student);}
        catch (Exception e) {
            throw new ResourceNotFoundException("That infraction does not exist");
        } return "${infraction} has been deleted";
    }
}
