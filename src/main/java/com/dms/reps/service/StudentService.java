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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public Optional<Student> requestStudentIdNumber(String studentId) {
       var findMe = studentRepository.findByStudentIdNumber(studentId);

       if (findMe.isEmpty()) {
           throw new ResourceNotFoundException("No student with that Id exists");
       }
       logger.debug(String.valueOf(findMe));
       return findMe;
    }
    public List<Student> requestStudentLastName(StudentRequest studentRequest) {
        var findMe = studentRepository.findByLastName(studentRequest.getStudent().getLastName());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No student with that Last Name exists");
        }

        return findMe;
    }

    public Optional<Student> requestStudentEmail(StudentRequest studentRequest) {
        var findMe = studentRepository.findByStudentEmail(studentRequest.getStudent().getStudentEmail());

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

    public String deleteStudent ( StudentRequest studentRequest ) throws ResourceNotFoundException {
        try{
            System.out.println(studentRequest.getStudent());
            studentRepository.delete(studentRequest.getStudent());}
        catch (Exception e) {
            throw new ResourceNotFoundException("That student does not exist");
        } return new StringBuilder().append(studentRequest.getStudent().getFirstName())
                .append(" ")
                .append(studentRequest.getStudent().getLastName())
                .append(" has been deleted")
                .toString();
    }

    private Student ensureStudentExists(Student student) {
        Query query = new Query();
        query.addCriteria(
                new Criteria()
                        .andOperator(Criteria.where("Student")
                                .elemMatch(Criteria.where("studentIdNumber").is(student.getStudentIdNumber())),
                                Criteria.where("lastName").is(student.getLastName())));
        Student findMe = mongoTemplate.findOne(query, Student.class);
        if (findMe != null) {
            return findMe;
        } else {
            return null;
        }
    }
}
