package ru.academits.danilov_e.temperature.model;

import ru.academits.danilov_e.temperature.scalestypes.*;

public class Converter implements ModelInterface {
    @Override
    public String[] getTemperaturesTypes() {
        return new String[]{"Градусы Цельсия", "Кельвины", "Фаренгейты", "Градусы Ранкина"};
    } // Я пока не придумал по какому принципу заполнять этот массив автоматически.

    @Override
    public double convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo) {
        double temperatureResult;

        ScaleInterface scaleFrom = null;
        ScaleInterface scaleTo = null;

        switch (temperatureTypeFrom) {
            case 0:
                scaleFrom = new Celsius();
                break;
            case 1:
                scaleFrom = new Kelvin();
                break;
            case 2:
                scaleFrom = new Fahrenheit();
                break;
            case 3:
                scaleFrom = new Rankin();
                break;
            default:
                System.out.println("Ошибка первоначальной шкалы");
        }

        switch (temperatureTypeTo) {
            case 0:
                scaleTo = new Celsius();
                break;
            case 1:
                scaleTo = new Kelvin();
                break;
            case 2:
                scaleTo = new Fahrenheit();
                break;
            case 3:
                scaleTo = new Rankin();
                break;
            default:
                System.out.println("Ошибка конечной шкалы");
        }

        temperatureResult = scaleTo.convertFromCelsius(scaleFrom.convertToCelsius(temperature));

        return temperatureResult;
    }
}
