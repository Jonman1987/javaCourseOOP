package ru.academits.Danilov_E.Main;

import ru.academits.Danilov_E.Matrix.Matrix;
import ru.academits.Danilov_E.Vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array1 = new double[]{-3.4, 3.3, 5.7, 6.7};
        double[] array2 = new double[]{2.4, 1.3, -0.7, 3.7, 1.1, 5.6};
        double[] array3 = new double[]{6.7, -3.1, 0.0, 8.8, 2.7, 9.9, 4.2, 5.5};

        double[][] myArray = {{1.1, 2.2, 3.3}, {4.4, 5.5, 6.6}, {7.7, 8.8}};
        Matrix matrix = new Matrix(4, 8);
        matrix.print();
        Matrix matrix1 = new Matrix(matrix);
        System.out.println();
        matrix1.print();
        Matrix matrix2 = new Matrix(myArray);
        System.out.println();
        matrix2.print();

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectors = {vector1, vector2, vector3};

        Matrix matrix3 = new Matrix(vectors);
        System.out.println();
        matrix3.print();
        System.out.println();

        System.out.println(matrix3.getVector(1));
        System.out.println();

        matrix3.setVector(1, new Vector(8));
        matrix3.print();
        System.out.println();

        System.out.println(matrix3.getColumnVector(0));
        System.out.println();

        matrix3.transposition();
        matrix3.print();
        System.out.println();

        matrix2.print();
        System.out.println();
        matrix2.makeMultiplication(2);
        matrix2.print();
        System.out.println();

        System.out.println(matrix2);
        System.out.println();
    }
}