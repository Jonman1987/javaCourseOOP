package ru.academits.danilov_e.temperature.scalestypes;

public class Rankin implements ScaleInterface{
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 491.67) * 5 / 9;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature + 273.15) * 9 / 5;
    }
}
