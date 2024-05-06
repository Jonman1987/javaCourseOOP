package ru.academits.danilov_e.temperature.scalestypes;

public class Celsius implements ScaleInterface {
    double temperature;

    public Celsius(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }
}
