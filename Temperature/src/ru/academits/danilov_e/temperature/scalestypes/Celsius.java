package ru.academits.danilov_e.temperature.scalestypes;

public class Celsius implements ScaleInterface {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }
}
