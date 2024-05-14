package ru.academits.danilov_e.temperature;

import ru.academits.danilov_e.temperature.controller.BaseController;
import ru.academits.danilov_e.temperature.model.ConvertModel;
import ru.academits.danilov_e.temperature.model.Model;
import ru.academits.danilov_e.temperature.model.scales.*;
import ru.academits.danilov_e.temperature.view.DesktopView;
import ru.academits.danilov_e.temperature.view.View;

public class TemperatureMain {
    public static void main(String[] args) {
        Scale[] scales = new Scale[]{new CelsiusScale(), new KelvinScale(), new FahrenheitScale(), new RankinScale()};

        Model model = new ConvertModel(scales);

        View view = new DesktopView();
        // ViewInterface view = new ConsoleView();

        new BaseController(model, view);
        view.run();
    }
}