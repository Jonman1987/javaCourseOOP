package ru.academits.danilov_e.temperature.controller;

import ru.academits.danilov_e.temperature.model.ModelInterface;
import ru.academits.danilov_e.temperature.view.ViewInterface;

public class Controller implements ControllerInterface{
    private final ModelInterface model;
    private final ViewInterface view;

    public Controller(ModelInterface model, ViewInterface view) {
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
