package de.fiducia.langermann.langer_mann.controllers;


import de.fiducia.langermann.langer_mann.controllers.dtos.PersonDTO;
import de.fiducia.langermann.langer_mann.controllers.mapper.PersonDTOMapper;
import de.fiducia.langermann.langer_mann.services.PersonService;
import de.fiducia.langermann.langer_mann.services.PersonenServiceException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonDTOMapper mapper;

    public PersonController(PersonService personService, PersonDTOMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }


    // Erste Möglichkeit parameter zu übergeben (mit ? operator)
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(responseCode = "200", description = "Personliste wurde erstellt")
    public ResponseEntity<List<PersonDTO>> getAllPersons(
            @RequestParam(name = "vorname",required = false) String vorname,
            @RequestParam(name = "nachname",required = false) String nachname) throws PersonenServiceException {
        return ResponseEntity.ok(mapper.convert(personService.findeAlle()));
    }


    // Zweite Möglichkeit parameter zu übergeben (als Pfadvariable)
    @GetMapping(path="/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE} )

    @ApiResponse(responseCode = "200", description = "Person wurde gefunden")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable String id) throws PersonenServiceException{
        return ResponseEntity.of(personService.findePersonMitId(id).map(mapper::convert));
    }

    @DeleteMapping (path="/{id}")
    @ApiResponse(responseCode = "200", description = "Person wurde gelöscht")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    public ResponseEntity<Void> deleteById(@PathVariable String id) throws Exception{
        if(personService.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<Void> saveOrUpdate(@Valid @RequestBody  PersonDTO dto) throws Exception{
        if(personService.speichern(mapper.convert(dto)))
            return  ResponseEntity.ok().build();
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Ersatzget
    @PostMapping(value = "/to-lower",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> toUpper(@RequestBody PersonDTO person) {
        person.setNachname(person.getNachname().toLowerCase());
        person.setVorname(person.getVorname().toLowerCase());
        return ResponseEntity.ok(person);
    }

}
