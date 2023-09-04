package com.reps.dms.service;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.reps.dms.data.StudentRepository;
import com.reps.dms.model.student.Student;
import com.reps.dms.model.student.StudentRequest;
import com.reps.dms.model.student.StudentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
//    String keyVaultName = "repskv";
//    String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";
//
//    SecretClient secretClient = new SecretClientBuilder()
//            .vaultUrl(keyVaultUri)
//            .credential(new DefaultAzureCredentialBuilder().build())
//            .buildClient();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;

    public Student requestStudentIdNumber(String studentId) throws Exception {
       var findMe = studentRepository.findByStudentIdNumber(studentId);

       if (findMe == null) {
           throw new Exception("No student with that Id exists");
       }
       logger.debug(String.valueOf(findMe));
       return findMe;
    }
    public List<Student> requestStudentLastName(StudentRequest studentRequest) throws Exception {
        var findMe = studentRepository.findByLastName(studentRequest.getStudent().getLastName());
//        System.out.println(secretClient.getSecret("TWILIO-ACCOUNT-SID"));  Not working because it isn't even accessing it

        if (findMe.isEmpty()) {
            throw new Exception("No student with that Last Name exists");
        }

        return findMe;
    }

    public Student requestStudentEmail(StudentRequest studentRequest) throws Exception {
        var findMe = studentRepository.findByStudentEmail(studentRequest.getStudent().getStudentEmail());

        if (findMe == null) {
            throw new Exception("No student with that email exists");
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

    public String deleteStudent ( StudentRequest studentRequest ) throws Exception {
        try{
            System.out.println(studentRequest.getStudent());
            studentRepository.delete(studentRequest.getStudent());}
        catch (Exception e) {
            throw new Exception("That student does not exist");
        } return new StringBuilder().append(studentRequest.getStudent().getFirstName())
                .append(" ")
                .append(studentRequest.getStudent().getLastName())
                .append(" has been deleted")
                .toString();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    private Student ensureStudentExists(Student student) {
    return null;
        }
    }
