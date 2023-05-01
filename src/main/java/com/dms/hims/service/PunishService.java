package com.dms.hims.service;

import com.dms.hims.data.InfractionRepository;
import com.dms.hims.data.PunishRepository;
import com.dms.hims.data.StudentRepository;
import com.dms.hims.event.PunishRequestCommand;
import com.dms.hims.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PunishService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PunishRepository punishRepository;
    public PunishService (PunishRepository punishRepository) {
        this.punishRepository = punishRepository;
    }

    public Optional<PunishRequestCommand> findByStudent (PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByStudent(punishRequestCommand.getStudent());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("That student does not exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<PunishRequestCommand> findByInfraction (PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByInfraction(punishRequestCommand.getInfraction());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No students with that Infraction exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<PunishRequestCommand> findByStatus (PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByStatus(punishRequestCommand.getStatus());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No punishments with that status exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Optional<PunishRequestCommand> findByPunishId (PunishRequestCommand punishRequestCommand) {
        var findMe = punishRepository.findByPunishId(punishRequestCommand.getPunishId());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No punishments with that ID exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public PunishRequestCommand createNewPunish (PunishRequestCommand punishRequestCommand) {
        return punishRepository.save(punishRequestCommand);
    }
}
