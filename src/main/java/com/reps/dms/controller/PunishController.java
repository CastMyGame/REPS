package com.reps.dms.controller;

import com.reps.dms.model.punishment.Punishment;
import com.reps.dms.model.punishment.PunishmentFormRequest;
import com.reps.dms.model.punishment.PunishmentRequest;
import com.reps.dms.model.punishment.PunishmentResponse;
import com.reps.dms.service.PunishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/punish/v1")
public class PunishController {
    @Autowired
    PunishmentService punishmentService;

    @GetMapping("/punishId")
    public ResponseEntity<Punishment> getByPunishId(@RequestBody Punishment punishment) throws Exception {
        var message = punishmentService.findByPunishmentId(punishment);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @PostMapping("/punishId/close")
    public ResponseEntity<PunishmentResponse> closePunishment(@RequestBody Punishment punishment) throws Exception {
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
    public ResponseEntity<List<Punishment>> getByStatus(@PathVariable String status) throws Exception {
        var message = punishmentService.findByStatus(status);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @GetMapping("/student")
    public ResponseEntity<List<Punishment>> getByStudent(@RequestBody PunishmentRequest punishmentRequest) throws Exception {
        var message = punishmentService.findByStudent(punishmentRequest);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @CrossOrigin
    @PostMapping("/startPunish")
    public ResponseEntity<PunishmentResponse> createNewPunish(@RequestBody PunishmentRequest punishmentRequest) {
        var message = punishmentService.createNewPunish(punishmentRequest);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @CrossOrigin
    @PostMapping("/startPunish/form")
    public ResponseEntity<PunishmentResponse> createNewFormPunish(@RequestBody PunishmentFormRequest punishmentFormRequest) {
        var message = punishmentService.createNewPunishForm(punishmentFormRequest);

        return ResponseEntity
                .accepted()
                .body(message);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePunishment (@RequestBody Punishment punishment) throws Exception {
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
