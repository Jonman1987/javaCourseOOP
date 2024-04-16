package ru.academits.danilov_e.temperaturemodel;

public interface TemperatureModel {
    double convertCelsiusToKelvin(double celsiusTemperature);
    double convertKelvinToCelsius(double kelvinTemperature);
    double convertFahrenheitToCelsius(double fahrenheitTemperature);
    double convertCelsiusToFahrenheit(double celsiusTemperature);
    double convertKelvinToFahrenheit(double kelvinTemperature);
    double convertFahrenheitToKelvin(double fahrenheitTemperature);

}
