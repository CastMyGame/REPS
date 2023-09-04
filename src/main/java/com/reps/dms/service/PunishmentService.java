package com.reps.dms.service;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.reps.dms.data.InfractionRepository;
import com.reps.dms.data.PunishRepository;
import com.reps.dms.data.StudentRepository;
import com.reps.dms.model.infraction.Infraction;
import com.reps.dms.model.punishment.Punishment;
import com.reps.dms.model.punishment.PunishmentFormRequest;
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
//    String keyVaultName = "repskv";
//    String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";
//
//    SecretClient secretClient = new SecretClientBuilder()
//            .vaultUrl(keyVaultUri)
//            .credential(new DefaultAzureCredentialBuilder().build())
//            .buildClient();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StudentRepository studentRepository;
    private final InfractionRepository infractionRepository;
    private final PunishRepository punishRepository;
    private final EmailService emailService;

    public List<Punishment> findByStudent(PunishmentRequest punishmentRequest) throws Exception {
        Punishment punishment = punishmentRequest.getPunishment();
        var findMe = punishRepository.findByStudent(punishment.getStudent());

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
        Punishment punishment = punishmentRequest.getPunishment();
        var findMe = punishRepository.findByInfraction(punishment.getInfraction());

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
//        Twilio.init(secretClient.getSecret("TWILIO-ACCOUNT-SID").toString(), secretClient.getSecret("TWILIO-AUTH-TOKEN").toString());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("REP CREATED");

        Punishment punishment = punishmentRequest.getPunishment();
        punishment.setPunishmentId(UUID.randomUUID().toString());
        punishment.setTimeCreated(now);
        punishment.setStatus("OPEN");

        System.out.println(punishment);

        punishRepository.save(punishment);

        PunishmentResponse punishmentResponse = new PunishmentResponse();
        punishmentResponse.setPunishment(punishment);
//        punishmentResponse.setMessage(" Hello," +
//                " This is to inform you that " + punishment.getStudent().getFirstName() + " " + punishment.getStudent().getLastName() +
//                " has received a REP for " + punishment.getInfraction().getInfractionName() + " and must complete "
//        + punishment.getInfraction().getAssignment() + ". If you have any questions you may contact the school's main office." +
//                "This is an automated message DO NOT REPLY to this message.");
//        punishmentResponse.setSubject("REP " + punishment.getPunishmentId() + " for " + punishment.getStudent().getFirstName() + " " + punishment.getStudent().getLastName());
//        punishmentResponse.setToEmail(punishment.getStudent().getParentEmail());
//
//        emailService.sendEmail(punishmentResponse.getToEmail(), punishmentResponse.getSubject(), punishmentResponse.getMessage());

//        Message.creator(new PhoneNumber(punishmentResponse.getPunishment().getStudent().getParentPhoneNumber()),
//                new PhoneNumber("+18437900073"), punishmentResponse.getMessage()).create();

        return punishmentResponse;
        }

    public PunishmentResponse createNewPunishForm(PunishmentFormRequest formRequest) {
//        Twilio.init(secretClient.getSecret("TWILIO-ACCOUNT-SID").toString(), secretClient.getSecret("TWILIO-AUTH-TOKEN").toString());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("REP CREATED");

        Student findMe = studentRepository.findByStudentEmail(formRequest.getStudentEmail());
        System.out.println(findMe);
        Infraction findInf = infractionRepository.findByInfractionName(formRequest.getInfractionName());
        System.out.println(findInf);
        findInf.setInfractionDescription(formRequest.getInfractionDescription());

        Punishment punishment = new Punishment();
        punishment.setStudent(findMe);
        punishment.setInfraction(findInf);
        punishment.setClassPeriod(formRequest.getInfractionPeriod());
        punishment.setPunishmentId(UUID.randomUUID().toString());
        punishment.setTimeCreated(now);
        punishment.setStatus("OPEN");
//        punishment.setClosedTimes("NEED TO WRITE LOGIC FOR THIS");
        PunishmentRequest punishmentRequest = new PunishmentRequest();
        punishmentRequest.setPunishment(punishment);

//        Message.creator(new PhoneNumber(punishmentResponse.getPunishment().getStudent().getParentPhoneNumber()),
//                new PhoneNumber("+18437900073"), punishmentResponse.getMessage()).create();
        checkOpen(punishmentRequest);
        punishRepository.save(punishment);
        PunishmentResponse punishmentResponse = new PunishmentResponse();
        punishmentResponse.setPunishment(punishment);

        return punishmentResponse;
    }

    public String deletePunishment ( Punishment punishment ) throws Exception {
        try{punishRepository.delete(punishment);}
        catch (Exception e) {
            throw new Exception("That infraction does not exist");
        } return "Punishment has been deleted";
    }

    public PunishmentResponse closePunishment ( Punishment punishment ) throws  Exception {
//        Twilio.init(secretClient.getSecret("TWILIO-ACCOUNT-SID").toString(), secretClient.getSecret("TWILIO-AUTH-TOKEN").toString());

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

//            Message.creator(new PhoneNumber(punishmentResponse.getPunishment().getStudent().getParentPhoneNumber()),
//                    new PhoneNumber("+18437900073"), punishmentResponse.getMessage()).create();

//        int closedPunishments = punishment.getClosedInfraction();
//        punishment.setClosedInfraction(closedPunishments + 1);

        return punishmentResponse;}
        else {
            throw new Exception("That infraction does not exist");
        }

    }

    public PunishmentResponse createClosedPunish(PunishmentRequest punishmentRequest) {
//        Twilio.init(secretClient.getSecret("TWILIO-ACCOUNT-SID").toString(), secretClient.getSecret("TWILIO-AUTH-TOKEN").toString());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("REP CREATED");

        Punishment punishment = punishmentRequest.getPunishment();
        punishment.setPunishmentId(UUID.randomUUID().toString());
        punishment.setTimeCreated(now);
        punishment.setStatus("CFR");

        return checkOpen(punishmentRequest);

//        PunishmentResponse punishmentResponse = new PunishmentResponse();
//        punishmentResponse.setPunishment(punishment);
//        punishmentResponse.setMessage(" This message needs to be changed because it is wrong. Do we even need it for this?");
//        punishmentResponse.setSubject("REP " + punishment.getPunishmentId() + " for " + punishment.getStudent().getFirstName() + " " + punishment.getStudent().getLastName());
//        punishmentResponse.setToEmail(punishment.getStudent().getParentEmail());
//
//        emailService.sendEmail(punishmentResponse.getToEmail(), punishmentResponse.getSubject(), punishmentResponse.getMessage());

//        Message.creator(new PhoneNumber(punishmentResponse.getPunishment().getStudent().getParentPhoneNumber()),
//                new PhoneNumber("+18437900073"), punishmentResponse.getMessage()).create();
    }

    private PunishmentResponse checkOpen(PunishmentRequest punishmentRequest) {
        Punishment punishment = punishmentRequest.getPunishment();
        var findOpen = punishRepository.findOpenByName(punishment.getStudent().getStudentIdNumber(),
                punishment.getInfraction().getInfractionId());

        if (findOpen != null) {
            return null;
        } else {
            return createNewPunish(punishmentRequest);
        }

    }

}
