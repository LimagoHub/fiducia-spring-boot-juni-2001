package de.fiducia.langermann.langer_mann.controllers;

import de.fiducia.langermann.langer_mann.application.PersonenHandler;
import de.fiducia.langermann.langer_mann.controllers.dtos.PersonDTO;
import de.fiducia.langermann.langer_mann.controllers.mapper.PersonDTOMapper;
import de.fiducia.langermann.langer_mann.messaging.event.PersonErfassenEvent;
import de.fiducia.langermann.langer_mann.messaging.event.PersonenLöschenEvent;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v2/persons")
public class PersonCommandController {


    private final PersonDTOMapper mapper;
    private final PersonenHandler handler;

    public PersonCommandController(PersonDTOMapper mapper, PersonenHandler handler) {
        this.mapper = mapper;
        this.handler = handler;
    }


    @DeleteMapping (path="/{id}")
    @ApiResponse(responseCode = "200", description = "Person wurde gelöscht")
    @ApiResponse(responseCode = "404", description = "Person wurde nicht gefunden")
    public ResponseEntity<Void> deleteById(@PathVariable String id) throws Exception{
       handler.handle(PersonenLöschenEvent.builder().toDeleteId(id).build());
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
    //@ApiResponse(responseCode = "201", description = "Person wurde gespeicher")
    public ResponseEntity<Void> saveOrUpdate(@Valid @RequestBody PersonDTO dto) throws Exception{
        //if(personService.speichern(mapper.convert(dto)))
        handler.handle(PersonErfassenEvent.builder().payload(dto).build());
        return  ResponseEntity.ok().build();
        //return  ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
