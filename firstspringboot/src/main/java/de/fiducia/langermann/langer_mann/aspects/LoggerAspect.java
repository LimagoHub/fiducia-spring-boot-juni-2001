package de.fiducia.langermann.langer_mann.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class LoggerAspect {



    @Before(value="Pointcuts.personControllerMethods()")
    public void logAdvice(JoinPoint joinPoint) {
        log.error("Methode {} wird ausgeführt",joinPoint.getSignature().getName());
    }
    @AfterReturning(value = "Pointcuts.personControllerMethods()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        log.error("Methode {} wurde ausgeführt",joinPoint.getSignature().getName());
        log.error("Mit Result {}", result);
    }
    @AfterThrowing(value = "execution(public * de.fiducia.langermann.langer_mann.controllers.PersonController.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Methode {} wurde ausgeführt",joinPoint.getSignature().getName());
        log.error("Upps", ex);
    }
    @Around(value = "execution(public * de.fiducia.langermann.langer_mann.controllers.PersonController.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws  Throwable{
        Instant start = Instant.now();
        proceedingJoinPoint.proceed();
        Instant ende = Instant.now();
        Duration duration = Duration.between(start, ende);
        System.out.println("Duration = " + duration.toMillis());
    }



}
