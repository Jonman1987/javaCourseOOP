package ru.academits.danilov_e.range_main;

import ru.academits.danilov_e.range.Range;

import java.util.Arrays;
import java.util.Scanner;

public class RangeMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное число диапазона:");
        double from = scanner.nextDouble();

        System.out.println("Введите конечное число диапазона:");
        double to = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.printf("Создали объект класса с параметрами (%.2f, %.2f):%n", from, to);
        System.out.println(range);
        System.out.println();

        System.out.println("Введите новое начальное число диапазона:");
        double newFrom = scanner.nextDouble();

        System.out.println("Введите новое конечное число диапазона:");
        double newTo = scanner.nextDouble();

        range.setFrom(newFrom);
        range.setTo(newTo);
        System.out.println("Выведем новые значения диапазона:");
        System.out.println(range);
        System.out.println();

        System.out.printf("Найдем длину диапазона между %.2f и %.2f:%n", newFrom, newTo);
        System.out.printf("Длина диапазона равна: %.2f%n", range.getLength());
        System.out.println();

        System.out.println("Введите число для проверки его принадлежности диапазону:");
        double number = scanner.nextDouble();

        System.out.println();

        System.out.printf("Проверим входит ли число %.2f в диапазон:%n", number);
        String isNumberInsideMessage = range.isInside(number) ? "принадлежит диапазону" : "не принадлежит диапазону";
        System.out.printf("Число %.2f %s", number, isNumberInsideMessage);
        System.out.println();

        System.out.println("Введите начальное число второго диапазона:");
        double checkedRangeFrom = scanner.nextDouble();

        System.out.println("Введите конечное число второго диапазона:");
        double checkedRangeTo = scanner.nextDouble();

        Range checkedRange = new Range(checkedRangeFrom, checkedRangeTo);

        if (range.getIntersection(checkedRange) == null) {
            System.out.println("Диапазоны не пересекаются");
        } else {
            System.out.println("Получили диапазон пересечения:");
            System.out.println(range.getIntersection(checkedRange));
        }

        System.out.println();
        System.out.println("Объединение диапазонов:");
        System.out.println(Arrays.toString(range.getUnion(checkedRange)));
        System.out.println();

        if (range.getDifference(checkedRange).length == 0) {
            System.out.println("Диапазоны вычитаются без остатка.");
        } else {
            System.out.println("Получили разность диапазонов:");
            System.out.println(Arrays.toString(range.getDifference(checkedRange)));
        }
    }
}