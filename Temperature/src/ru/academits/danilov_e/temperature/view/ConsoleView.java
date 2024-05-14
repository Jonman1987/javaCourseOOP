package ru.academits.danilov_e.temperature.view;

import ru.academits.danilov_e.temperature.controller.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView implements View {
    private Controller controller;

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Введите температуру:");
                double temperature = scanner.nextDouble();
                System.out.println();

                System.out.println("Введите шкалу введенного значения:");

                String[] scalesNames = controller.getScalesNames();

                for (int i = 0; i < scalesNames.length; i++) {
                    System.out.println((i + 1) + " - " + scalesNames[i]);
                }

                System.out.println();
                int indexFrom = scanner.nextInt();

                System.out.println("Введите шкалу в которое переводим значения:");

                for (int i = 0; i < scalesNames.length; i++) {
                    System.out.println((i + 1) + " - " + scalesNames[i]);
                }

                System.out.println();
                int indexTo = scanner.nextInt();

                controller.convertTemperature(temperature, indexFrom - 1, indexTo - 1);
            } catch (InputMismatchException e) {
                System.out.println("Температура должна быть числом");
                break;
            }
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showResultTemperature(double temperature) {
        System.out.println("Температура: " + Math.ceil(temperature * 100) / 100);
        System.out.println();
    }
}
