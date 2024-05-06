package ru.academits.danilov_e.temperature.scalestypes;

public class Kelvin implements ScaleInterface {
    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }
}
