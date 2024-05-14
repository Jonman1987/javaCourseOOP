package ru.academits.danilov_e.temperature.model;

import ru.academits.danilov_e.temperature.model.scales.Scale;

public interface Model {
    String[] getScalesNames();

    double convertTemperature(double temperature, Scale scaleFrom, Scale scaleTo);
    Scale getScale(int index);
}
