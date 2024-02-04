package ru.academits.Danilov_E.Vector;

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

    public String toString() { // Изначально делал без StringBuilder, но IDEA рекомендовала его использовать
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < componentsValues.length; i++) {
            stringBuilder.append(componentsValues[i]);

            if (i != componentsValues.length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("}");

        return String.valueOf(stringBuilder);
    }

    public void makeAddition(Vector vector){
        for(int i = 0; i < Math.min(this.componentsValues.length, vector.componentsValues.length); i++){
            this.componentsValues[i] = this.componentsValues[i] + vector.componentsValues[i];
        }
    }

    public void makeSubtraction(Vector vector){
        for(int i = 0; i < Math.min(this.componentsValues.length, vector.componentsValues.length); i++){
            this.componentsValues[i] = this.componentsValues[i] - vector.componentsValues[i];
        }
    }

    public void makeMultiplication(double number){
        for(int i = 0; i < componentsValues.length; i++){
            componentsValues[i] = componentsValues[i] * number;
        }
    }

    public void makeReverse(){
        for(int i = 0; i < componentsValues.length; i++){
            componentsValues[i] = componentsValues[i] * -1;
        }
    }


}
