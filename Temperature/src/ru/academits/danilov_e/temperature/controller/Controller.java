package ru.academits.danilov_e.temperature.controller;

public interface Controller {
    void convertTemperature(double temperature, int indexFrom, int indexTo);

    String[] getScalesNames();
}
