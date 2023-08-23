package com.dms.reps.model.infraction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfractionResponse {
    private String error;
    private Infraction infraction;
}
