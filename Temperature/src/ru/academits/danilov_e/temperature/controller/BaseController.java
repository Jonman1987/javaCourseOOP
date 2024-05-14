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

    public void convertTemperature(double temperature, int indexFrom, int indexTo) {
        view.showResultTemperature(model.convertTemperature(temperature, model.getScale(indexFrom), model.getScale(indexTo)));
    }

    @Override
    public String[] getScalesNames() {
        return model.getScalesNames();
    }
}