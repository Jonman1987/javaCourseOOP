package ru.academits.danilov_e.temperatureview;

import ru.academits.danilov_e.temperaturecontroller.TemperatureController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConsoleView implements TemperatureView{
    private TemperatureController temperatureController;
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true){
            try {
                System.out.println("Введите температуру в градусах цельсия:");
                double celsiusTemperature = scanner.nextDouble();
                temperatureController.convertCelsiusToKelvin(celsiusTemperature);
            }catch (InputMismatchException e){
                System.out.println("Температура должна быть числом");
                break;
            }
        }
    }

    @Override
    public void setController(TemperatureController temperatureController) {
        this.temperatureController = temperatureController;
    }

    @Override
    public void showKelvinTemperature(double kelvinTemperature) {
        System.out.println("Температура в градусах Кельвинах: " + kelvinTemperature);
    }
}
