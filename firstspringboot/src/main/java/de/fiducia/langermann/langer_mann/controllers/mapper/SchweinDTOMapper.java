package de.fiducia.langermann.langer_mann.controllers.mapper;

import de.fiducia.langermann.langer_mann.controllers.dtos.SchweinDTO;
import de.fiducia.langermann.langer_mann.services.models.Schwein;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchweinDTOMapper {


        Schwein convert(SchweinDTO dto);
        SchweinDTO convert(Schwein schwein);
        Iterable<SchweinDTO> convert(Iterable<Schwein> schweine);


}
