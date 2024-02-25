package matrix;

import ru.academits.danilov_e.vector.Vector;

public class Matrix extends Vector {
    Vector[] vectorsArray;

    public Matrix(int matrixHeight, int matrixWidth) {
        super(matrixWidth);

        vectorsArray = new Vector[matrixHeight];

        for (int i = 0; i < matrixHeight; i++) {
            vectorsArray[i] = new Vector(matrixWidth);
        }
    }

    public Matrix(Matrix matrix) { // Нужно ли проверять на not null?
        super(matrix.vectorsArray.length);

        vectorsArray = matrix.vectorsArray;
    }

    public Matrix(double[][] doubleArray) {
        super(doubleArray[0].length, doubleArray[0]);

        int maxVectorSize = doubleArray[0].length;

        for (int i = 1; i < doubleArray.length; i++) {
            if (maxVectorSize < doubleArray[i].length) {
                maxVectorSize = doubleArray[i].length;
            }
        }

        vectorsArray = new Vector[doubleArray.length];

        for (int i = 0; i < doubleArray.length; i++) {
            vectorsArray[i] = new Vector(maxVectorSize, doubleArray[i]);
        }
    }

    public Matrix(Vector[] vector) {
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
            vector[i] = getColumnVector(i);
        }

        vectorsArray = vector;
    }

    public void multiply(double number) {
        for (Vector vector : vectorsArray) {
            vector.multiply(number);
        }
    }

    public double matrixDeterminant() {
        if (vectorsArray.length == 1) {
            return vectorsArray[0].getComponent(0);
        }

        if (vectorsArray.length == 2) {
            return vectorsArray[0].getComponent(0) * vectorsArray[1].getComponent(1)
                    - vectorsArray[0].getComponent(1) * vectorsArray[1].getComponent(0);
        }

        double determinant = 0;
        int minusChanger = 1;

        for (int determinantColumn = 0; determinantColumn < vectorsArray[0].getDimension(); ++determinantColumn) {
            double[][] simplifiedMatrix = new double[vectorsArray.length - 1][vectorsArray.length - 1];
            int simplifiedMatrixRow = 0;

            for (int row = 1; row < vectorsArray.length; ++row) {
                int simplifiedMatrixColunm = 0;

                for (int column = 0; column < vectorsArray[0].getDimension(); ++column) {
                    if (column != determinantColumn) {
                        simplifiedMatrix[simplifiedMatrixRow][simplifiedMatrixColunm]
                                = vectorsArray[row].getComponent(column);

                        ++simplifiedMatrixColunm;
                    }
                }

                ++simplifiedMatrixRow;
            }

            determinant += (minusChanger * vectorsArray[0].getComponent(determinantColumn)
                    * (new Matrix(simplifiedMatrix).matrixDeterminant()));
            minusChanger *= -1;
        }

        return determinant;
    }

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

    public void add(Matrix matrix) {
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

    public void subtract(Matrix matrix) {
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

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
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

        return new Matrix(array);
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
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

        return new Matrix(array);
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        double[][] array = new double[matrix1.vectorsArray.length][matrix2.vectorsArray[0].getDimension()];

        if (matrix1.vectorsArray[0].getDimension() == matrix2.vectorsArray.length) {
            for (int i = 0; i < matrix1.vectorsArray.length; i++) {
                for (int j = 0; j < matrix2.vectorsArray[0].getDimension(); j++) {
                    for (int k = 0; k < matrix1.vectorsArray.length; k++) {
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

        return new Matrix(array);
    }
}

