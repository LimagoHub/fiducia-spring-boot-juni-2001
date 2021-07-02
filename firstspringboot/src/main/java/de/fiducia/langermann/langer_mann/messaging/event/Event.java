package de.fiducia.langermann.langer_mann.messaging.event;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
public class Event {

    @Builder.Default
    private final String eventID = UUID.randomUUID().toString();
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();


}
