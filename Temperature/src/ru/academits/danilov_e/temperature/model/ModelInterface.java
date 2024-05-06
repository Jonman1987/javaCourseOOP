package ru.academits.danilov_e.temperature.model;

public interface ModelInterface {
    double convertCelsiusToKelvin(double celsiusTemperature);

    double convertKelvinToCelsius(double kelvinTemperature);

    double convertFahrenheitToCelsius(double fahrenheitTemperature);

    double convertCelsiusToFahrenheit(double celsiusTemperature);

    double convertKelvinToFahrenheit(double kelvinTemperature);

    double convertFahrenheitToKelvin(double fahrenheitTemperature);

    String[] getTemperaturesTypes();

    double convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo);
}
