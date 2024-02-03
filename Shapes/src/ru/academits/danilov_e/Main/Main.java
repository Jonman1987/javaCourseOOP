package ru.academits.danilov_e.Main;

import ru.academits.danilov_e.Shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] figuresArray = new Shape[]{new Square(2.4),
                new Triangle(1.2, 1.1, 2.2, 2.4, 5.5, 6.5),
                new Rectangle(5.6, 10.1), new Circle(4.4)};

        for (int i = 0; i < figuresArray.length; i++){
            System.out.println(figuresArray[i].getArea());
        }

        Arrays.sort(figuresArray);
        System.out.println();

        for (int i = 0; i < figuresArray.length; i++){
            System.out.println(figuresArray[i].getArea());
        }
    }
}