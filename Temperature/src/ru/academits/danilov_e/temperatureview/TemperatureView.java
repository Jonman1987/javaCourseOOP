package ru.academits.danilov_e.temperatureview;

import ru.academits.danilov_e.temperaturecontroller.TemperatureController;

public interface TemperatureView {
    void run();

    void setController(TemperatureController temperatureController);

    void showTemperatureResult(double temperature);
}
