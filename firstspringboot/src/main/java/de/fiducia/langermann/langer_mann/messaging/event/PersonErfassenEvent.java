package de.fiducia.langermann.langer_mann.messaging.event;

import de.fiducia.langermann.langer_mann.controllers.dtos.PersonDTO;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PersonErfassenEvent extends  Event{

    private final PersonDTO payload;
}
