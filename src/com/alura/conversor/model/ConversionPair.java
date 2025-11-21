package com.alura.conversor.model;

public class ConversionPair {
    private final Currency from;
    private final Currency to;

    public ConversionPair(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public String getFromCode() {
        return from.getCode();
    }

    public String getToCode() {
        return to.getCode();
    }
}
