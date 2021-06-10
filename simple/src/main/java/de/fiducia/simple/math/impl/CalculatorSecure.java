package de.fiducia.simple.math.impl;

import de.fiducia.simple.math.Calculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CalculatorSecure implements Calculator {

    private final  Calculator calculator;


    public CalculatorSecure(@Qualifier("logger") final Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public double add(double a, double b) {
        System.out.println("Du kommst rein");
        return calculator.add(a, b);
    }
}
