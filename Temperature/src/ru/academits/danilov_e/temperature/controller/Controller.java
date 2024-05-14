package ru.academits.danilov_e.temperature.controller;

import ru.academits.danilov_e.temperature.model.scales.Scale;

public interface Controller {
    void convertTemperature(double temperature, int scaleFrom, int scaleTo);

    String[] getTemperaturesTypes();
}
