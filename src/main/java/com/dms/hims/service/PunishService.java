package com.dms.hims.service;

import com.dms.hims.data.PunishRepository;
import com.dms.hims.data.StudentRepository;
import com.dms.hims.event.PunishRequestCommand;
import com.dms.hims.model.Infraction;
import com.dms.hims.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PunishService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    PunishRepository punishRepository;
    @Autowired
    StudentRepository studentRepository;

    public PunishService(PunishRepository punishRepository, StudentRepository studentRepository) {
        this.punishRepository = punishRepository;
        this.studentRepository = studentRepository;
    }

    public Optional<PunishRequestCommand> findByStudent(PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByStudent(punishRequestCommand.getStudent());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("That student does not exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<PunishRequestCommand> findByInfraction(PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByInfraction(punishRequestCommand.getInfraction());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No students with that Infraction exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<PunishRequestCommand> findByStatus(PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByStatus(punishRequestCommand.getStatus());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No punishments with that status exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<PunishRequestCommand> findByPunishId(PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByPunishId(punishRequestCommand.getPunishId());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No punishments with that ID exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public PunishRequestCommand createNewPunish(PunishRequestCommand punishRequestCommand) {
            Optional<Student> badStudent = studentRepository.findById(punishRequestCommand.getStudent().getStudentIdNumber());
//        if (badStudent.isEmpty()) {
//            return exception;
            if (badStudent != null) {
                Student test = punishRequestCommand.getStudent();
                ArrayList<Integer> punishments = test.getStudentPunishments();
                punishments.add(punishRequestCommand.getPunishId());

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
