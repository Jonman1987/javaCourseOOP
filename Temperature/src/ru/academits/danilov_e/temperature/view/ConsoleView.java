package ru.academits.danilov_e.temperature.view;

import ru.academits.danilov_e.temperature.controller.ControllerInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView implements ViewInterface {
    private ControllerInterface controller;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Введите температуру:");
                double celsiusTemperature = scanner.nextDouble();
                System.out.println();

                System.out.println("Введите шкалу введенного значения:");

                String[] temperaturesTypes = controller.getTemperaturesTypes();

                for(int i = 0; i < temperaturesTypes.length; i++){
                    System.out.println((i + 1) + " - " + temperaturesTypes[i]);
                }

                System.out.println();
                int temperatureTypeFrom = scanner.nextInt();

                System.out.println("Введите шкалу в которое переводим значения:");

                for(int i = 0; i < temperaturesTypes.length; i++){
                    System.out.println((i + 1) + " - " + temperaturesTypes[i]);
                }

                System.out.println();
                int temperatureTypeTo = scanner.nextInt();

                controller.convertTemperature(celsiusTemperature, temperatureTypeFrom - 1, temperatureTypeTo - 1);
            } catch (InputMismatchException e) {
                System.out.println("Температура должна быть числом");
                break;
            }
        }
    }

    @Override
    public void setController(ControllerInterface controller) {
        this.controller = controller;
    }

    @Override
    public void showResultTemperature(double temperature) {
        System.out.println("Температура: " + temperature);
        System.out.println();
    }
}
