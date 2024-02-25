package ru.academits.danilov_e.shapes_main;

import ru.academits.danilov_e.shapes.*;
import ru.academits.danilov_e.shapes_comparator.AreaComparator;
import ru.academits.danilov_e.shapes_comparator.PerimeterComparator;

import java.util.Arrays;
import java.util.Scanner;

public class ShapesMain {
    private static Shape getShapeWithAreaRating(Shape[] shapesArray, int indexFromEnd) {
        if (shapesArray.length == 0) {
            return null;
        }

        if (indexFromEnd < 0 || indexFromEnd >= shapesArray.length) {
            throw new IllegalArgumentException("Array index is " + indexFromEnd
                    + ". Index may belong to [0, " + (shapesArray.length - 1) + "]");
        }

        Arrays.sort(shapesArray, new AreaComparator());

        return shapesArray[shapesArray.length - indexFromEnd];
    }

    private static Shape getShapeWithPerimeterRating(Shape[] shapesArray, int indexFromEnd) {
        if (shapesArray.length == 0) {
            return null;
        }

        if (indexFromEnd < 0 || indexFromEnd >= shapesArray.length) {
            throw new IllegalArgumentException("Array index is " + indexFromEnd
                    + ". Index may belong to [0, " + (shapesArray.length - 1) + "]");
        }

        Arrays.sort(shapesArray, new PerimeterComparator());

        return shapesArray[shapesArray.length - indexFromEnd];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение позиции фигуры по размеру площади в списке фигур:");
        int position = scanner.nextInt();

        Shape[] shapesArray = {
                new Square(2.4),
                new Triangle(2, -3, 1, 1, -6, 5),
                new Rectangle(5.6, 10.1),
                new Circle(4.4),
                new Circle(2.6),
                new Rectangle(7.7, 9.1),
                new Square(4.9)
        };

        System.out.println("Список фигур с указанием размера площади и периметра:");
        System.out.println();

        for (Shape shape : shapesArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println();
        }

        Shape findedShape = getShapeWithAreaRating(shapesArray, position);
        System.out.println("Список фигур отсортированный, по размеру площади:");
        System.out.println();

        for (Shape shape : shapesArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println();
        }

        System.out.println("Информация о полученной фигуре:");
        System.out.println(findedShape);
        System.out.println();
        System.out.println("Введите значение позиции фигуры по размеру периметра в списке фигур:");
        position = scanner.nextInt();

        findedShape = getShapeWithPerimeterRating(shapesArray, position);
        System.out.println("Список отсортированных периметров фигур:");
        System.out.println();

        for (Shape shape : shapesArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println();
        }

        System.out.println("Информация о полученной фигуре:");
        System.out.println(findedShape);

        Shape[] simpleShapesArray = {
                new Square(2.4),
                new Triangle(2, -3, 1, 1, -6, 5),
                new Rectangle(5.6, 10.1),
                new Square(2.4)
        };

        System.out.println();
        System.out.println("Проверка равенства фигур:");
        System.out.println();
        System.out.println("Сравнение " + simpleShapesArray[0].getName() + " и " + simpleShapesArray[3].getName());
        System.out.println(simpleShapesArray[0].equals(simpleShapesArray[3]));
        System.out.println("Сравнение одной и той же фигуры:");
        System.out.println(simpleShapesArray[0].equals(simpleShapesArray[0]));
        System.out.println("Сравнение " + simpleShapesArray[0].getName() + " и " + simpleShapesArray[2].getName());
        System.out.println(simpleShapesArray[0].equals(simpleShapesArray[2]));

        System.out.println();
        System.out.println("Хэш код:");

        System.out.println(simpleShapesArray[0].hashCode());
        System.out.println(simpleShapesArray[1].hashCode());
        System.out.println(simpleShapesArray[2].hashCode());
    }
}