package de.fiducia.simple.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Qualifier("lower")
@Profile("development")
public class TranslatorLowerImpl implements Translator{
    @Override
    public String translate(String message) {
        return message.toLowerCase();
    }
}
