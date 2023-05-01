package com.dms.hims.controller;

import com.dms.hims.event.PunishRequestCommand;
import com.dms.hims.model.Student;
import com.dms.hims.service.PunishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/punish/v1")
public class PunishController {
    private final PunishService punishService;

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
    public ResponseEntity<Optional<PunishRequestCommand>> getByStudent(@RequestBody PunishRequestCommand punishRequestCommand) {
        var message = punishService.findByStudent(punishRequestCommand);

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
}
