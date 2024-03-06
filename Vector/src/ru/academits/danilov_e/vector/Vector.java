package ru.academits.danilov_e.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int vectorDimension) {
        if (vectorDimension <= 0) {
            throw new IllegalArgumentException("Vector dimension is equal " + vectorDimension
                    + ". Vector dimension must be > 0");
        }

        components = new double[vectorDimension];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Array length is equal " + array.length + ". Array length must be > 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int vectorDimension, double[] array) {
        if (vectorDimension <= 0) {
            throw new IllegalArgumentException("Vector dimension is equal "
                    + vectorDimension + ". Vector dimension must be > 0");
        }

        components = Arrays.copyOf(array, vectorDimension);
    }

    public int getDimension() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (double component : components) {
            stringBuilder.append(component).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append('}');

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiply(double number) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= number;
        }
    }

    public void reverse() {
        multiply(-1);
    }

    public double getLength() {
        double squaresSum = 0;

        for (double component : components) {
            squaresSum += component * component;
        }

        return Math.sqrt(squaresSum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Vector component index is " + index
                    + ". Vector component index must belong to [0, " + (components.length - 1) + "]");
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Vector component index is " + index
                    + ". Vector component index must belong to [0, " + (components.length - 1) + "]");
        }

        components[index] = value;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) object;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector difference = new Vector(vector1);
        difference.subtract(vector2);

        return difference;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector sum = new Vector(vector1);
        sum.add(vector2);

        return sum;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minVectorDimension = Math.min(vector1.components.length, vector2.components.length);
        double scalarProduct = 0;

        for (int i = 0; i < minVectorDimension; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}
