package ru.academits.danilov_e.temperature.model;

import ru.academits.danilov_e.temperature.scalestypes.Celsius;
import ru.academits.danilov_e.temperature.scalestypes.Fahrenheit;
import ru.academits.danilov_e.temperature.scalestypes.Kelvin;
import ru.academits.danilov_e.temperature.scalestypes.ScaleInterface;

public class Converter implements ModelInterface {
    @Override
    public String[] getTemperaturesTypes() {
        return new String[]{"Градусы Цельсия", "Кельвины", "Фаренгейты"};
    } // Я пока не придумал по какому принципу заполнять этот массив автоматически.

    @Override
    public double convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo) {
        double temperatureResult;

        ScaleInterface scaleFrom = null;
        ScaleInterface scaleTo = null;

        switch (temperatureTypeFrom) {
            case 0:
                scaleFrom = new Celsius(temperature);
                break;
            case 1:
                scaleFrom = new Kelvin(temperature);
                break;
            case 2:
                scaleFrom = new Fahrenheit(temperature);
                break;
            default:
                System.out.println("Ошибка первоначальной шкалы");
        }

        switch (temperatureTypeTo) {
            case 0:
                scaleTo = new Celsius(temperature);
                break;
            case 1:
                scaleTo = new Kelvin(temperature);
                break;
            case 2:
                scaleTo = new Fahrenheit(temperature);
                break;
            default:
                System.out.println("Ошибка конечной шкалы");
        }

        temperatureResult = scaleTo.convertFromCelsius(scaleFrom.convertToCelsius(temperature));

        return temperatureResult;
    }
}
