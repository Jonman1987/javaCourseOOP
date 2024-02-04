package ru.academits.Danilov_E.Main;

import ru.academits.Danilov_E.Vector.Vector;

public class Main {
    public static void main(String[] args) {


        double[] array = new double[]{3.4, 3.3, 5.7, 6.7};
        double[] array1 = new double[]{2.4, 1.3, 0.7, 3.7};

        Vector vector = new Vector(4);
        Vector vector2 = new Vector(array);
        Vector vector1 = new Vector(array1);
        Vector vector3 = new Vector(4, array);
        Vector vector4 = new Vector(10, array);


        for (int i = 0; i < vector.getComponentsArray().length; i++) {
            System.out.println(vector.getComponentsArray()[i]);
        }

        String string = vector2.toString();

        System.out.println(string);

      //  vector2.makeSubtraction(vector1);
        string = vector4.toString();
        System.out.println(string);

        System.out.println(vector4.getVectorLength());

        vector4.editVectorComponent(5, 4.4);

        System.out.println(vector4.getVectorLength());
        string = vector4.toString();
        System.out.println(string);

        Vector vector5 = new Vector(4, array1);
        Vector vector6 = new Vector(4, array);
        System.out.println(vector5.equals(vector1));

        System.out.println(vector5.hashCode());

        Vector vector7 = Vector.makeSubtraction(vector5, vector4);
        string = vector7.toString();
        System.out.println(string);

        Vector vector8 = Vector.makeMultiplication(vector1, vector7);
        string = vector8.toString();
        System.out.println(string);
    }
}