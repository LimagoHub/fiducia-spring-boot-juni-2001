package de.fiducia.simple.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Scope("prototype") // Eine Instanz bei Bedarf jedesmal neu und merkt sich die Referenz NICHT (nicht unter Verwaltung von Spring)
//@Scope("singleton") // Eine Instanz und merkt sich die Referenz (Default)
//@Lazy // ist nur zusammen mit singleton sinnvoll
public class Demo {


    private final Translator translator; // Depenency wird in den Ctor injected


    public Demo(final Translator translator) {
        this.translator = translator;
        System.out.println(this.translator.translate("Ctor Demo"));
    }


    @PostConstruct // Immer
    public void init() {
        System.out.println(translator.translate("Hier ist Init"));
    }

    @PreDestroy // Keine Ausführung bei Prototype (Keine Verwaltung durch Spring)
    public void destroy() {
        System.out.println("Und Tschüss...");
    }
}
