package ru.academits.danilov_e.matrix_draft;

import ru.academits.danilov_e.vector.Vector;

public class MatrixDraft extends Vector {
    private int n;
    private int m;
    Vector[] vectorsArray;

    public MatrixDraft(int n, int m) {
        super(m);
        vectorsArray = new Vector[n];
        this.n = n;
        this.m = m;

        for (int i = 0; i < n; i++) {
            vectorsArray[i] = new Vector(m);
        }
    }

    public MatrixDraft(MatrixDraft matrix) {
        super(matrix.m);
        n = matrix.n;
        m = matrix.m;
        vectorsArray = matrix.vectorsArray;
    }

    public MatrixDraft(double[][] doubleArray) {
        super(doubleArray[0].length, doubleArray[0]);
        int maxVectorSize = doubleArray[0].length;

        for (int i = 1; i < doubleArray.length; i++) {
            if (maxVectorSize < doubleArray[i].length) {
                maxVectorSize = doubleArray[i].length;
            }
        }

        m = maxVectorSize;
        n = doubleArray.length;

        vectorsArray = new Vector[m];

        for (int i = 0; i < doubleArray.length; i++) {
            vectorsArray[i] = new Vector(m, doubleArray[i]);
        }
    }

    public MatrixDraft(Vector[] vector) {
        super(vector.length);
        n = vector.length;
        int maxVectorSize = vector[0].getDimension();

        for (int i = 1; i < vector.length; i++) {
            if (maxVectorSize < vector[i].getDimension()) {
                maxVectorSize = vector[i].getDimension();
            }
        }

        m = maxVectorSize;
        vectorsArray = new Vector[n];

        for (int i = 0; i < vector.length; i++) {
            double[] array = new double[m];

            for (int j = 0; j < vector.length; j++) {
                array[j] = vector[i].getComponent(j);
            }

            vectorsArray[i] = new Vector(m, array);
        }
    }

    public void print() {
        for (Vector vector : vectorsArray) {
            System.out.println(vector);
        }
    }

    public int getWidth() {
        return m;
    }

    public int getHeight() {
        return n;
    }

    public Vector getVector(int index) {
        return vectorsArray[index];
    }

    public void setVector(int index, Vector vector) {
        vectorsArray[index] = vector;
    }

    public Vector getColumnVector(int index) {
        double[] columnVector = new double[n];

        for (int i = 0; i < n; i++) {
            columnVector[i] = vectorsArray[i].getComponent(index);
        }

        return new Vector(columnVector);
    }

    public void transposition() {
        int maxVectorSize = vectorsArray[0].getDimension();

        for (int i = 1; i < vectorsArray.length; i++) {
            if (maxVectorSize < vectorsArray[i].getDimension()) {
                maxVectorSize = vectorsArray[i].getDimension();
            }
        }

        Vector[] vector = new Vector[maxVectorSize];

        for (int i = 0; i < maxVectorSize; i++) {
            vector[i] = this.getColumnVector(i);
        }

        vectorsArray = vector;
    }

    public void makeMultiplication(double number) {
        for (int i = 0; i < n; i++) {
            vectorsArray[i].multiply(number);
        }
    }

    public double matrixDeterminant() { // Не доделано
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

            MatrixDraft temp = new MatrixDraft(vectorsArray);

            while (temp.n > 2) {

            }

        }
        return 1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < n; i++) {
            stringBuilder.append(vectorsArray[i].toString());

            if (i != n - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append('}');

        return stringBuilder.toString();
    }

    public void makeMultiplication(Vector vector) {

    }
}

