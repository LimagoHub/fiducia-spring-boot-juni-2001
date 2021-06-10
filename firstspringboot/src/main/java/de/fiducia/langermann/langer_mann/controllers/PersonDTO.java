package de.fiducia.langermann.langer_mann.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement
public class PersonDTO {

    private String id;
    private String vorname;
    private String nachname;
}
