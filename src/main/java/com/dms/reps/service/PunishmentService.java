package com.dms.reps.service;

import com.dms.reps.data.InfractionRepository;
import com.dms.reps.data.PunishRepository;
import com.dms.reps.data.StudentRepository;
import com.dms.reps.model.punishment.Punishment;
import com.dms.reps.model.punishment.PunishmentRequest;
import com.dms.reps.model.punishment.PunishmentResponse;
import com.dms.reps.model.student.Student;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PunishmentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;
    private final InfractionRepository infractionRepository;
    private final PunishRepository punishRepository;
    private final EmailService emailService;

    public static final String ACCOUNT_SID = "AC31fd459d82bd5d3ff135db0968b011d7";
    public static final String AUTH_TOKEN = "79a5f09dbb2b7d6d367c71715899a10e";

    public List<Punishment> findByStudent(PunishmentRequest punishmentRequest) {
        var findMe = punishRepository.findByStudent(punishmentRequest.getStudent());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("That student does not exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public List<Punishment> findAll() {
        return punishRepository.findAll();
    }

    public List<Punishment> findByInfraction(PunishmentRequest punishmentRequest) {
        var findMe = punishRepository.findByInfraction(punishmentRequest.getInfraction());

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No students with that Infraction exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public List<Punishment> findByStatus(String status) {
        var findMe = punishRepository.findByStatus(status);

        if (findMe.isEmpty()) {
            throw new ResourceNotFoundException("No punishments with that status exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Punishment findByPunishmentId(Punishment punishment) {
        var findMe = punishRepository.findByPunishmentId(punishment.getPunishmentId());

        if (findMe == null) {
            throw new ResourceNotFoundException("No punishments with that ID exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public PunishmentResponse createNewPunish(PunishmentRequest punishmentRequest) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("REP CREATED");

        Punishment punishment = new Punishment();
        punishment.setStudent(punishmentRequest.getStudent());
        punishment.setInfraction(punishmentRequest.getInfraction());
        punishment.setPunishmentId(UUID.randomUUID().toString());
//        punishment.setTimeCreated(now.toString());
        punishment.setStatus("OPEN");

        punishRepository.save(punishment);

        PunishmentResponse punishmentResponse = new PunishmentResponse();
        punishmentResponse.setPunishment(punishment);
        punishmentResponse.setMessage(" Hello," +
                " This is to inform you that " + punishment.getStudent().getFirstName() + " " + punishment.getStudent().getLastName() +
                " has received a REP for " + punishment.getInfraction().getInfractionName() + " and must complete "
        + punishment.getInfraction().getInfractionAssign() + ". If you have any questions you may contact the school's main office." +
                "This is an automated message DO NOT REPLY to this message.");
        punishmentResponse.setSubject("REP " + punishment.getPunishmentId() + " for " + punishment.getStudent().getFirstName() + " " + punishment.getStudent().getLastName());
        punishmentResponse.setToEmail(punishment.getStudent().getParentEmail());

        emailService.sendEmail(punishmentResponse.getToEmail(), punishmentResponse.getSubject(), punishmentResponse.getMessage());

        Message.creator(new PhoneNumber(punishmentResponse.getPunishment().getStudent().getParentPhoneNumber()),
                new PhoneNumber("+18437900073"), punishmentResponse.getMessage()).create();

        return punishmentResponse;
        }

    public String deletePunishment ( Punishment punishment ) throws ResourceNotFoundException {
        try{punishRepository.delete(punishment);}
        catch (Exception e) {
            throw new ResourceNotFoundException("That infraction does not exist");
        } return "Punishment has been deleted";
    }

    public PunishmentResponse closePunishment ( Punishment punishment ) throws  ResourceNotFoundException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        var findMe = punishRepository.findByPunishmentId(punishment.getPunishmentId());

        if (findMe != null) {
            PunishmentResponse punishmentResponse = new PunishmentResponse();
            punishmentResponse.setPunishment(findMe);
            punishmentResponse.setMessage(" Hello," +
                    " This is to inform you that " + findMe.getStudent().getFirstName() + " " + findMe.getStudent().getLastName() +
                    " has completed "
                    + findMe.getInfraction().getInfractionAssign() + " for " + findMe.getInfraction().getInfractionName() + ". If you have any questions you may contact the school's main office." +
                    "This is an automated message DO NOT REPLY to this message.");
            punishmentResponse.setSubject("REP " + findMe.getPunishmentId() + " for " + findMe.getStudent().getFirstName() + " " + findMe.getStudent().getLastName() + " CLOSED");
            punishmentResponse.setToEmail(punishment.getStudent().getParentEmail());

            emailService.sendEmail(punishmentResponse.getToEmail(), punishmentResponse.getSubject(), punishmentResponse.getMessage());

            Message.creator(new PhoneNumber(punishmentResponse.getPunishment().getStudent().getParentPhoneNumber()),
                    new PhoneNumber("+18437900073"), punishmentResponse.getMessage()).create();

//        int closedPunishments = punishment.getClosedInfraction();
//        punishment.setClosedInfraction(closedPunishments + 1);

        return punishmentResponse;}
        else {
            throw new ResourceNotFoundException("That infraction does not exist");
        }

    }
}
