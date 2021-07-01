package de.fiducia.langermann.langer_mann.repositories;

import de.fiducia.langermann.langer_mann.repositories.entities.SchweinEntity;
import org.springframework.data.repository.CrudRepository;

public interface SchweineRepository extends CrudRepository<SchweinEntity,String> {
}
