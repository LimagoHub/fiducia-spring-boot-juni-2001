package de.fiducia.langermann.langer_mann.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {

    // Erste Möglichkeit parameter zu übergeben (mit ? operator)
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDTO> getAllPersons(@RequestParam(name = "vorname",required = false) String vorname, @RequestParam(name = "nachname",required = false) String nachname) {
        return List.of(
                PersonDTO.builder().id("1").vorname("John").nachname("Doe" ).build(),
                PersonDTO.builder().id("2").vorname("John").nachname("Rambo" ).build(),
                PersonDTO.builder().id("3").vorname("John").nachname("Wayne" ).build(),
                PersonDTO.builder().id("4").vorname("John").nachname("Wick" ).build()
                )   ;
    }
}
