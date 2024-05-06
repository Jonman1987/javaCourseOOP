package ru.academits.danilov_e.temperature.controller;

public interface ControllerInterface {
    void convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo);
    String[] getTemperaturesTypes();
}
