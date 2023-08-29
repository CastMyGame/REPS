package com.reps.dms.controller;

import com.reps.dms.model.infraction.Infraction;
import com.reps.dms.service.InfractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("infraction/v1")
public class InfractionController {
    private final InfractionService infractionService;

    @PostMapping("/createInfraction")
    public ResponseEntity<Infraction> createNewInfraction(@RequestBody Infraction infraction) {
        var message = infractionService.createNewInfraction(infraction);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/infractionId")
    public ResponseEntity<Infraction> getInfractionById (@RequestBody Infraction infraction) throws Exception {
        var findMe = infractionService.findByInfractionId(infraction.getInfractionId());

        return ResponseEntity
                .accepted()
                .body(findMe);
    }

    @GetMapping("/infractionName")
    public ResponseEntity<Infraction> getInfractionByName (@RequestBody Infraction infraction) throws Exception {
        var findMe = infractionService.findInfractionByInfractionName(infraction.getInfractionName());

        return ResponseEntity
                .accepted()
                .body(findMe);
    }

    @DeleteMapping("/delete/infraction")
    public ResponseEntity<String> deleteInfraction (@RequestBody Infraction infraction) throws Exception {
        var delete = infractionService.deleteInfraction(infraction);
        return ResponseEntity
                .accepted()
                .body(delete);
    }

    @PutMapping("/infraction/edit")
    public ResponseEntity<Infraction> editInfraction (@RequestBody Infraction infraction) {
        var edit = infractionService.createNewInfraction(infraction);
        return ResponseEntity
                .accepted()
                .body(edit);
    }
}
