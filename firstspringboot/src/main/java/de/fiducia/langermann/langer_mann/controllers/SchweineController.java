package de.fiducia.langermann.langer_mann.controllers;


import de.fiducia.langermann.langer_mann.controllers.dtos.SchweinDTO;
import de.fiducia.langermann.langer_mann.controllers.mapper.SchweinDTOMapper;
import de.fiducia.langermann.langer_mann.services.SchweineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/schweine")
public class SchweineController {

    private final SchweineService schweineService;
    private final SchweinDTOMapper mapper;

    public SchweineController(final SchweineService schweineService, final SchweinDTOMapper mapper) {
        this.schweineService = schweineService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SchweinDTO> findPigById(@PathVariable  String id) throws Exception{
        return ResponseEntity.of(schweineService.findeSchweinMitId(id).map(mapper::convert));
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<SchweinDTO>> findeAlle() throws Exception{
        return ResponseEntity.ok(mapper.convert(schweineService.findeAlle()));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePigById(@PathVariable  String id) throws Exception{
        if(schweineService.delete(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
    @PostMapping(path = "/{id}/fuettern")
    public ResponseEntity<Void> fuetternPigById(@PathVariable  String id) throws Exception{
        if(schweineService.fuettern(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody @Valid  SchweinDTO schweinDTO) throws Exception{
        if(schweineService.speichern(mapper.convert(schweinDTO)))
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
