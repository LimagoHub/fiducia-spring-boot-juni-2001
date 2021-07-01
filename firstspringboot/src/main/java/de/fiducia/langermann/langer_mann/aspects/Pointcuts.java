package de.fiducia.langermann.langer_mann.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(public * de.fiducia.langermann.langer_mann.controllers.PersonController.*(..))")
    public void personControllerMethods(){}

    @Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)" )
    public void restControllerMethodes() {}
}
