package de.fiducia.langermann.langer_mann.services.models;


import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class Schwein {
    @Setter(AccessLevel.NONE)
    private String id;
    private String name;
    @Setter(AccessLevel.NONE)
    private int gewicht;

    public void fressen(){
        gewicht ++;
    }

    @Setter(AccessLevel.NONE)
    private LocalDateTime version;
}
