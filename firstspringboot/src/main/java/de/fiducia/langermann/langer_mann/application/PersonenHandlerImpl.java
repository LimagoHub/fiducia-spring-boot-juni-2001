package de.fiducia.langermann.langer_mann.application;


import de.fiducia.langermann.langer_mann.controllers.mapper.PersonDTOMapper;
import de.fiducia.langermann.langer_mann.messaging.event.PersonErfassenEvent;
import de.fiducia.langermann.langer_mann.messaging.event.PersonenLöschenEvent;
import de.fiducia.langermann.langer_mann.services.PersonService;
import de.fiducia.langermann.langer_mann.services.PersonenServiceException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class PersonenHandlerImpl implements PersonenHandler {

    private final PersonService service;
    private final PersonDTOMapper mapper;

    public PersonenHandlerImpl(PersonService service, PersonDTOMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // @OnEventReceived("PersonErfassenEvent")
    @Override
    public void handle(PersonErfassenEvent event) {
        try {
            service.speichern(mapper.convert(event.getPayload()));
            // Success Event
            System.out.println("Success Event fired");
        } catch (PersonenServiceException e) {
            // Failure Event
            System.out.println("Failured Event fired");
            throw new RuntimeException(e); // Rollback
        }
    }
    @Override
    public void handle(PersonenLöschenEvent event) {

    }
}
