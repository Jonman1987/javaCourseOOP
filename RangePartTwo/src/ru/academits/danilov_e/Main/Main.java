package ru.academits.danilov_e.Main;

import ru.academits.danilov_e.Range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное число первого диапазона:");
        double from = scanner.nextDouble();

        System.out.println("Введите конечное число первого диапазона:");
        double to = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.println("Введите начальное число второго диапазона:");
        double checkedIntervalFrom = scanner.nextDouble();

        System.out.println("Введите конечное число второго диапазона:");
        double checkedIntervalTo = scanner.nextDouble();

        if (range.intersection(checkedIntervalFrom, checkedIntervalTo) != null) {
            System.out.printf("Получили интервал пересечения:%n");
            System.out.printf("From: %.2f%n", range.intersection(checkedIntervalFrom, checkedIntervalTo).getFrom());
            System.out.printf("To: %.2f%n", range.intersection(checkedIntervalFrom, checkedIntervalTo).getTo());
        } else {
            System.out.printf("Интервалы не пересекаются%n");
        }

    }
}