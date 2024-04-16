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

    public void convertTemperature(double temperature, int temperatureTypeFrom, int temperatureTypeTo){
        double temperatureResult = 0;

        if(temperatureTypeFrom == 0 && temperatureTypeTo == 1){
            temperatureResult = model.convertCelsiusToKelvin(temperature);
        }else if(temperatureTypeFrom == 1 && temperatureTypeTo == 0){
            temperatureResult = model.convertKelvinToCelsius(temperature);
        }else if(temperatureTypeFrom == 0 && temperatureTypeTo == 2){
            temperatureResult = model.convertCelsiusToFahrenheit(temperature);
        }else if(temperatureTypeFrom == 2 && temperatureTypeTo == 0){
            temperatureResult = model.convertFahrenheitToCelsius(temperature);
        }else if(temperatureTypeFrom == 2 && temperatureTypeTo == 1){
            temperatureResult = model.convertFahrenheitToKelvin(temperature);
        }else if(temperatureTypeFrom == 1 && temperatureTypeTo == 2){
            temperatureResult = model.convertKelvinToFahrenheit(temperature);
        }else if(temperatureTypeFrom == temperatureTypeTo){
            temperatureResult = temperature;
        }

        view.showKelvinTemperature(temperatureResult);
    }
}
