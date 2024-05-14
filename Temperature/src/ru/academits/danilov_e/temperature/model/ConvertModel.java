package ru.academits.danilov_e.temperature.model;

import ru.academits.danilov_e.temperature.model.scales.*;

import java.util.Arrays;

public class ConvertModel implements Model {
    Scale[] scales;

    public ConvertModel(Scale[] scales){
        this.scales = Arrays.copyOf(scales, scales.length);
    }

    public Scale getScale(int index){
        return scales[index];
    }
    @Override
    public String[] getScalesNames() {
        String[] scalesName = new String[scales.length];

        for(int i = 0; i < scales.length; i++){
            scalesName[i] = scales[i].toString();
        }

        return scalesName;
    }

    @Override
    public double convertTemperature(double temperature, Scale scaleFrom, Scale scaleTo) {
        return scaleTo.convertFromCelsius(scaleFrom.convertToCelsius(temperature));
    }
}