package ru.academits.danilov_e.temperature.scalestypes;

public interface ScaleInterface {
    double convertToCelsius(double thisScaleTemperature);

    double convertFromCelsius(double celsiusTemperature);
}
