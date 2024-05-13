package ru.academits.danilov_e.temperature.model;

import ru.academits.danilov_e.temperature.model.scales.*;

public class ConvertModel implements Model {
    @Override
    public String[] getTemperaturesTypes() {
        return new String[]{"Градусы Цельсия", "Кельвины", "Фаренгейты", "Градусы Ранкина"};
    } // Я пока не придумал по какому принципу заполнять этот массив автоматически.

    @Override
    public double convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo) {
        double temperatureResult;

        Scale scaleFrom = null;
        Scale scaleTo = null;

        switch (temperatureTypeFrom) {
            case 0:
                scaleFrom = new CelsiusScale();
                break;
            case 1:
                scaleFrom = new KelvinScale();
                break;
            case 2:
                scaleFrom = new FahrenheitScale();
                break;
            case 3:
                scaleFrom = new RankinScale();
                break;
            default:
                System.out.println("Ошибка первоначальной шкалы");
        }

        switch (temperatureTypeTo) {
            case 0:
                scaleTo = new CelsiusScale();
                break;
            case 1:
                scaleTo = new KelvinScale();
                break;
            case 2:
                scaleTo = new FahrenheitScale();
                break;
            case 3:
                scaleTo = new RankinScale();
                break;
            default:
                System.out.println("Ошибка конечной шкалы");
        }

        temperatureResult = scaleTo.convertFromCelsius(scaleFrom.convertToCelsius(temperature));

        return temperatureResult;
    }
}
