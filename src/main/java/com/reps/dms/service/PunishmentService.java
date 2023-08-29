package com.reps.dms.service;

import com.reps.dms.data.InfractionRepository;
import com.reps.dms.data.PunishRepository;
import com.reps.dms.data.StudentRepository;
import com.reps.dms.model.punishment.Punishment;
import com.reps.dms.model.punishment.PunishmentRequest;
import com.reps.dms.model.punishment.PunishmentResponse;
import com.reps.dms.model.student.Student;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    public static final String AUTH_TOKEN = "e39f18b47fb8274bfe7b22dc11d3779f";

    public List<Punishment> findByStudent(PunishmentRequest punishmentRequest) throws Exception {
        var findMe = punishRepository.findByStudent(punishmentRequest.getStudent());

        if (findMe.isEmpty()) {
            throw new Exception("That student does not exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public List<Punishment> findAll() {
        return punishRepository.findAll();
    }

    public List<Punishment> findByInfraction(PunishmentRequest punishmentRequest) throws Exception {
        var findMe = punishRepository.findByInfraction(punishmentRequest.getInfraction());

        if (findMe.isEmpty()) {
            throw new Exception("No students with that Infraction exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public List<Punishment> findByStatus(String status) throws Exception {
        var findMe = punishRepository.findByStatus(status);

        if (findMe.isEmpty()) {
            throw new Exception("No punishments with that status exist");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Punishment findByPunishmentId(Punishment punishment) throws Exception {
        var findMe = punishRepository.findByPunishmentId(punishment.getPunishmentId());

        if (findMe == null) {
            throw new Exception("No punishments with that ID exist");
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
        + punishment.getInfraction().getAssignment() + ". If you have any questions you may contact the school's main office." +
                "This is an automated message DO NOT REPLY to this message.");
        punishmentResponse.setSubject("REP " + punishment.getPunishmentId() + " for " + punishment.getStudent().getFirstName() + " " + punishment.getStudent().getLastName());
        punishmentResponse.setToEmail(punishment.getStudent().getParentEmail());

        emailService.sendEmail(punishmentResponse.getToEmail(), punishmentResponse.getSubject(), punishmentResponse.getMessage());

        Message.creator(new PhoneNumber(punishmentResponse.getPunishment().getStudent().getParentPhoneNumber()),
                new PhoneNumber("+18437900073"), punishmentResponse.getMessage()).create();

        return punishmentResponse;
        }

    public String deletePunishment ( Punishment punishment ) throws Exception {
        try{punishRepository.delete(punishment);}
        catch (Exception e) {
            throw new Exception("That infraction does not exist");
        } return "Punishment has been deleted";
    }

    public PunishmentResponse closePunishment ( Punishment punishment ) throws  Exception {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        var findMe = punishRepository.findByPunishmentId(punishment.getPunishmentId());

        if (findMe != null) {
            PunishmentResponse punishmentResponse = new PunishmentResponse();
            punishmentResponse.setPunishment(findMe);
            findMe.setStatus("CLOSED");
            punishmentResponse.setMessage(" Hello," +
                    " This is to inform you that " + findMe.getStudent().getFirstName() + " " + findMe.getStudent().getLastName() +
                    " has completed "
                    + findMe.getInfraction().getAssignment() + " for " + findMe.getInfraction().getInfractionName() + ". If you have any questions you may contact the school's main office." +
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
            throw new Exception("That infraction does not exist");
        }

    }
}
