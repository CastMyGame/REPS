package com.dms.hims.controller;

import com.dms.hims.model.Infraction;
import com.dms.hims.service.InfractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("infraction/v1")
public class InfractionController {
    private final InfractionService service;

    @PostMapping("/createInfraction")
    public ResponseEntity<Infraction> createNewPunish(@RequestBody Infraction infraction) {
        var message = service.createNewInfraction(infraction);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/infractionId")
    public ResponseEntity<Optional<Infraction>> getInfractionById (@RequestBody Infraction infraction) {
        var findMe = service.findInfractionById(infraction.getInfractionId());

        return ResponseEntity
                .accepted()
                .body(findMe);
    }

    @GetMapping("/infractionName")
    public ResponseEntity<Optional<Infraction>> getInfractionByName (@RequestBody Infraction infraction) {
        var findMe = service.findInfractionByName(infraction.getInfractionName());

        return ResponseEntity
                .accepted()
                .body(findMe);
    }
}
