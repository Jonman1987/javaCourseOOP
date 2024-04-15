package ru.academits.danilov_e.matrix;

import ru.academits.danilov_e.vector.Vector;

import java.util.NoSuchElementException;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Rows count must be more than 0. Rows count is " + rowsCount + ".");
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Columns count must be more than 0. Columns count is " + columnsCount + ".");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("The passed matrix refers to null");
        }

        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("The passed array refers to null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Array rows count must be more than 0.");
        }

        int columnsCount = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (columnsCount < array[i].length) {
                columnsCount = array[i].length;
            }
        }

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Matrix columns count is 0"); // Я пытался подобрать более правильное исключение
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("The passed vectors array refers to null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Vectors array length must be greater than 0.");
        }

        int maxVectorDimension = vectors[0].getDimension();

        for (int i = 1; i < vectors.length; i++) {
            if (maxVectorDimension < vectors[i].getDimension()) {
                maxVectorDimension = vectors[i].getDimension();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxVectorDimension);
            rows[i].add(vectors[i]);
        }
    }

    public int getColumnsCount() {
        return rows[0].getDimension();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("The row index must belong to the range [0; " + (rows.length - 1)
                    + "]. Row index is " + index + ".");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (vector == null) {
            throw new NullPointerException("The passed vector refers to null");
        }

        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("The row index must belong to the range [0; " + (rows.length - 1)
                    + "]. Row index is " + index + ".");
        }

        if (getColumnsCount() != vector.getDimension()) {
            throw new IllegalArgumentException("Matrix columns count and vector dimension are different. Matrix columns "
                    + "count and vector dimension must be same. Matrix columns count is " + getColumnsCount()
                    + ". Vector dimension is " + vector.getDimension() + ".");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("The column index must belong to the range [0; "
                    + (getColumnsCount() - 1) + "]. Column index is " + index + ".");
        }

        double[] columnComponentsArray = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            columnComponentsArray[i] = rows[i].getComponent(index);
        }

        return new Vector(columnComponentsArray);
    }

    public void transpose() {
        Vector[] newRows = new Vector[getColumnsCount()];

        for (int i = 0; i < newRows.length; i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiply(double number) {
        for (Vector row : rows) {
            row.multiply(number);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalStateException("Matrix must have same count of rows and columns. Rows count "
                    + "is " + rows.length + ". Columns count is " + getColumnsCount() + ".");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double determinant = 0;
        int sign = 1;

        for (int determinantColumn = 0; determinantColumn < getColumnsCount(); ++determinantColumn) {
            double[][] minor = new double[rows.length - 1][rows.length - 1];
            int minorRow = 0;

            for (int row = 1; row < rows.length; ++row) {
                int minorColumn = 0;

                for (int column = 0; column < getColumnsCount(); ++column) {
                    if (column != determinantColumn) {
                        minor[minorRow][minorColumn] = rows[row].getComponent(column);
                        ++minorColumn;
                    }
                }

                ++minorRow;
            }

            determinant += sign * rows[0].getComponent(determinantColumn) * new Matrix(minor).getDeterminant();
            sign *= -1;
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append('}');

        return stringBuilder.toString();
    }

    public Vector multiply(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("The passed vector refers to null");
        }

        if (getColumnsCount() != vector.getDimension()) {
            throw new IllegalArgumentException("Matrix columns count and vector dimension must be same. "
                    + "Matrix columns count is " + getColumnsCount()
                    + ". Vector dimension is " + vector.getDimension() + ".");
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    private static void checkMatricesSizesEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("The passed matrix1 refers to null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("The passed matrix2 refers to null");
        }

        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + matrix1.rows.length + "x" + matrix1.getColumnsCount() + ". Dimension of matrix2 is "
                    + matrix2.rows.length + "x" + matrix2.getColumnsCount() + ".");
        }
    }

    public void add(Matrix matrix) {
        checkMatricesSizesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesSizesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return new Matrix(result);
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("The passed matrix1 refers to null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("The passed matrix2 refers to null");
        }

        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Matrix1 columns count and matrix2 rows count must be same. "
                    + "Columns count of matrix1 is " + matrix1.getColumnsCount()
                    + ". Rows count of matrix2 is " + matrix2.rows.length + ".");
        }

        double[][] array = new double[matrix1.rows.length][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                array[i][j] = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(array);
    }
}