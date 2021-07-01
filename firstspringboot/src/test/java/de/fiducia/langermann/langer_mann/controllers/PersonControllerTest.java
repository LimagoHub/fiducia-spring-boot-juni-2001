package de.fiducia.langermann.langer_mann.controllers;

import de.fiducia.langermann.langer_mann.controllers.dtos.PersonDTO;
import de.fiducia.langermann.langer_mann.services.PersonService;
import de.fiducia.langermann.langer_mann.services.PersonenServiceException;
import de.fiducia.langermann.langer_mann.services.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"/create.sql", "/insert.sql"})
@ExtendWith(SpringExtension.class)
class PersonControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean // Springspezifisch
    private PersonService personServiceMock;

    @Test
    //@Sql({"/create.sql", "/insert.sql"})
    void test1() throws Exception{

        // Arrange
        Person validPerson = Person.builder().id("123").vorname("john").nachname("doe").build();
        Optional<Person> optional = Optional.of(validPerson);
        when(personServiceMock.findePersonMitId(Mockito.anyString())).thenReturn(optional);

        // Act
        PersonDTO dto = restTemplate.getForObject("/v1/persons/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        // Assertion
        assertEquals("john", dto.getVorname());

        //String string = restTemplate.getForObject("/v1/persons/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        //System.out.println(string);
    }

    @Test
        //@Sql({"/create.sql", "/insert.sql"})
    void test2() throws Exception{

        // Arrange
        Optional<Person> optional = Optional.empty();
        when(personServiceMock.findePersonMitId(Mockito.anyString())).thenReturn(optional);

        // Act
        ResponseEntity<PersonDTO> entity = restTemplate.getForEntity("/v1/persons/b2e24e74-8686-43ea-baff-d9396b4202e0", PersonDTO.class);

        // Assertion
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
        //String string = restTemplate.getForObject("/v1/persons/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        //System.out.println(string);
    }

    @Test
        //@Sql({"/create.sql", "/insert.sql"})
    void test3() throws Exception{


        when(personServiceMock.findePersonMitId(Mockito.anyString())).thenThrow(new PersonenServiceException("Upps"));
        //ResponseEntity<Map<String,Object>>  entity = restTemplate.getForEntity("/v1/persons/b2e24e74-8686-43ea-baff-d9396b4202e0", Map<String, Object>.class);
        ResponseEntity<Map<String,Object>>  entity =
                restTemplate.exchange("/v1/persons/b2e24e74-8686-43ea-baff-d9396b4202e0", HttpMethod.GET, null,new ParameterizedTypeReference<Map<String, Object>>() {});
        Map<String,Object> body = entity.getBody();

        assertEquals("abc", body.get("xyz"));
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
        //String string = restTemplate.getForObject("/v1/persons/b2e24e74-8686-43ea-baff-d9396b4202e0", String.class);
        //System.out.println(string);
    }

}