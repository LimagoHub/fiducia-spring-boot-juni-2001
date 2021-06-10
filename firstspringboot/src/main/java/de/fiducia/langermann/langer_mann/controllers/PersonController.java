package de.fiducia.langermann.langer_mann.controllers;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {

    // Erste Möglichkeit parameter zu übergeben (mit ? operator)
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Personliste wurde erstellt")
    public ResponseEntity<List<PersonDTO>> getAllPersons(@RequestParam(name = "vorname",required = false) String vorname, @RequestParam(name = "nachname",required = false) String nachname) {
        return ResponseEntity.ok(List.of(
                PersonDTO.builder().id("1").vorname("John").nachname("Doe" ).build(),
                PersonDTO.builder().id("2").vorname("John").nachname("Rambo" ).build(),
                PersonDTO.builder().id("3").vorname("John").nachname("Wayne" ).build(),
                PersonDTO.builder().id("4").vorname("John").nachname("Wick" ).build()
        ) );
    }

    @GetMapping(path="/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE} )

    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable String id) {
        if("4711".equals(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(PersonDTO.builder().id(id).vorname("John").nachname("Doe").build());
    }
}
