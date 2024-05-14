package ru.academits.danilov_e.temperature.model;

public interface Model {
    String[] getScalesNames();

    double convertTemperature(double temperature, int indexFrom, int indexTo);
}
