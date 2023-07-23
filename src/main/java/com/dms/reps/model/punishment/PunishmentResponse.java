package com.dms.reps.model.punishment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PunishmentResponse {
    private Punishment punishment;
    private String error;
    private String toEmail;
    private String subject;
    private String message;
}
