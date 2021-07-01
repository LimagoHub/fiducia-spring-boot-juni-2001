package de.fiducia.langermann.langer_mann.services;

import de.fiducia.langermann.langer_mann.services.models.Person;
import de.fiducia.langermann.langer_mann.services.models.Schwein;

import java.util.List;
import java.util.Optional;

public interface SchweineService {

    boolean speichern(Schwein schwein) throws SchweineServiceException;
    boolean delete(Schwein schwein) throws SchweineServiceException;
    boolean delete(String id) throws SchweineServiceException;
    Iterable<Schwein> findeAlle() throws SchweineServiceException;

    boolean fuettern(String id)throws SchweineServiceException;
    Optional<Schwein> findeSchweinMitId(String id) throws SchweineServiceException;
}
