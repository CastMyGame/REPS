package com.dms.hims.service;

import com.dms.hims.data.InfractionRepository;
import com.dms.hims.event.InfractionReportCommand;
import com.dms.hims.model.Infraction;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InfractionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InfractionRepository repository;

    public InfractionService (InfractionRepository repository) {
        this.repository = repository;
    }

    public Optional<Infraction> addInfraction (String level, InfractionReportCommand command) {

    }
}
