package ru.academits.danilov_e.temperature.view;

import ru.academits.danilov_e.temperature.controller.ControllerInterface;

public interface ViewInterface {
    void run();

    void setController(ControllerInterface temperatureController);

    void showResultTemperature(double temperature);
}
