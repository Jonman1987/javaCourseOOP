package ru.academits.danilov_e.temperaturemodel;

public class TemperatureConverter implements TemperatureModel{
    @Override
    public double convertCelsiusToKelvin(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }
}
