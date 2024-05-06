package ru.academits.danilov_e.temperature.scalestypes;

public class Kelvin implements ScaleInterface {
    double temperature;

    public Kelvin(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }
}
