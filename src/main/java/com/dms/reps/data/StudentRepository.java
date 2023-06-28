package com.dms.reps.data;

import com.dms.reps.model.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {
    @Override
    Optional<Student> findById (Integer id);
    Student findByLastName(String lastName);
    @Query(value="{category:'?0'}", fields="{'First Name' : 1, 'Last Name' : 1, '}")
    List<Student> findAll(String category);
    Student findByStudentEmail(String email);
}
