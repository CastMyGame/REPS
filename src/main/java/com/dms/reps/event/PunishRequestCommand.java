package com.dms.reps.event;


import com.dms.reps.model.Infraction;
import com.dms.reps.model.student.Student;
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
    private Integer punishId;
    private Infraction infraction;
    private Student student;
    private String infractionDetails;
    private String status;

}
