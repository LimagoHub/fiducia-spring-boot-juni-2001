package de.fiducia.langermann.langer_mann.messaging.event;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder

public class PersonenL√∂schenEvent extends Event{

    private String toDeleteId;
}
