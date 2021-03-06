package de.fiducia.langermann.langer_mann.application;

import de.fiducia.langermann.langer_mann.messaging.event.PersonErfassenEvent;
import de.fiducia.langermann.langer_mann.messaging.event.PersonenL√∂schenEvent;

public interface PersonenHandler {
    // @OnEventReceived("PersonErfassenEvent")
    void handle(PersonErfassenEvent event);

    void handle(PersonenL√∂schenEvent event);
}
