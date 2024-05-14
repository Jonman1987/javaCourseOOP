package ru.academits.danilov_e.temperature.model.scales;

public interface Scale {
    double convertToCelsius(double thisScaleTemperature);

    double convertFromCelsius(double celsiusTemperature);
    @Override
    String toString();
}
