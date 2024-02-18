package shapes_main;

// пункт 17

import shapes_class.*;


import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Shape getShapeFromRating(Shape[] shapesArray, int position) {
        return shapesArray[shapesArray.length - position];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение места в рейтинге площади фигур:");
        int position = scanner.nextInt();

        Shape[] shapesArray = new Shape[]{new Square(2.4),
                new Triangle(2, -3, 1, 1, -6, 5),
                new Rectangle(5.6, 10.1), new Circle(4.4),
                new Circle(2.6), new Rectangle(7.7, 9.1), new Square(4.9)};

        System.out.println("Список площадей фигур: ");

        for (Shape shape : shapesArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println();
        }

        Arrays.sort(shapesArray, new AreaComparator());

        System.out.println("Список отсортированных площадей фигур: ");

        for (Shape shape : shapesArray) {
            System.out.println(shape.getName());
            System.out.println("Площадь: " + shape.getArea());
            System.out.println("Периметр: " + shape.getPerimeter());
            System.out.println();
        }

        Shape findedShape = getShapeFromRating(shapesArray, position);

        System.out.println("Информация о фигуре: ");
        System.out.println(findedShape.toString());

        Arrays.sort(shapesArray, new PerimeterComparator());
        findedShape = getShapeFromRating(shapesArray, position);
        System.out.println("Информация о фигуре: ");
        System.out.println(findedShape.toString());

        Shape[] simpleShapesArray = new Shape[]{new Square(2.4),
                new Triangle(2, -3, 1, 1, -6, 5),
                new Rectangle(5.6, 10.1), new Square(2.4)};

        System.out.println();
        System.out.println("Проверка равенства:");

        System.out.println(simpleShapesArray[0].equals(simpleShapesArray[3]));
        System.out.println(simpleShapesArray[0].equals(simpleShapesArray[0]));
        System.out.println(simpleShapesArray[0].equals(simpleShapesArray[2]));

        System.out.println();
        System.out.println("Хэш код:");

        System.out.println(simpleShapesArray[0].hashCode());
        System.out.println(simpleShapesArray[1].hashCode());
        System.out.println(simpleShapesArray[2].hashCode());
    }
}