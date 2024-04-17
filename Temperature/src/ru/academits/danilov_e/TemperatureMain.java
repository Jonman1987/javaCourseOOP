package ru.academits.danilov_e;

import ru.academits.danilov_e.temperaturecontroller.TemperatureController;
import ru.academits.danilov_e.temperaturemodel.TemperatureConverter;
import ru.academits.danilov_e.temperaturemodel.TemperatureModel;
import ru.academits.danilov_e.temperatureview.TemperatureConsoleView;
import ru.academits.danilov_e.temperatureview.TemperatureDesktopView;
import ru.academits.danilov_e.temperatureview.TemperatureView;

public class TemperatureMain {
    public static void main(String[] args) {
        TemperatureModel temperatureModel = new TemperatureConverter();

        TemperatureView view1 = new TemperatureDesktopView();
        // TemperatureView view2 = new TemperatureConsoleView();

        TemperatureController temperatureController = new TemperatureController(temperatureModel, view1);

        view1.setController(temperatureController);
        view1.run();
    }
}