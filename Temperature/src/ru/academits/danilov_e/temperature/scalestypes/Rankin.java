package ru.academits.danilov_e.temperature.scalestypes;

public class Rankin implements ScaleInterface{
    double temperature;

    public Rankin(double temperature) {
        this.temperature = temperature;
    }
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 491.67) * 5 / 9;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return (temperature + 273.15) * 9 / 5;
    }
}
