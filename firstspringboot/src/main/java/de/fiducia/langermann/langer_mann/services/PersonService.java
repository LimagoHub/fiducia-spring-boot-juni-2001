package de.fiducia.langermann.langer_mann.services;

import de.fiducia.langermann.langer_mann.services.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    boolean speichern(Person person) throws PersonenServiceException;
    boolean delete(Person person) throws PersonenServiceException;
    boolean delete(String id) throws PersonenServiceException;
    List<Person> findeAlle() throws PersonenServiceException;
    List<Person> findenachVorname(String vorname) throws PersonenServiceException;

    Optional<Person> findePersonMitId(String id) throws PersonenServiceException;

}
