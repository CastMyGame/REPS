package com.dms.reps.controller;

import com.dms.reps.model.infraction.Infraction;
import com.dms.reps.service.InfractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("infraction/v1")
public class InfractionController {
    @Autowired
    InfractionService infractionService;

    @PostMapping("/createInfraction")
    public ResponseEntity<Infraction> createNewInfraction(@RequestBody Infraction infraction) {
//        var message = infractionService.createNewInfraction(infraction);

        return ResponseEntity
                .accepted()
                .body(infraction);
    }

    @GetMapping("/infractionId")
    public ResponseEntity<Infraction> getInfractionById (@RequestBody Infraction infraction) {
        var findMe = infractionService.findByInfractionId(infraction.getInfractionId());

        return ResponseEntity
                .accepted()
                .body(findMe);
    }

    @GetMapping("/infractionName")
    public ResponseEntity<Optional<Infraction>> getInfractionByName (@RequestBody Infraction infraction) {
        var findMe = infractionService.findInfractionByInfractionName(infraction.getInfractionName());

        return ResponseEntity
                .accepted()
                .body(findMe);
    }

    @DeleteMapping("/delete/infraction")
    public ResponseEntity<String> deleteInfraction (@RequestBody Infraction infraction) {
//        var delete = infractionService.deleteInfraction(infraction);
        return ResponseEntity
                .accepted()
                .body("delete");
    }

    @PutMapping("/infraction/edit")
    public ResponseEntity<Infraction> editInfraction (@RequestBody Infraction infraction) {
//        var edit = infractionService.createNewInfraction(infraction);
        return ResponseEntity
                .accepted()
                .body(infraction);
    }
}
