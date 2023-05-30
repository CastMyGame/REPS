package com.dms.hims.data;

import com.dms.hims.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {
    @Override
    Optional<Student> findById (Integer id);
    Student findByLastName(String lastName);

    Student findByStudentEmail(String email);
}
