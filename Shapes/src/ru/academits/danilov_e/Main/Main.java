package ru.academits.danilov_e.Main;

import ru.academits.danilov_e.Shapes.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Shape getFigureFromRating(Shape[] shapesArray, int position) {
        return shapesArray[shapesArray.length - position];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение места в рейтинге площади фигур:");
        int position = scanner.nextInt();

        Shape[] figuresArray = new Shape[]{new Square(2.4),
                new Triangle(2, -3, 1, 1, -6, 5),
                new Rectangle(5.6, 10.1), new Circle(4.4),
                new Circle(2.6), new Rectangle(7.7, 9.1), new Square(4.9)};

        System.out.println("Список площадей фигур: ");

        for (Shape shape : figuresArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println();
        }

        Arrays.sort(figuresArray);

        System.out.println("Список отсортированных площадей фигур: ");

        for (Shape shape : figuresArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println();
        }

        Shape findedShape = getFigureFromRating(figuresArray, position);

        System.out.println("Информация о фигуре: ");
        System.out.println(findedShape.toString());

        Shape[] simpleFiguresArray = new Shape[]{new Square(2.4),
                new Triangle(2, -3, 1, 1, -6, 5),
                new Rectangle(5.6, 10.1), new Square(2.4)};

        System.out.println();
        System.out.println("Проверка равенства:");

        System.out.println(simpleFiguresArray[0].equals(simpleFiguresArray[3]));
        System.out.println(simpleFiguresArray[0].equals(simpleFiguresArray[0]));
        System.out.println(simpleFiguresArray[0].equals(simpleFiguresArray[2]));

        System.out.println();
        System.out.println("Хэш код:");

        System.out.println(simpleFiguresArray[0].hashCode());
        System.out.println(simpleFiguresArray[1].hashCode());
        System.out.println(simpleFiguresArray[2].hashCode());
    }
}