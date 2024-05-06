package ru.academits.danilov_e.temperature;

import ru.academits.danilov_e.temperature.controller.Controller;
import ru.academits.danilov_e.temperature.model.Converter;
import ru.academits.danilov_e.temperature.model.ModelInterface;
import ru.academits.danilov_e.temperature.view.ConsoleView;
import ru.academits.danilov_e.temperature.view.DesktopView;
import ru.academits.danilov_e.temperature.view.ViewInterface;

public class TemperatureMain {
    public static void main(String[] args) {
        ModelInterface model = new Converter();

        ViewInterface view = new DesktopView();
        // ViewInterface view = new ConsoleView();

        Controller controller = new Controller(model, view);
        view.run();
    }
}