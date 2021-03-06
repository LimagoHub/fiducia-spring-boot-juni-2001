package de.fiducia.langermann.langer_mann.services.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

// Lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Person { // Aggregat

    @Setter(AccessLevel.NONE)
    private String id;

    private String vorname;


    private String nachname;

}
