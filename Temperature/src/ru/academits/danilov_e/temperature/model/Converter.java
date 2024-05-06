package ru.academits.danilov_e.temperature.model;

public class Converter implements ModelInterface {
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

    @Override
    public String[] getTemperaturesTypes() {
        return new String[] {"Градусы Цельсия", "Кельвины", "Фаренгейты"};
    }

    @Override
    public double convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo) {
        double temperatureResult = 0;

        if (temperatureTypeFrom == 0 && temperatureTypeTo == 1) {
            temperatureResult = convertCelsiusToKelvin(temperature);
        } else if (temperatureTypeFrom == 1 && temperatureTypeTo == 0) {
            temperatureResult = convertKelvinToCelsius(temperature);
        } else if (temperatureTypeFrom == 0 && temperatureTypeTo == 2) {
            temperatureResult = convertCelsiusToFahrenheit(temperature);
        } else if (temperatureTypeFrom == 2 && temperatureTypeTo == 0) {
            temperatureResult = convertFahrenheitToCelsius(temperature);
        } else if (temperatureTypeFrom == 2 && temperatureTypeTo == 1) {
            temperatureResult = convertFahrenheitToKelvin(temperature);
        } else if (temperatureTypeFrom == 1 && temperatureTypeTo == 2) {
            temperatureResult = convertKelvinToFahrenheit(temperature);
        } else if (temperatureTypeFrom == temperatureTypeTo) {
            temperatureResult = temperature;
        }

        return temperatureResult;
    }
}
