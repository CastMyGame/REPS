package com.reps.dms.model.infraction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "infraction_list")
public class Infraction {
    @Id
    @Column(name = "infraction_id")
    private String infractionId;
    @Column(name = "infraction_name")
    private String infractionName;
    @Column(name = "infraction_description")
    private String infractionDescription;
//    private String infractionType;
//    private String infractionUrl;
    @Column(name = "assignment")
    private String assignment;

}
