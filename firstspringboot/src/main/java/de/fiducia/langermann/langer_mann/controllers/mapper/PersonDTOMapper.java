package de.fiducia.langermann.langer_mann.controllers.mapper;

import de.fiducia.langermann.langer_mann.controllers.PersonDTO;
import de.fiducia.langermann.langer_mann.repositories.entities.PersonEntity;
import de.fiducia.langermann.langer_mann.services.models.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {
    Person convert(PersonDTO dto);
    PersonDTO convert(Person person);
    List<PersonDTO> convert(List<Person> personen);
}
