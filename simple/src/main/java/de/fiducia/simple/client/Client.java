package de.fiducia.simple.client;

import org.springframework.stereotype.Component;

import de.fiducia.simple.math.Calculator;


@Component
public class Client {

    private final Calculator calculator;

    public Client(Calculator calculator) {
        this.calculator = calculator;
    }

    public void run() {
        System.out.println(calculator.add(3,4));
    }
}
