package de.fiducia.langermann.langer_mann.controllers;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.glassfish.jersey.uri.UriComponent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

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


    // Zweite Möglichkeit parameter zu übergeben (als Pfadvariable)
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

    @DeleteMapping (path="/{id}")
    @ApiResponse(responseCode = "200", description = "Person wurde gelöscht")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    public ResponseEntity<Void> deleteById(@PathVariable String id){
        if("4711".equals(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

//    // Dritte Möglichkeit parameter zu übergeben (als JSON)
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ApiResponse(responseCode = "200", description = "Person wurde geändert")
//    @ApiResponse(responseCode = "201", description = "Person wurde gespeicher")
//    public ResponseEntity<Void> saveOrUpdate(@RequestBody  PersonDTO dto, UriComponentsBuilder uriComponentsBuilder) {
//        dto.setId(UUID.randomUUID().toString());
//
//        // Immer wenn kein Idempotetes Post
//        UriComponents uriComponents = uriComponentsBuilder.path("v1/persons/{id}").buildAndExpand(dto.getId());
//
//        System.out.println("DTO: " + dto + " wurde gespeichert");
//        return  ResponseEntity.created(uriComponents.toUri()).build();
//    }

    // Dritte Möglichkeit parameter zu übergeben (als JSON)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Person wurde geändert")
    @ApiResponse(responseCode = "201", description = "Person wurde gespeicher")
    public ResponseEntity<Void> saveOrUpdate(@RequestBody  PersonDTO dto) {


        System.out.println("DTO: " + dto + " wurde gespeichert");
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Ersatz
    @PostMapping(value = "/to-upper",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> toUpper(@RequestBody PersonDTO person) {
        person.setNachname(person.getNachname().toUpperCase());
        person.setVorname(person.getVorname().toUpperCase());
        return ResponseEntity.ok(person);
    }

}
