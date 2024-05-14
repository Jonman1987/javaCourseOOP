package ru.academits.danilov_e.temperature.model;

import ru.academits.danilov_e.temperature.model.scales.Scale;

public interface Model {
    String[] getTemperaturesTypes();

    double convertTemperature(double temperature, int indexFrom, int indexTo);
}
