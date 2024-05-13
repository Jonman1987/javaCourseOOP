package ru.academits.danilov_e.temperature.controller;

import ru.academits.danilov_e.temperature.model.Model;
import ru.academits.danilov_e.temperature.view.View;

public class BaseController implements Controller {
    private final Model model;
    private final View view;

    public BaseController(Model model, View view) {
        this.model = model;
        this.view = view;
        view.setController(this);
    }

    public void convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo) {
        view.showResultTemperature(model.convertTemperature(temperature, temperatureTypeFrom, temperatureTypeTo));
    }

    @Override
    public String[] getTemperaturesTypes() {
        return model.getTemperaturesTypes();
    }
}