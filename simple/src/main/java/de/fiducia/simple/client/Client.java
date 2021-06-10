package de.fiducia.simple.client;

import de.fiducia.simple.math.Calculator;

public class Client {

    private final Calculator calculator;

    public Client(Calculator calculator) {
        this.calculator = calculator;
    }

    public void run() {
        System.out.println(calculator.add(3,4));
    }
}
