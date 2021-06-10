package de.fiducia.simple.math.impl;

import de.fiducia.simple.math.Calculator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("logger")
public class CalculatorLogger implements Calculator {

    private final Calculator calculator;

    public CalculatorLogger(@Qualifier("impl") final Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public double add(double a, double b) {
        System.out.println("Add wurde gerufen");
        return calculator.add(a,b);
    }
}
