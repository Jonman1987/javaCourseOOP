package ru.academits.danilov_e.shapes_main;

import ru.academits.danilov_e.shapes.*;
import ru.academits.danilov_e.shapes_comparators.ShapeAreaComparator;
import ru.academits.danilov_e.shapes_comparators.ShapePerimeterComparator;

import java.util.Arrays;
import java.util.Scanner;

public class ShapesMain {
    private static Shape getShapeWithAreaRating(Shape[] shapes, int indexFromEnd) {
        if (shapes.length == 0) {
            throw new NullPointerException("Array is empty. Array length is " + shapes.length);
        }

        if (indexFromEnd <= 0 || indexFromEnd > shapes.length) {
            throw new ArrayIndexOutOfBoundsException("Array index is " + indexFromEnd
                    + ". Index may belong to [1, " + shapes.length + "]");
        }

        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - indexFromEnd];
    }

    private static Shape getShapeWithPerimeterRating(Shape[] shapes, int indexFromEnd) {
        if (shapes.length == 0) {
            throw new NullPointerException("Array is empty. Array length is " + shapes.length);
        }

        if (indexFromEnd <= 0 || indexFromEnd > shapes.length) {
            throw new ArrayIndexOutOfBoundsException("Array index is " + indexFromEnd
                    + ". Index may belong to [1, " + shapes.length + "]");
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - indexFromEnd];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение позиции фигуры по размеру площади в списке фигур:");
        int position = scanner.nextInt();

        Shape[] shapesArray = {
                new Square(2.4),
                new Triangle(2, -3, 1, 1, -6, 5),
                // new Triangle(1, 1, 2, 2, 3, 3), проверка на isTriangle
                new Rectangle(5.6, 10.1),
                new Circle(4.4),
                new Circle(2.6),
                new Rectangle(7.7, 9.1),
                new Square(4.9)
        };

        Shape foundShape = getShapeWithAreaRating(shapesArray, position);

        System.out.println("Информация о полученной фигуре:");
        System.out.println(foundShape);
        System.out.println();
        System.out.println("Список фигур отсортированный, по размеру площади:");
        System.out.println();

        for (Shape shape : shapesArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println();
        }

        System.out.println();
        System.out.println("Введите значение позиции фигуры по размеру периметра в списке фигур:");
        position = scanner.nextInt();

        foundShape = getShapeWithPerimeterRating(shapesArray, position);

        System.out.println("Информация о полученной фигуре:");
        System.out.println(foundShape);
        System.out.println();
        System.out.println("Список отсортированных периметров фигур:");
        System.out.println();

        for (Shape shape : shapesArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println();
        }

        Shape[] simpleShapesArray = {
                new Square(2.4),
                new Triangle(2, -3, 1, 1, -6, 5),
                new Rectangle(5.6, 10.1),
                new Square(2.4)
        };

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