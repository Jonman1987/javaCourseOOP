package matrix_class;

import ru.academits.danilov_e.vector.Vector;

public class Matrix2 extends Vector {
    Vector[] vectorsArray;

    public Matrix2(int matrixWidth, int matrixHeight) {
        super(matrixHeight);

        vectorsArray = new Vector[matrixWidth];

        for (int i = 0; i < matrixWidth; i++) {
            vectorsArray[i] = new Vector(matrixHeight);
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

        int maxVectorSize = vector[0].getDimension();

        for (int i = 1; i < vector.length; i++) {
            if (maxVectorSize < vector[i].getDimension()) {
                maxVectorSize = vector[i].getDimension();
            }
        }

        vectorsArray = new Vector[vector.length];

        for (int i = 0; i < vector.length; i++) {
            double[] array = new double[maxVectorSize];

            for (int j = 0; j < vector.length; j++) { // Не создается далее вектор. Размерность больше чем строк в массиве
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
        return vectorsArray[0].getDimension();
    }

    public int getHeight() {
        return vectorsArray.length;
    }

    public Vector getVector(int index) {
        if (index < 0 || index > vectorsArray.length - 1) {
            throw new IllegalArgumentException("The index must belong to the range [0; " + (vectorsArray.length - 1)
                    + "]. Index is " + index + ".");
        }

        return vectorsArray[index];
    }

    public void setVector(int index, Vector vector) {
        if (index < 0 || index > vectorsArray.length - 1) {
            throw new IllegalArgumentException("The index must belong to the range [0; " + (vectorsArray.length - 1)
                    + "]. Index is " + index + ".");
        }

        vectorsArray[index] = vector;
    }

    public Vector getColumnVector(int index) {
        if (index < 0 || index > vectorsArray[0].getDimension() - 1) {
            throw new IllegalArgumentException("The index must belong to the range [0; "
                    + (vectorsArray[0].getDimension() - 1) + "]. Index is " + index + ".");
        }

        double[] columnVector = new double[vectorsArray.length];

        for (int i = 0; i < vectorsArray.length; i++) {
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
            for (int j = 0; j < vectorsArray[0].getDimension(); j++) {
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
                && vectorsArray[0].getDimension() == matrix.vectorsArray[0].getDimension()) {
            for (int i = 0; i < vectorsArray.length; i++) {
                for (int j = 0; j < vectorsArray[0].getDimension(); j++) {
                    vectorsArray[i].setComponent(j,
                            vectorsArray[i].getComponent(j) + matrix.vectorsArray[i].getComponent(j));
                }
            }
        } else {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is ("
                    + vectorsArray.length + "; " + vectorsArray[0].getDimension() + "). Dimension of matrix2 is ("
                    + matrix.vectorsArray.length + "; " + matrix.vectorsArray[0].getDimension() + ").");
        }
    }

    public void subtract(Matrix2 matrix) {
        if (vectorsArray.length == matrix.vectorsArray.length
                && vectorsArray[0].getDimension() == matrix.vectorsArray[0].getDimension()) {
            for (int i = 0; i < vectorsArray.length; i++) {
                for (int j = 0; j < vectorsArray[0].getDimension(); j++) {
                    vectorsArray[i].setComponent(j,
                            vectorsArray[i].getComponent(j) - matrix.vectorsArray[i].getComponent(j));
                }
            }
        } else {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is ("
                    + vectorsArray.length + "; " + vectorsArray[0].getDimension() + "). Dimension of matrix2 is ("
                    + matrix.vectorsArray.length + "; " + matrix.vectorsArray[0].getDimension() + ").");
        }
    }

    public static Matrix2 add(Matrix2 matrix1, Matrix2 matrix2) {
        double[][] array = new double[matrix1.vectorsArray.length][matrix1.vectorsArray[0].getDimension()];

        if (matrix1.vectorsArray.length == matrix2.vectorsArray.length
                && matrix1.vectorsArray[0].getDimension() == matrix2.vectorsArray[0].getDimension()) {
            for (int i = 0; i < matrix1.vectorsArray.length; i++) {
                for (int j = 0; j < matrix1.vectorsArray[0].getDimension(); j++) {
                    array[i][j] = matrix1.vectorsArray[i].getComponent(j) + matrix2.vectorsArray[i].getComponent(j);
                }
            }
        } else {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is ("
                    + matrix1.vectorsArray.length + "; " + matrix1.vectorsArray[0].getDimension()
                    + "). Dimension of matrix2 is (" + matrix2.vectorsArray.length + "; "
                    + matrix2.vectorsArray[0].getDimension() + ").");
        }

        return new Matrix2(array);
    }

    public static Matrix2 subtract(Matrix2 matrix1, Matrix2 matrix2) {
        double[][] array = new double[matrix1.vectorsArray.length][matrix1.vectorsArray[0].getDimension()];

        if (matrix1.vectorsArray.length == matrix2.vectorsArray.length
                && matrix1.vectorsArray[0].getDimension() == matrix2.vectorsArray[0].getDimension()) {
            for (int i = 0; i < matrix1.vectorsArray.length; i++) {
                for (int j = 0; j < matrix1.vectorsArray[0].getDimension(); j++) {
                    array[i][j] = matrix1.vectorsArray[i].getComponent(j) - matrix2.vectorsArray[i].getComponent(j);
                }
            }
        } else {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is ("
                    + matrix1.vectorsArray.length + "; " + matrix1.vectorsArray[0].getDimension()
                    + "). Dimension of matrix2 is (" + matrix2.vectorsArray.length + "; "
                    + matrix2.vectorsArray[0].getDimension() + ").");
        }

        return new Matrix2(array);
    }

    public static Matrix2 multiply(Matrix2 matrix1, Matrix2 matrix2) {
        int matrixWidth = Math.max(matrix1.getWidth(), matrix2.getHeight());
        int matrixHeight = Math.max(matrix1.getHeight(), matrix2.getWidth());
        double[][] array = new double[matrix1.vectorsArray.length][matrix2.vectorsArray[0].getDimension()];

        if (matrix1.vectorsArray[0].getDimension() == matrix2.vectorsArray.length) {
            for (int i = 0; i < matrix1.vectorsArray.length; i++) {
                for (int j = 0; j < matrix2.vectorsArray[0].getDimension(); j++) {
                    for(int k = 0; k < matrix1.vectorsArray.length; k++){
                        array[i][j] += matrix1.getVector(i).getComponent(k) * matrix2.getColumnVector(j).getComponent(k);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Both matrix must be same dimension or transposition size. Dimension of matrix1 is ("
                    + matrix1.vectorsArray.length + "; " + matrix1.vectorsArray[0].getDimension()
                    + "). Dimension of matrix2 is (" + matrix2.vectorsArray.length + "; "
                    + matrix2.vectorsArray[0].getDimension() + ").");
        }

        return new Matrix2(array);
    }
}

