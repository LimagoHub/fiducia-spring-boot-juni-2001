package de.fiducia.langermann.langer_mann.repositories.impl;

import de.fiducia.langermann.langer_mann.repositories.PersonCustomRepository;
import de.fiducia.langermann.langer_mann.repositories.entities.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersonCustomRepositoryImpl implements PersonCustomRepository {

    @Autowired
    private EntityManager manager;


    @Override
    public List<PersonEntity> xyz() {
        TypedQuery<PersonEntity> query = manager.createQuery("from PersonEntity", PersonEntity.class);
        query.setFirstResult(100);
        query.setMaxResults(10);
        return query.getResultList();
    }
}
