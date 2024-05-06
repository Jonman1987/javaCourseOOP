package ru.academits.danilov_e.temperature.model;

public interface ModelInterface {
    String[] getTemperaturesTypes();

    double convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo);
}
