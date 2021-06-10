package de.fiducia.langermann.langer_mann.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {

    private String id;
    private String vorname;
    private String nachname;
}
