package de.fiducia.simple.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Qualifier("upper")
@Profile("production")
public class TranslatorUpperImpl implements  Translator{


    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }


}
