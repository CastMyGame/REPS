package com.dms.reps.controller;

import com.dms.reps.model.punishment.Punishment;
import com.dms.reps.model.punishment.PunishmentRequest;
import com.dms.reps.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/punish/v1")
public class PunishController {
    @Autowired
    PunishService punishService;

    @GetMapping("/punishId")
    public ResponseEntity<Optional<PunishRequestCommand>> getByPunishId(@RequestBody PunishRequestCommand punishRequestCommand) {
        var message = punishService.findByPunishId(punishRequestCommand);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/punishStatus")
    public ResponseEntity<Optional<PunishRequestCommand>> getByStatus(@RequestBody PunishRequestCommand punishRequestCommand) {
        var message = punishService.findByStatus(punishRequestCommand);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/student")
    public ResponseEntity<Optional<Punishment>> getByStudent(@RequestBody PunishmentRequest punishmentRequest) {
        var message = punishService.findByStudent(punishmentRequest);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @PostMapping("/startPunish")
    public ResponseEntity<PunishRequestCommand> createNewPunish(@RequestBody PunishRequestCommand punishRequestCommand) {
        var message = punishService.createNewPunish(punishRequestCommand);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePunishment (@RequestBody PunishRequestCommand requestCommand) {
        var delete = punishService.deletePunishment(requestCommand);
        return ResponseEntity
                .accepted()
                .body(delete);
    }

    @PutMapping("/edit")
    public ResponseEntity<PunishRequestCommand> editInfraction (@RequestBody PunishRequestCommand requestCommand) {
        var edit = punishService.createNewPunish(requestCommand);
        return ResponseEntity
                .accepted()
                .body(edit);
    }
}
