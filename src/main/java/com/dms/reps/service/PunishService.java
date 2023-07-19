package com.dms.reps.service;

import com.dms.reps.data.InfractionRepository;
import com.dms.reps.data.PunishRepository;
import com.dms.reps.data.StudentRepository;
import com.dms.reps.model.punishment.Punishment;
import com.dms.reps.model.punishment.PunishmentRequest;
import com.dms.reps.model.student.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PunishService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;
    private final InfractionRepository infractionRepository;
    private final PunishRepository punishRepository;

    public PunishService(PunishRepository punishRepository, StudentRepository studentRepository, InfractionRepository infractionRepository) {
        this.punishRepository = punishRepository;
        this.studentRepository = studentRepository;
        this.infractionRepository = infractionRepository;
    }

    public Optional<Punishment> findByStudent(PunishmentRequest punishmentRequest) {
        var findMe = punishRepository.findByStudent(punishmentRequest.getPunishment().getStudent());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("That student does not exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<Punishment> findByInfraction(PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByInfraction(punishRequestCommand.getInfraction());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No students with that Infraction exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<Punishment> findByStatus(PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByStatus(punishRequestCommand.getStatus());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No punishments with that status exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<Punishment> findByPunishId(PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByPunishId(punishRequestCommand.getPunishId());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No punishments with that ID exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Punishment createNewPunish(PunishmentRequest punishmentRequest) {
            Optional<Student> badStudent = studentRepository.findByStudentIdNumber(punishmentRequest.getStudent().getStudentIdNumber());
//        if (badStudent.isEmpty()) {
//            return exception;
            if (badStudent != null) {
                Student test = punishmentRequest.getStudent();
                ArrayList<String> punishments = test.getStudentPunishments();
                punishments.add(punishmentRequest.getPunishId());

            }
            return punishRepository.save(punishRequestCommand);
        }

    public String deletePunishment ( PunishRequestCommand requestCommand ) throws ResourceNotFoundException {
        try{punishRepository.delete(requestCommand);}
        catch (Exception e) {
            throw new ResourceNotFoundException("That infraction does not exist");
        } return "${infraction} has been deleted";
    }
    }
