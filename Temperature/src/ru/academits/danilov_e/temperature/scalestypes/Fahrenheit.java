package ru.academits.danilov_e.temperature.scalestypes;

public class Fahrenheit implements ScaleInterface {
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature * 1.8 + 32;
    }
}
