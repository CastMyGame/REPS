package com.reps.dms.service;

import com.reps.dms.data.InfractionRepository;
import com.reps.dms.model.infraction.Infraction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InfractionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final InfractionRepository repository;

    public Infraction findInfractionByInfractionName (String infractionName) throws Exception {
        var findMe = repository.findByInfractionName(infractionName);

        if (findMe == null) {
            throw new Exception("No infraction with that name exists");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Infraction findByInfractionId (String infractionId) throws Exception {
//        Query query = new Query();
//        query.addCriteria(
//                new Criteria(
//                ).(Criteria.where("infractionId").is(infractionId)));
//
//        Infraction infraction = mongoTemplate.findOne(query, Infraction.class);
//        if (infraction != null) {
//            return infraction;
//        } else {
//            return null;
//        }
        var findMe = repository.findByInfractionId(infractionId);

        if (findMe == null) {
            throw new Exception("No infraction with that ID exists");
        }
        logger.debug(String.valueOf(findMe));
        return findMe;
    }

    public Infraction createNewInfraction (Infraction infraction ) {
        return repository.save(infraction);
    }
    public String deleteInfraction ( Infraction infraction ) throws Exception {
        try{repository.delete(infraction);}
        catch (Exception e) {
            throw new Exception("That infraction does not exist");
        } return "${infraction} has been deleted";
    }
}
