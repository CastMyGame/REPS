package com.reps.dms.data;

import com.reps.dms.model.infraction.Infraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface InfractionRepository extends JpaRepository<Infraction, String> {
    Infraction findByInfractionId(String infractionId);
    Infraction findByInfractionName (String infractionName);
}
