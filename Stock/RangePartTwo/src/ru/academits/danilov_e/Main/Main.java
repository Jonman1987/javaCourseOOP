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

        if (range.getIntersectionInterval(checkedIntervalFrom, checkedIntervalTo) != null) {
            System.out.println("Получили интервал пересечения:");
            System.out.printf("From: %.2f%n", range.getIntersectionInterval(checkedIntervalFrom, checkedIntervalTo).getFrom());
            System.out.printf("To: %.2f%n", range.getIntersectionInterval(checkedIntervalFrom, checkedIntervalTo).getTo());
        } else {
            System.out.println("Интервалы не пересекаются");
        }

        System.out.println("Получили объединенный интервал:");

        for (int i = 0; i < range.getCombinedIntervalArray(checkedIntervalFrom, checkedIntervalTo).length; i++) {
            System.out.printf("From: %.2f%n", range.getCombinedIntervalArray(checkedIntervalFrom, checkedIntervalTo)[i].getFrom());
            System.out.printf("To: %.2f%n", range.getCombinedIntervalArray(checkedIntervalFrom, checkedIntervalTo)[i].getTo());
        }

        if (range.getIntervalDifferenceArray(checkedIntervalFrom, checkedIntervalTo) != null) {
            System.out.println("Получили разность интервалов:");

            for (int i = 0; i < range.getIntervalDifferenceArray(checkedIntervalFrom, checkedIntervalTo).length; i++) {
                System.out.printf("From: %.2f%n", range.getIntervalDifferenceArray(checkedIntervalFrom, checkedIntervalTo)[i].getFrom());
                System.out.printf("To: %.2f%n", range.getIntervalDifferenceArray(checkedIntervalFrom, checkedIntervalTo)[i].getTo());
            }
        } else {
            System.out.println("Интервалы вычитаются без остатка.");
        }
    }
}