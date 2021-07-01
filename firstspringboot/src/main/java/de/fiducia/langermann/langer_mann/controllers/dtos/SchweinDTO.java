package de.fiducia.langermann.langer_mann.controllers.dtos;


import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchweinDTO {
    @NotNull
    @Size(min=36, max=36)
    private String id;
    @NotNull
    @NotBlank
    @Size(max=30)
    private String name;

    @DecimalMin(value="10")
    private int gewicht;

    @NotNull
    private LocalDateTime version;
}
