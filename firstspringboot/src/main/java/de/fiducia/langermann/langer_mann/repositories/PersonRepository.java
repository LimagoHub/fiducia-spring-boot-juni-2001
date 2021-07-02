package de.fiducia.langermann.langer_mann.repositories;

import de.fiducia.langermann.langer_mann.repositories.entities.PersonEntity;
import de.fiducia.langermann.langer_mann.repositories.entities.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonEntity, String> , PersonCustomRepository{

    public List<PersonEntity> findByVorname(String vorname);
    public List<PersonEntity> findByNachname(String nachname);
    public List<PersonEntity> findByVornameAndNachnameOrderByNachnameAsc(String vorname, String nachname);
    public List<PersonEntity> findByVornameOrNachnameOrderByNachnameAsc(String vorname, String nachname);
    public List<PersonEntity> findeAlle();
    public List<String> nachnamen();

    @Query("select new de.fiducia.langermann.langer_mann.repositories.entities.TinyPerson(p.id, p.nachname) from PersonEntity p where p.vorname=:vorname")
    public List<TinyPerson> tinies(String vorname);

    @Query("update PersonEntity p set p.vorname=:vorname where p.id=:id")
    public void updateVorname(String id, String vorname);

}
