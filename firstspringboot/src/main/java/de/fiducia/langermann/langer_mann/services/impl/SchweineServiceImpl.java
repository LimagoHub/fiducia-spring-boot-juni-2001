package de.fiducia.langermann.langer_mann.services.impl;

import de.fiducia.langermann.langer_mann.repositories.SchweineRepository;
import de.fiducia.langermann.langer_mann.repositories.entities.SchweinEntity;
import de.fiducia.langermann.langer_mann.services.SchweineService;
import de.fiducia.langermann.langer_mann.services.SchweineServiceException;
import de.fiducia.langermann.langer_mann.services.mapper.SchweinMapper;
import de.fiducia.langermann.langer_mann.services.models.Person;
import de.fiducia.langermann.langer_mann.services.models.Schwein;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = SchweineServiceException.class)
public class SchweineServiceImpl implements SchweineService {

    private final SchweineRepository repo;
    private final SchweinMapper mapper;

    public SchweineServiceImpl(final SchweineRepository repo, final SchweinMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @Override
    public boolean speichern(Schwein schwein) throws SchweineServiceException {
        try {
            boolean result = repo.existsById(schwein.getId());
            repo.save(mapper.convert(schwein));
            return result;
        } catch (Exception e) {
           throw new SchweineServiceException(e);
        }
    }

    @Override
    public boolean delete(Schwein schwein) throws SchweineServiceException {
        return delete(schwein.getId());
    }

    @Override
    public boolean delete(String id) throws SchweineServiceException {
        try {
            boolean result = repo.existsById(id);
            repo.deleteById(id);
            return result;
        } catch (Exception e) {
            throw new SchweineServiceException(e);
        }
    }

    @Override
    public Iterable<Schwein> findeAlle() throws SchweineServiceException {
        try {

            return mapper.convert(repo.findAll());

        } catch (Exception e) {
            throw new SchweineServiceException(e);
        }
    }

    @Override
    public boolean fuettern(String id)  throws SchweineServiceException{
        try {
            Optional<Schwein> optional = findeSchweinMitId(id);
           if(optional.isPresent()){
               Schwein s = optional.get();
               s.fressen();
               speichern(s);
               return true;
           }
           return false;
        } catch (RuntimeException e) {
            throw new SchweineServiceException(e);
        }
    }

    @Override
    public Optional<Schwein> findeSchweinMitId(String id) throws SchweineServiceException{
        try {
            return repo.findById(id).map(mapper::convert);
        } catch (Exception e) {
            throw new SchweineServiceException(e);
        }
    }
}
