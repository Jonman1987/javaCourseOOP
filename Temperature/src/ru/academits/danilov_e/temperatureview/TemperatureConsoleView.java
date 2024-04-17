package ru.academits.danilov_e.temperatureview;

import ru.academits.danilov_e.temperaturecontroller.TemperatureController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConsoleView implements TemperatureView {
    private TemperatureController temperatureController;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Введите температуру в градусах цельсия:");
                double celsiusTemperature = scanner.nextDouble();
                System.out.println();

                System.out.println("Введите шкалу введенного значения:");
                System.out.println("0 - градусы Цельсия");
                System.out.println("1 - градусы Кельвина");
                System.out.println("2 - градусы Фаренгейта");
                System.out.println();
                int temperatureTypeFrom = scanner.nextInt();

                System.out.println("Введите шкалу в которое переводим значения:");
                System.out.println("0 - градусы Цельсия");
                System.out.println("1 - градусы Кельвина");
                System.out.println("2 - градусы Фаренгейта");
                System.out.println();
                int temperatureTypeTo = scanner.nextInt();

                temperatureController.convertTemperature(celsiusTemperature, temperatureTypeFrom, temperatureTypeTo);
            } catch (InputMismatchException e) {
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
    public void showTemperatureResult(double temperature) {
        System.out.println("Температура в градусах Кельвинах: " + temperature);
        System.out.println();
    }
}
