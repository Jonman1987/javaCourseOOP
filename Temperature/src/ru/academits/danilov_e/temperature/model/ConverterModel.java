package ru.academits.danilov_e.temperature.model;

import ru.academits.danilov_e.temperature.model.scales.*;

import java.util.Arrays;

public class ConverterModel implements Model {
    private final Scale[] scales;

    public ConverterModel(Scale[] scales) {
        this.scales = Arrays.copyOf(scales, scales.length);
    }

    public Scale getScale(int index) {
        return scales[index];
    }

    @Override
    public String[] getScalesNames() {
        String[] scalesNames = new String[scales.length];

        for (int i = 0; i < scales.length; i++) {
            scalesNames[i] = scales[i].toString();
        }

        return scalesNames;
    }

    @Override
    public double convertTemperature(double temperature, Scale scaleFrom, Scale scaleTo) {
        return scaleTo.convertFromCelsius(scaleFrom.convertToCelsius(temperature));
    }
}