package de.fiducia.langermann.langer_mann.services.mapper;

import de.fiducia.langermann.langer_mann.controllers.PersonDTO;
import de.fiducia.langermann.langer_mann.repositories.entities.PersonEntity;
import de.fiducia.langermann.langer_mann.services.models.Person;
import lombok.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convert(PersonEntity dto);
    PersonEntity convert(Person person);
    List<Person> convert(List<PersonEntity> personEntities);
}
