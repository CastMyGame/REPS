package com.dms.hims.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Reported_Infractions")
public class PunishRequestCommand {
    @Id
    private Integer infractionCode;
    private String infractionType;
    private String studentId;
    private String firstName;
    private String lastName;
    private String infractionDetails;

}
