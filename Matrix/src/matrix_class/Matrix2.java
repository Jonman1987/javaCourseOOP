package matrix_class;

import ru.academits.danilov_e.vector.Vector;

public class Matrix2 extends Vector {
    Vector[] vectorsArray;

    public Matrix2(int n, int m) {
        super(m);

        vectorsArray = new Vector[n];

        for (int i = 0; i < n; i++) {
            vectorsArray[i] = new Vector(m);
        }
    }

    public Matrix2(Matrix2 matrix) {
        super(matrix.vectorsArray.length);

        vectorsArray = matrix.vectorsArray;
    }

    public Matrix2(double[][] doubleArray) {
        super(doubleArray[0].length, doubleArray[0]);

        int maxVectorSize = doubleArray[0].length;

        for (int i = 1; i < doubleArray.length; i++) {
            if (maxVectorSize < doubleArray[i].length) {
                maxVectorSize = doubleArray[i].length;
            }
        }

        vectorsArray = new Vector[maxVectorSize];

        for (int i = 0; i < doubleArray.length; i++) {
            vectorsArray[i] = new Vector(maxVectorSize, doubleArray[i]);
        }
    }

    public Matrix2(Vector[] vector) {
        super(vector.length);

        int maxVectorSize = vector[0].getSize();

        for (int i = 1; i < vector.length; i++) {
            if (maxVectorSize < vector[i].getSize()) {
                maxVectorSize = vector[i].getSize();
            }
        }

        vectorsArray = new Vector[vector.length];

        for (int i = 0; i < vector.length; i++) {
            double[] array = new double[maxVectorSize];

            for (int j = 0; j < vector.length; j++) {
                array[j] = vector[i].getComponent(j);
            }

            vectorsArray[i] = new Vector(maxVectorSize, array);
        }
    }

    public void print() {
        for (Vector vector : vectorsArray) {
            System.out.println(vector);
        }
    }

    public int getWidth() {
        return vectorsArray[0].getSize();
    }

    public int getHeight() {
        return vectorsArray.length;
    }

    public Vector getVector(int index) {
        // Кинуть исключение

        return vectorsArray[index];
    }

    public void setVector(int index, Vector vector) {
        // Кинуть исключение

        vectorsArray[index] = vector;
    }

    public Vector getColumnVector(int index) {
        // Кинуть исключение

        double[] columnVector = new double[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            columnVector[i] = vectorsArray[i].getComponent(index);
        }

        return new Vector(columnVector);
    }

    public void transposition() {
        int maxVectorSize = vectorsArray[0].getSize();

        for (int i = 1; i < vectorsArray.length; i++) {
            if (maxVectorSize < vectorsArray[i].getSize()) {
                maxVectorSize = vectorsArray[i].getSize();
            }
        }

        Vector[] vector = new Vector[maxVectorSize];

        for (int i = 0; i < maxVectorSize; i++) {
            vector[i] = this.getColumnVector(i);
        }

        vectorsArray = vector;
    }

    public void multiply(double number) {
        for (Vector vector : vectorsArray) {
            vector.multiply(number);
        }
    }

   /* public double matrixDeterminant() { // Не доделано
        final double EPSILON = 1.0e-10;

        if (n != m) {
            throw new IllegalArgumentException("n must be equal m");
        }

        if (n == 1) {
            return vectorsArray[0].getComponent(0);
        }

        if (n == 2) {
            return vectorsArray[0].getComponent(0) * vectorsArray[1].getComponent(1)
                    - vectorsArray[0].getComponent(0) * vectorsArray[0].getComponent(1);
        } else {
            double determinant;

            Matrix2 temp = new Matrix2(vectorsArray);

            while (temp.n > 2) {

            }

        }
        return 1;
    }*/

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < vectorsArray.length; i++) {
            stringBuilder.append(vectorsArray[i].toString());

            if (i != vectorsArray.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public void multiply(Vector vector) {
        double[] array = new double[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
            for (int j = 0; j < vectorsArray[0].getSize(); j++) {
                array[i] += vectorsArray[i].getComponent(j) * vector.getComponent(j);
            }

            // Я пытался вызвать команду vectorsArray[i] = new Vector(1).setComponent(0, array[i]);
            // но почему-то так сделать нельзя.
            Vector unitVector = new Vector(1);
            unitVector.setComponent(0, array[i]);
            vectorsArray[i] = unitVector;
        }
    }

    public void add(Matrix2 matrix) {
        if (vectorsArray.length == matrix.vectorsArray.length
                && vectorsArray[0].getSize() == matrix.vectorsArray[0].getSize()) {
            for (int i = 0; i < vectorsArray.length; i++) {
                for (int j = 0; j < vectorsArray[0].getSize(); j++) {
                    vectorsArray[i].setComponent(j,
                            vectorsArray[i].getComponent(j) + matrix.vectorsArray[i].getComponent(j));
                }
            }
        }else {
            System.out.println("Ошибка - сложение матриц разной размерности.");
            // Кинуть исключение
        }
    }

    public void subtract(Matrix2 matrix) {
        if (vectorsArray.length == matrix.vectorsArray.length
                && vectorsArray[0].getSize() == matrix.vectorsArray[0].getSize()) {
            for (int i = 0; i < vectorsArray.length; i++) {
                for (int j = 0; j < vectorsArray[0].getSize(); j++) {
                    vectorsArray[i].setComponent(j,
                            vectorsArray[i].getComponent(j) - matrix.vectorsArray[i].getComponent(j));
                }
            }
        }else {
            System.out.println("Ошибка - вычитание матриц разной размерности.");
            // Кинуть исключение
        }
    }
}

