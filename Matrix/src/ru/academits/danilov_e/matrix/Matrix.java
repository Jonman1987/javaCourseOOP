package ru.academits.danilov_e.matrix;

import ru.academits.danilov_e.vector.Vector;

public class Matrix {  // Не исправлено 12 и 19. остановился на 20
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
            throw new NullPointerException("The passed object refers to null");
        }

        rows = matrix.rows;
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Array rows count must be more than 0. Rows count is " + array.length + ".");
        }

        if (array[0].length == 0) {
            throw new IllegalArgumentException("Array columns count must be more than 0. Columns count is " + array[0].length + ".");
        }

        int columnsCount = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (columnsCount < array[i].length) {
                columnsCount = array[i].length;
            }
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) { // Пункт 12 не исправлен!!!!!!!!!!
        if (vectors == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Vector array length must be more than 0. Vector array length count is "
                    + vectors.length + ".");
        }

        int maxVectorDimension = vectors[0].getDimension();

        for (int i = 1; i < vectors.length; i++) {
            if (maxVectorDimension < vectors[i].getDimension()) {
                maxVectorDimension = vectors[i].getDimension();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            double[] array = new double[maxVectorDimension];

            for (int j = 0; j < vectors[i].getDimension(); j++) {
                array[j] = vectors[i].getComponent(j);
            }

            rows[i] = new Vector(maxVectorDimension, array);
        }
    }

    public int getColumnCount() {
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

    public void setRow(int index, Vector vector) { // Возможно добавить проверку длины вектора равной 0
        if (vector == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("The row index must belong to the range [0; " + (rows.length - 1)
                    + "]. Row index is " + index + ".");
        }

        if (getColumnCount() != vector.getDimension()) {
            throw new IllegalArgumentException("Matrix dimension and vector dimension are different. Both dimension "
                    + "must be same. Matrix dimension is " + getColumnCount() + ". Vector dimension is "
                    + vector.getDimension() + ".");
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnCount()) {
            throw new IndexOutOfBoundsException("The column index must belong to the range [0; "
                    + (getColumnCount() - 1) + "]. Column index is " + index + ".");
        }

        double[] columnArray = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            columnArray[i] = rows[i].getComponent(index);
        }

        return new Vector(columnArray);
    }

    public void transpose() {
        Vector[] newRows = new Vector[getColumnCount()];

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
        if (rows.length != getColumnCount()) {
            throw new IllegalStateException("Matrix must have same dimension of rows and columns. Rows dimension "
                    + "is " + rows.length + ". Columns dimension is " + getColumnCount() + ".");
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

        for (int determinantColumn = 0; determinantColumn < getColumnCount(); ++determinantColumn) {
            double[][] minor = new double[rows.length - 1][rows.length - 1];
            int minorRow = 0;

            for (int row = 1; row < rows.length; ++row) {
                int minorColumn = 0;

                for (int column = 0; column < getColumnCount(); ++column) {
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

    public Vector multiply(Vector vector) { // Возможно добавить проверку длины вектора равной 0
        if (vector == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (rows.length != vector.getDimension()) { // Не исправил проверку размеров. Пункт 19
            throw new IllegalArgumentException("Matrix rows dimension and vector dimension must have same dimension. "
                    + "Matrix rows dimension is " + rows.length
                    + ". Vector dimension is " + vector.getDimension() + ".");
        }

        Vector unitVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            double sum = 0;

            for (int j = 0; j < getColumnCount(); j++) {
                sum += rows[i].getComponent(j) * vector.getComponent(j);
            }

            unitVector.setComponent(i, sum);
        }

        return unitVector;
    }

    private static boolean hasMatricesSizeEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        return matrix1.rows.length != matrix2.rows.length
                && matrix1.getColumnCount() != matrix2.getColumnCount();
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (hasMatricesSizeEquality(this, matrix)) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + rows.length + "x" + getColumnCount() + ". Dimension of matrix2 is "
                    + matrix.rows.length + "x" + matrix.getColumnCount() + ".");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (hasMatricesSizeEquality(this, matrix)) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + rows.length + "x" + getColumnCount() + ". Dimension of matrix2 is "
                    + matrix.rows.length + "x" + matrix.getColumnCount() + ".");
        }

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (hasMatricesSizeEquality(matrix1, matrix2)) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + matrix1.rows.length + "x" + matrix1.getColumnCount()
                    + ". Dimension of matrix2 is " + matrix2.rows.length + "x"
                    + matrix2.getColumnCount() + ".");
        }

        matrix1.add(matrix2);

        return new Matrix(matrix1);
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (hasMatricesSizeEquality(matrix1, matrix2)) {
            throw new IllegalArgumentException("Both matrix must be same dimension. Dimension of matrix1 is "
                    + matrix1.rows.length + "x" + matrix1.getColumnCount()
                    + ". Dimension of matrix2 is " + matrix2.rows.length + "x"
                    + matrix2.getColumnCount() + ".");
        }

        matrix1.subtract(matrix2);

        return new Matrix(matrix1);
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null || matrix2 == null) {
            throw new NullPointerException("The passed object refers to null");
        }

        if (matrix1.getColumnCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Matrix1 columns count and matrix2 rows count must be same dimension. "
                    + "Columns dimension of matrix1 is " + matrix1.getColumnCount()
                    + ". Rows dimension of matrix2 is " + matrix2.rows.length + ".");
        }

        double[][] array = new double[matrix1.rows.length][matrix2.getColumnCount()];

        for (int i = 0; i < matrix1.rows.length; i++) {
            Vector vector1 = matrix1.rows[i];

            for (int j = 0; j < matrix2.getColumnCount(); j++) {
                Vector vector2 = matrix2.getColumn(j);

                for (int k = 0; k < matrix1.getColumnCount(); k++) {
                    array[i][j] += vector1.getComponent(k) * vector2.getComponent(k);
                }
            }
        }

        return new Matrix(array);
    }
}