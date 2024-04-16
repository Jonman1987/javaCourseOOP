package ru.academits.danilov_e.temperaturecontroller;

import ru.academits.danilov_e.temperaturemodel.TemperatureModel;
import ru.academits.danilov_e.temperatureview.TemperatureView;

public class TemperatureController {
    private final TemperatureModel model;
    private final TemperatureView view;

    public TemperatureController(TemperatureModel model, TemperatureView view) {
        this.model = model;
        this.view = view;
    }

    public void convertCelsiusToKelvin(double celsiusTemperature){
        double kalvinTemperature =  model.convertCelsiusToKelvin(celsiusTemperature);
        view.showKelvinTemperature(kalvinTemperature);
    }
}
