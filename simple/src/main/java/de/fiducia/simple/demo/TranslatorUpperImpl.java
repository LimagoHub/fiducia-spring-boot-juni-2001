package de.fiducia.simple.demo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TranslatorUpperImpl implements  Translator{


    @Override
    public String translate(String message) {
        return message.toUpperCase();
    }


}
