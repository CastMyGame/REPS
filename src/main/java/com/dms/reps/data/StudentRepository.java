package com.dms.reps.data;

import com.dms.reps.model.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findByStudentIdNumber (String id);
    List<Student> findByLastName(String lastName);
    Optional<Student> findByStudentEmail(String email);
}
