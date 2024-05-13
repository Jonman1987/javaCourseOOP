package ru.academits.danilov_e.temperature.model;

import ru.academits.danilov_e.temperature.model.scales.*;

public class ConvertModel implements Model {
    @Override
    public String[] getTemperaturesTypes() {
        return new String[]{"Градусы Цельсия", "Кельвины", "Фаренгейты", "Градусы Ранкина"};
    } // Я пока не придумал по какому принципу заполнять этот массив автоматически.

    @Override
    public double convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo) {
        Scale scaleFrom = switch (temperatureTypeFrom) {
            case 0 -> new CelsiusScale();
            case 1 -> new KelvinScale();
            case 2 -> new FahrenheitScale();
            case 3 -> new RankinScale();
            default -> throw new IllegalArgumentException(""); // TODO: добавить текст сообщения
        };

        Scale scaleTo = switch (temperatureTypeTo) {
            case 0 -> new CelsiusScale();
            case 1 -> new KelvinScale();
            case 2 -> new FahrenheitScale();
            case 3 -> new RankinScale();
            default -> throw new IllegalArgumentException(""); // TODO: добавить текст сообщения
        };

        return scaleTo.convertFromCelsius(scaleFrom.convertToCelsius(temperature));
    }
}