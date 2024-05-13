package ru.academits.danilov_e.temperature.model;

public interface Model {
    String[] getTemperaturesTypes();

    double convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo);
}
