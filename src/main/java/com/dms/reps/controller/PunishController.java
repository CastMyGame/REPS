package com.dms.reps.controller;

import com.dms.reps.model.punishment.Punishment;
import com.dms.reps.model.punishment.PunishmentRequest;
import com.dms.reps.model.punishment.PunishmentResponse;
import com.dms.reps.service.PunishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/punish/v1")
public class PunishController {
    @Autowired
    PunishmentService punishmentService;

    @GetMapping("/punishId")
    public ResponseEntity<Punishment> getByPunishId(@RequestBody Punishment punishment) {
        var message = punishmentService.findByPunishmentId(punishment);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @PostMapping("/punishId/close")
    public ResponseEntity<PunishmentResponse> closePunishment(@RequestBody Punishment punishment) {
        var message = punishmentService.closePunishment(punishment);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/punishments")
    public ResponseEntity<List<Punishment>> getAll() {
        var message = punishmentService.findAll();

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/punishStatus/{status}")
    public ResponseEntity<List<Punishment>> getByStatus(@PathVariable String status) {
        var message = punishmentService.findByStatus(status);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/student")
    public ResponseEntity<List<Punishment>> getByStudent(@RequestBody PunishmentRequest punishmentRequest) {
        var message = punishmentService.findByStudent(punishmentRequest);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @PostMapping("/startPunish")
    public ResponseEntity<PunishmentResponse> createNewPunish(@RequestBody PunishmentRequest punishmentRequest) {
        var message = punishmentService.createNewPunish(punishmentRequest);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePunishment (@RequestBody Punishment punishment) {
        var delete = punishmentService.deletePunishment(punishment);
        return ResponseEntity
                .accepted()
                .body(delete);
    }

//    @PutMapping("/edit")
//    public ResponseEntity<PunishRequestCommand> editInfraction (@RequestBody PunishRequestCommand requestCommand) {
//        var edit = punishmentService.createNewPunish(requestCommand);
//        return ResponseEntity
//                .accepted()
//                .body(edit);
//    }
}
