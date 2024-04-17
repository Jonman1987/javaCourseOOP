package ru.academits.danilov_e.temperaturemodel;

public class TemperatureConverter implements TemperatureModel {
    @Override
    public double convertCelsiusToKelvin(double celsiusTemperature) {
        return celsiusTemperature + 273.15;
    }

    @Override
    public double convertKelvinToCelsius(double kelvinTemperature) {
        return kelvinTemperature - 273.15;
    }

    @Override
    public double convertFahrenheitToCelsius(double fahrenheitTemperature) {
        return (fahrenheitTemperature - 32) / 1.8;
    }

    @Override
    public double convertCelsiusToFahrenheit(double celsiusTemperature) {
        return celsiusTemperature * 1.8 + 32;
    }

    @Override
    public double convertKelvinToFahrenheit(double kelvinTemperature) {
        return kelvinTemperature * 1.8 - 459.7;
    }

    @Override
    public double convertFahrenheitToKelvin(double fahrenheitTemperature) {
        return (fahrenheitTemperature - 32) / 1.8 + 273.15;
    }
}
