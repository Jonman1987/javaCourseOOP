package ru.academits.danilov_e.matrix;

import ru.academits.danilov_e.vector.Vector;

public class Matrix {
    private Vector[] lines;

    public Matrix(int matrixLinesCount, int matrixColumnCount) {
        lines = new Vector[matrixLinesCount];

        for (int i = 0; i < matrixLinesCount; i++) {
            lines[i] = new Vector(matrixColumnCount);
        }
    }

    public Matrix(Matrix matrix) { // Нужно ли проверять на not null?
        Vector[] vector = new Vector[matrix.getMatrixLinesDimension()];

        for (int i = 0; i < matrix.getMatrixLinesDimension(); i++) {
            vector[i] = matrix.getLine(i);
        }

        lines = vector;
    }

    public Matrix(double[][] array) {
        int maxVectorDimension = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (maxVectorDimension < array[i].length) {
                maxVectorDimension = array[i].length;
            }
        }

        lines = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            lines[i] = new Vector(maxVectorDimension, array[i]);
        }
    }

    public Matrix(Vector[] vectorArray) {
        if (vectorArray.length == 0) {
            lines = new Vector[]{new Vector(1)}; // Я добавил вариант, когда передается пустой массив.
        } else {
            int maxVectorDimension = vectorArray[0].getDimension(); // Я так понимаю пункт 5 замечаний не относится к данному
            // случаю, так как размер матрицы еще не определен?

            for (int i = 1; i < vectorArray.length; i++) {
                if (maxVectorDimension < vectorArray[i].getDimension()) {
                    maxVectorDimension = vectorArray[i].getDimension();
                }
            }

            lines = new Vector[vectorArray.length];

            for (int i = 0; i < vectorArray.length; i++) {
                double[] array = new double[maxVectorDimension];

                for (int j = 0; j < vectorArray[i].getDimension(); j++) { // Я так понял ошибка была в этом месте,
                    // заполнялась только часть матрицы размером с квадрат из-за равенства ограничений в циклах for
                    array[j] = vectorArray[i].getComponent(j);
                }

                lines[i] = new Vector(maxVectorDimension, array);
            }
        }
    }

    public int getMatrixColumnDimension() {
        return lines[0].getDimension();
    }

    public int getMatrixLinesDimension() {
        return lines.length;
    }

    public Vector getLine(int index) {
        if (index < 0 || index >= lines.length) {
            throw new IndexOutOfBoundsException("The line index must belong to the range [0; " + (lines.length - 1)
                    + "]. Line index is " + index + ".");
        }

        Vector vector = new Vector(getMatrixColumnDimension());

        for (int i = 0; i < getMatrixColumnDimension(); i++) {
            vector.setComponent(i, lines[index].getComponent(i));
        }

        return vector;
    }

    public void setLine(int index, Vector vector) {
        if (index < 0 || index >= lines.length) {
            throw new IndexOutOfBoundsException("The line index must belong to the range [0; " + (lines.length - 1)
                    + "]. Line index is " + index + ".");
        }

        if (getMatrixColumnDimension() != vector.getDimension()) {
            throw new IllegalArgumentException("Matrix dimension and vector dimension are different. Both dimension "
                    + "must be same. Matrix dimension is " + getMatrixColumnDimension() + ". Vector dimension is "
                    + vector.getDimension() + ".");
        }

        Vector vectorCopy = new Vector(getMatrixColumnDimension());

        for (int i = 0; i < vector.getDimension(); i++) {
            vectorCopy.setComponent(i, vector.getComponent(i));
        }

        lines[index] = vectorCopy;
    }

    public Vector getColumnArray(int index) {
        if (index < 0 || index >= getMatrixColumnDimension()) {
            throw new IndexOutOfBoundsException("The column index must belong to the range [0; "
                    + (getMatrixColumnDimension() - 1) + "]. Column index is " + index + ".");
        }

        double[] columnVector = new double[lines.length];

        for (int i = 0; i < lines.length; i++) {
            columnVector[i] = lines[i].getComponent(index);
        }

        return new Vector(columnVector);
    }

    public void transpose() {
        Vector[] lines = new Vector[getMatrixColumnDimension()];

        for (int i = 0; i < getMatrixColumnDimension(); i++) {
            lines[i] = getColumnArray(i);
        }

        this.lines = lines;
    }

    public void multiply(double number) {
        for (Vector vector : lines) {
            vector.multiply(number);
        }
    }

    public double getDeterminant() {
        if (getMatrixLinesDimension() != getMatrixColumnDimension()) {
            throw new IllegalArgumentException("Matrix must have same dimension of lines and columns. Lines dimension "
                    + "is " + getMatrixLinesDimension() + ". Columns dimension is " + getMatrixColumnDimension() + ".");
        }

        if (lines.length == 1) {
            return lines[0].getComponent(0);
        }

        if (lines.length == 2) {
            return lines[0].getComponent(0) * lines[1].getComponent(1)
                    - lines[0].getComponent(1) * lines[1].getComponent(0);
        }

        double determinant = 0;
        int sign = 1;

        for (int determinantColumn = 0; determinantColumn < getMatrixColumnDimension(); ++determinantColumn) {
            double[][] minor = new double[lines.length - 1][lines.length - 1];
            int minorRow = 0;

            for (int row = 1; row < lines.length; ++row) {
                int minorColunm = 0;

                for (int column = 0; column < getMatrixColumnDimension(); ++column) {
                    if (column != determinantColumn) {
                        minor[minorRow][minorColunm]
                                = lines[row].getComponent(column);

                        ++minorColunm;
                    }
                }

                ++minorRow;
            }

            determinant += sign * lines[0].getComponent(determinantColumn) * new Matrix(minor).getDeterminant();
            sign *= -1;
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector line : lines) {
            stringBuilder.append(line).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append('}');

        return stringBuilder.toString();
    }

    public Vector multiply(Vector vector) { // Судя по правилу умножение матрицы на вектор-столбец получается матрица
        // одиночных векторов. Если я сейчас верну просто вектор, будет ли это считаться правильным?
        if (getMatrixLinesDimension() != vector.getDimension()) {
            throw new IllegalArgumentException("Matrix line dimension and vector dimension must have same dimension. "
                    + "Matrix line dimension is " + getMatrixLinesDimension() + ". Vector dimension is "
                    + vector.getDimension() + ".");
        }

        Vector unitVector = new Vector(getMatrixLinesDimension());

        for (int i = 0; i < lines.length; i++) {
            double sum = 0;

            for (int j = 0; j < getMatrixColumnDimension(); j++) {
                sum += lines[i].getComponent(j) * vector.getComponent(j);
            }

            unitVector.setComponent(i, sum);
        }

        return unitVector;
    }

    private static boolean getMatrixEquality(Matrix matrix1, Matrix matrix2) {
        return matrix1.lines.length != matrix2.lines.length
                && matrix1.getMatrixColumnDimension() != matrix2.getMatrixColumnDimension();
    }

    public void add(Matrix matrix) {
        if (getMatrixEquality(this, matrix)) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + lines.length + "x" + getMatrixColumnDimension() + ". Dimension of matrix2 is "
                    + matrix.lines.length + "x" + matrix.getMatrixColumnDimension() + ".");
        }

        for (int i = 0; i < lines.length; i++) {
            lines[i].add(matrix.lines[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (getMatrixEquality(this, matrix)) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + lines.length + "x" + getMatrixColumnDimension() + ". Dimension of matrix2 is "
                    + matrix.lines.length + "x" + matrix.getMatrixColumnDimension() + ".");
        }

        for (int i = 0; i < lines.length; i++) {
            lines[i].subtract(matrix.lines[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (getMatrixEquality(matrix1, matrix2)) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + matrix1.lines.length + "x" + matrix1.getMatrixColumnDimension()
                    + ". Dimension of matrix2 is " + matrix2.lines.length + "x"
                    + matrix2.getMatrixColumnDimension() + ".");
        }

        matrix1.add(matrix2);

        return new Matrix(matrix1);
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (getMatrixEquality(matrix1, matrix2)) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + matrix1.lines.length + "x" + matrix1.getMatrixColumnDimension()
                    + ". Dimension of matrix2 is " + matrix2.lines.length + "x"
                    + matrix2.getMatrixColumnDimension() + ".");
        }

        matrix1.subtract(matrix2);

        return new Matrix(matrix1);
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getMatrixColumnDimension() != matrix2.lines.length) {
            throw new IllegalArgumentException("Matrix1 columns count and matrix2 lines count must be same dimension. "
                    + "Columns dimension of matrix1 is " + matrix1.getMatrixColumnDimension()
                    + ". Lines dimension of matrix2 is " + matrix2.lines.length + ".");
        }

        double[][] array = new double[matrix1.lines.length][matrix2.getMatrixColumnDimension()];

        for (int i = 0; i < matrix1.lines.length; i++) {
            Vector vector1 = matrix1.lines[i];

            for (int j = 0; j < matrix2.getMatrixColumnDimension(); j++) {
                Vector vector2 = matrix2.getColumnArray(j);

                for (int k = 0; k < matrix1.getMatrixColumnDimension(); k++) {
                    array[i][j] += vector1.getComponent(k) * vector2.getComponent(k);
                }
            }
        }

        return new Matrix(array);
    }
}