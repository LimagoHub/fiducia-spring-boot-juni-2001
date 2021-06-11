package de.fiducia.langermann.langer_mann.services.impl;

import de.fiducia.langermann.langer_mann.repositories.PersonRepository;
import de.fiducia.langermann.langer_mann.services.PersonService;
import de.fiducia.langermann.langer_mann.services.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PersonConfig {

    @Bean
    @Qualifier("antipathen")
    public List<String> antipathen() {
        return List.of("Attila","Peter","Paul","Mary");
    }
    @Bean
    @Qualifier("fruits")
    public List<String> fruits() {
        return List.of("Apple","Banana","Raspberry","Cherry");
    }

    @Bean
    public PersonService personService(final PersonRepository repo, final PersonMapper mapper,@Qualifier("antipathen") final List<String> antipathen)  {
        return new PersonServiceImpl(repo,mapper,antipathen);
    }

}
