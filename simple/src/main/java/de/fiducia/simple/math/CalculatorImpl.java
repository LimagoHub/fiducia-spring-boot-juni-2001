package de.fiducia.simple.math;

import org.springframework.stereotype.Component;

@Component
public class CalculatorImpl implements Calculator{
    @Override
    public double add(double a, double b) {
        return a + b;
    }
}
