package de.fiducia.langermann.langer_mann.services.impl;

import de.fiducia.langermann.langer_mann.repositories.PersonRepository;
import de.fiducia.langermann.langer_mann.repositories.entities.PersonEntity;
import de.fiducia.langermann.langer_mann.services.PersonService;
import de.fiducia.langermann.langer_mann.services.PersonenServiceException;
import de.fiducia.langermann.langer_mann.services.mapper.PersonMapper;
import de.fiducia.langermann.langer_mann.services.models.Person;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersonenServiceException.class)
public class PersonServiceImpl implements PersonService {


    private final PersonRepository repo;
    private final PersonMapper mapper;
    private final List<String> antipathen;

    public PersonServiceImpl(PersonRepository repo, PersonMapper mapper,List<String> antipathen) {
        this.repo = repo;
        this.mapper = mapper;
        this.antipathen = antipathen;
    }


    /*
        Validierung
        Person nicht null > PSE
        vorname nicht null und nicht weniger als 2 Zeichen -> PSE
        nachname nicht null und nicht weniger als 2 Zeichen -> PSE

        Business check
        vorname nicht Attila -> PSE

        Technische Exceptions in PSE wandeln

        im Happyfall person an Repo übergeben

        return true wenn Person persistent
        return false wenn Person transient

     */
    @Override
    public boolean speichern(Person person) throws PersonenServiceException {
        try {
            if(person == null)
                throw new PersonenServiceException("Person darf nicht null sein.");
            if(person.getVorname() == null || person.getVorname().length() < 2)
                throw new PersonenServiceException("Vorname muss min. 2 Zeichen haben.");

            if(antipathen.contains(person.getVorname()))
                throw new PersonenServiceException("Antipath.");

            boolean exists = repo.existsById(person.getId());
            repo.save(mapper.convert(person));
            return exists;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("Service nicht erreichbar.", e);
        }
    }

    @Override
    public boolean delete(Person person) throws PersonenServiceException {
        if(person == null)
            throw new PersonenServiceException("Person darf nicht null sein.");
        return delete(person.getId());
    }

    @Override
    public boolean delete(String id) throws PersonenServiceException {
        try {
            if( repo.existsById(id)){
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            throw new PersonenServiceException("",e);
        }
    }

    @Override
    public List<Person> findeAlle() throws PersonenServiceException {
        List<PersonEntity> retval = new ArrayList<>();
        repo.findAll().forEach(retval::add);
        return mapper.convert(retval);
    }

    @Override
    public List<Person> findenachVorname(String vorname) throws PersonenServiceException {
        return mapper.convert(repo.findByVorname(vorname));
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public Optional<Person> findePersonMitId(String id) throws PersonenServiceException {
        return repo.findById(id).map(mapper::convert);
    }
}
