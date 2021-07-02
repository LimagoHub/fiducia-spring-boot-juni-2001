package de.fiducia.langermann.langer_mann.application;

import de.fiducia.langermann.langer_mann.messaging.event.PersonErfassenEvent;
import de.fiducia.langermann.langer_mann.messaging.event.PersonenLöschenEvent;

public interface PersonenHandler {
    // @OnEventReceived("PersonErfassenEvent")
    void handle(PersonErfassenEvent event);

    void handle(PersonenLöschenEvent event);
}
