package de.fiducia.langermann.langer_mann.services.mapper;

import de.fiducia.langermann.langer_mann.repositories.entities.PersonEntity;
import de.fiducia.langermann.langer_mann.repositories.entities.SchweinEntity;
import de.fiducia.langermann.langer_mann.services.models.Person;
import de.fiducia.langermann.langer_mann.services.models.Schwein;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SchweinMapper {
    Schwein convert(SchweinEntity entity);
    SchweinEntity convert(Schwein schwein);
    Iterable<Schwein> convert(Iterable<SchweinEntity> schweinEntities);
}
