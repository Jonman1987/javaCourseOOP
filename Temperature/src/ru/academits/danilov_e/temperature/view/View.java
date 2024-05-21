package ru.academits.danilov_e.temperature.view;

import ru.academits.danilov_e.temperature.controller.Controller;

public interface View {
    void run();

    void setController(Controller temperatureController);

    void showResultTemperature(double temperature);
}