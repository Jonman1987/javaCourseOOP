package ru.academits.Danilov_E.Main;

import ru.academits.Danilov_E.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        int n = 2;
        double[] array = new double[]{3.4, 3.3, 5.7, 6.7};
        double[] array1 = new double[]{2.4, 1.3, 0.7, 3.7};

        Vector vector = new Vector(4);
        Vector vector2 = new Vector(array);
        Vector vector1 = new Vector(array1);
        Vector vector3 = new Vector(n, array);

        for (int i = 0; i < vector.getComponentsArray().length; i++) {
            System.out.println(vector.getComponentsArray()[i]);
        }

        String string = vector2.toString();

        System.out.println(string);

        vector2.makeSubtraction(vector1);
        string = vector2.toString();
        System.out.println(string);
    }
}