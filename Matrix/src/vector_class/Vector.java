package vector_class;

import java.util.Arrays;

public class Vector {
    private double[] componentsValues;
    private int n;

    public Vector(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be > 0");
        }

        this.n = n;
        componentsValues = new double[n];
        Arrays.fill(componentsValues, 0);
    }

    public Vector(Vector vector) {
        this.n = vector.n;
        this.componentsValues = new double[vector.n];

        System.arraycopy(vector.componentsValues, 0, this.componentsValues, 0, vector.getComponentsArray().length);
    }

    public Vector(double[] array) {
        this.n = array.length;
        componentsValues = new double[array.length];
        System.arraycopy(array, 0, componentsValues, 0, array.length);
    }

    public Vector(int n, double[] array) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be > 0");
        }

        this.n = n;
        componentsValues = new double[n];

        for (int i = 0; i < n; i++) {
            if (i < array.length) {
                componentsValues[i] = array[i];
                continue;
            }

            componentsValues[i] = 0;
        }
    }

    public int getSize() {
        return n;
    }

    public double[] getComponentsArray() {
        return componentsValues;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < componentsValues.length; i++) {
            stringBuilder.append(componentsValues[i]);

            if (i != componentsValues.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public void makeAddition(Vector vector) {
        if (vector.n > this.n) {
            int tempN = vector.componentsValues.length;
            double[] temp = new double[tempN];

            for (int i = 0; i < tempN; i++) {
                if (i >= this.componentsValues.length) {
                    temp[i] = 0;
                } else {
                    temp[i] = this.componentsValues[i];
                }
            }

            this.n = tempN;
            this.componentsValues = temp;

            for (int i = 0; i < this.componentsValues.length; i++) {
                this.componentsValues[i] = this.componentsValues[i] + vector.componentsValues[i];
            }
        } else {
            for (int i = 0; i < vector.componentsValues.length; i++) {
                this.componentsValues[i] = this.componentsValues[i] + vector.componentsValues[i];
            }
        }
    }

    public void makeSubtraction(Vector vector) {
        if (vector.n > this.n) {
            int tempN = vector.componentsValues.length;
            double[] temp = new double[tempN];

            for (int i = 0; i < tempN; i++) {
                if (i >= this.componentsValues.length) {
                    temp[i] = 0;
                } else {
                    temp[i] = this.componentsValues[i];
                }
            }

            this.n = tempN;
            this.componentsValues = temp;

            for (int i = 0; i < this.componentsValues.length; i++) {
                this.componentsValues[i] = this.componentsValues[i] - vector.componentsValues[i];
            }
        } else {
            for (int i = 0; i < vector.componentsValues.length; i++) {
                this.componentsValues[i] = this.componentsValues[i] - vector.componentsValues[i];
            }
        }
    }

    public void makeMultiplication(double number) {
        for (int i = 0; i < componentsValues.length; i++) {
            componentsValues[i] = componentsValues[i] * number;
        }
    }

    public void makeReverse() {
        for (int i = 0; i < componentsValues.length; i++) {
            componentsValues[i] = componentsValues[i] * -1;
        }
    }

    public int getVectorLength() {
        int vectorLength = 0;
        final double EPSILON = 1.0e-10;

        for (int i = 0; i < componentsValues.length; i++) {
            if (componentsValues[i] > -EPSILON && componentsValues[i] < EPSILON) {
                continue;
            }

            vectorLength = i + 1;
        }

        return vectorLength;
    }

    public void editVectorComponent(int arrayIndex, double value) {
        if (arrayIndex < 0) {
            throw new IllegalArgumentException("Array index cannot be less than 0");
        }

        componentsValues[arrayIndex] = value;
    }

    public boolean equals(Object vector) {
        if (vector == this) {
            return true;
        }

        if (vector == null || vector.getClass() != getClass()) {
            return false;
        }

        Vector temp = (Vector) vector;

        if (this.n == temp.n) {
            for (int i = 0; i < this.n; i++) {
                if (this.componentsValues[i] != temp.componentsValues[i]) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (double componentsValue : componentsValues) {
            hash += prime * hash + Double.hashCode(componentsValue);
        }

        hash += prime * hash + n;

        return hash;
    }

    public static Vector makeSubtraction(Vector subtractableVector, Vector subtractiveVector) {
        Vector temp = new Vector(Math.max(subtractableVector.getSize(), subtractiveVector.getSize()));

        for (int i = 0; i < temp.getSize(); i++) {
            if (i < Math.min(subtractableVector.getSize(), subtractiveVector.getSize())) {
                temp.componentsValues[i] = subtractableVector.componentsValues[i] - subtractiveVector.componentsValues[i];
                continue;
            }

            if (subtractableVector.getSize() > subtractiveVector.getSize()) {
                temp.componentsValues[i] = subtractableVector.componentsValues[i];
            } else {
                temp.componentsValues[i] = 0 - subtractiveVector.componentsValues[i]; // Если убрать 0, то будет значение -0.0
            }
        }

        return temp;
    }

    public static Vector makeAddition(Vector summand1, Vector summand2) {
        Vector temp = new Vector(Math.max(summand1.getSize(), summand2.getSize()));

        for (int i = 0; i < temp.getSize(); i++) {
            if (i < Math.min(summand1.getSize(), summand2.getSize())) {
                temp.componentsValues[i] = summand1.componentsValues[i] + summand2.componentsValues[i];
                continue;
            }

            if (summand1.getSize() > summand2.getSize()) {
                temp.componentsValues[i] = summand1.componentsValues[i];
            } else {
                temp.componentsValues[i] = summand2.componentsValues[i];
            }
        }

        return temp;
    }

    public static Vector makeMultiplication(Vector multiplier1, Vector multiplier2) {
        Vector temp = new Vector(Math.max(multiplier1.getSize(), multiplier2.getSize()));

        for (int i = 0; i < temp.getSize(); i++) {
            if (i < Math.min(multiplier1.getSize(), multiplier2.getSize())) {
                temp.componentsValues[i] = multiplier1.componentsValues[i] * multiplier2.componentsValues[i];
                continue;
            }

            temp.componentsValues[i] = 0;
        }

        return temp;
    }
}
