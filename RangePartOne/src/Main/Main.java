package Main;
import java.util.Scanner;
import Range.Range;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите начальное число диапазона:");
        double from = scanner.nextDouble();

        System.out.println("Введите конечное число диапазона:");
        double to = scanner.nextDouble();

        Range range = new Range(from, to);

        System.out.printf("Создали объект класса с параметрами (%.2f, %.2f):%n", from, to);
        System.out.printf("From: %.2f%n", range.getFrom());
        System.out.printf("To: %.2f%n", range.getTo());

        System.out.println("Введите новое начальное число диапазона:");
        double newFrom = scanner.nextDouble();

        System.out.println("Введите новое конечное число диапазона:");
        double newTo = scanner.nextDouble();

        range.setFrom(newFrom);
        range.setTo(newTo);
        System.out.println("Выведем новые значения From и To:");
        System.out.printf("From: %.2f%n", range.getFrom());
        System.out.printf("To: %.2f%n", range.getTo());

        System.out.println("Найдем длину диапазона между From и To:");
        System.out.printf("Длина диапазона равна: %.2f%n", range.getLength());

        System.out.println("Введите число для проверки его принадлежности диапазону:");
        double number = scanner.nextDouble();

        System.out.printf("Проверим входит ли число %.2f в диапазон:%n", number);
        String isNumberInside = range.isInside(number) ? "принадлежит диапазону" : "не принадлежит диапазону";
        System.out.printf("Число %.2f %s", number, isNumberInside);
    }
}