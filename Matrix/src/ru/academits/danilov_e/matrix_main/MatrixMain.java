package ru.academits.danilov_e.matrix_main;

import ru.academits.danilov_e.matrix.Matrix;
import ru.academits.danilov_e.vector.Vector;

public class MatrixMain {
    public static void print(Matrix matrix) {
        for (int i = 0; i < matrix.getMatrixLinesDimension(); i++) {
            System.out.println(matrix.getLine(i));
        }
    }

    public static void main(String[] args) {
        double[] array1 = {-3.4, 3.3, 5.7, 6.7};
        double[] array2 = {2.4, 1.3, -0.7, 3.7, 1.1, 5.6};
        double[] array3 = {6.7, -3.1, 0.0, 8.8, 2.7, 9.9, 4.2, 5.5};

        int lines = 4;
        int columns = 8;

        double[][] array4 = {{1.1, 2.2, 3.3}, {4.4, 5.5, 6.6}, {7.7, 8.8}};

        System.out.println("1. Конструктор матрицы нулей размером lines = " + lines + ", columns = " + columns + ":");
        Matrix matrix01 = new Matrix(lines, columns);

        print(matrix01);
        System.out.println();

        System.out.println("2. Конструктор матрицы из двумерного массива с добавлением нулей в меньшем векторе:");
        Matrix matrix2 = new Matrix(array4);

        print(matrix2);
        System.out.println();

        System.out.println("3. Конструктор копирования:");
        Matrix matrix1 = new Matrix(matrix2);

        print(matrix1);
        System.out.println();

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectors = {vector1, vector2, vector3};

        System.out.println("4. Конструктор из массива векторов-строк разной длины с добавлением нулей:");
        Matrix matrix3 = new Matrix(vectors);

        print(matrix3);
        System.out.println();

        System.out.println("5. Получение высоты матрицы из пункта 4:");
        System.out.println("Высота: " + matrix3.getMatrixLinesDimension());
        System.out.println();

        System.out.println("6. Получение ширины матрицы из пункта 4:");
        System.out.println("Ширина: " + matrix3.getMatrixColumnDimension());
        System.out.println();

        System.out.println("7. Получение вектора строки из пункта 4 по индексу 1:");
        System.out.println(matrix3.getLine(1));
        System.out.println();

        System.out.println("8. Получение вектора-строки матрицы из примера 3 по индексу 1:");
        System.out.println("Матрица:");
        print(matrix1);
        System.out.println("Вектор:");
        System.out.println(matrix1.getLine(1));
        System.out.println();

        System.out.println("9. Задание вектора-строки матрицы из примера 3 по индексу 2:");
        System.out.println("Матрица:");
        print(matrix1);
        System.out.println("Вектор:");
        System.out.println(new Vector(3));
        matrix1.setLine(2, new Vector(3));
        System.out.println("Новая матрица:");
        print(matrix1);
        System.out.println();

        System.out.println("10. Получение вектора-столбца матрицы из примера 9 по индексу 1:");
        System.out.println(matrix1.getColumnArray(1));
        System.out.println();

        System.out.println("11. Транспонирование матрицы из примера 4:");
        System.out.println("Матрица:");
        print(matrix3);
        matrix3.transpose();
        System.out.println("Результат:");
        print(matrix3);
        System.out.println();

        System.out.println("12. Умножение матрицы из примера 2 на скаляр 2:");
        System.out.println("Матрица:");
        print(matrix2);
        matrix2.multiply(2);
        System.out.println("Результат:");
        print(matrix2);
        System.out.println();

        System.out.println("13. Вычисление определителя матрицы:");
        Matrix matrix4 = new Matrix(new double[][]{{1, -2, 3}, {0, 7, 4}, {5, 3, -3}});
        System.out.println("Матрица:");
        print(matrix4);
        System.out.println("Определитель: " + matrix4.getDeterminant());
        System.out.println();

        System.out.println("14. Переопределение toString:");
        System.out.println(matrix2);
        System.out.println();

        System.out.println("15. Умножение матрицы из пункта 12 на вектор из пункта 10:");
        Vector vector4 = matrix1.getColumnArray(1);
        System.out.println(matrix2.multiply(vector4));
        System.out.println();

        System.out.println("16. Сложение матрицы из пункта 9 с самой собой:");
        Matrix matrix5 = new Matrix(array4);
        Matrix matrix6 = new Matrix(array4);
        System.out.println("Исходная матрица:");
        print(matrix5);
        matrix5.add(matrix6);
        System.out.println("Результат:");
        print(matrix5);
        System.out.println();

        // Закомментировал, чтобы не вылетала ошибка.
        System.out.println("17. Сложение матриц разной размерности:");
        /*Matrix matrix7 = new Matrix(2, 4);
        matrix5.add(matrix7);*/
        System.out.println("Необходимо раскомментировать код.");
        System.out.println();

        System.out.println("18. Вычитание матрицы из пункта 9 с самой собой:");
        Matrix matrix8 = new Matrix(array4);
        Matrix matrix9 = new Matrix(array4);
        System.out.println("Исходная матрица:");
        print(matrix8);
        matrix8.subtract(matrix9);
        System.out.println("Результат:");
        print(matrix8);
        System.out.println();

        // Закомментировал, чтобы не вылетала ошибка.
        System.out.println("19. Вычитание матриц разной размерности:");
        /*Matrix matrix10 = new Matrix(2, 4);
        matrix5.subtract(matrix10);*/
        System.out.println("Необходимо раскомментировать код.");
        System.out.println();

        System.out.println("20. Сложение матриц статическим методом:");
        Matrix matrix11 = new Matrix(array4);
        Matrix matrix12 = new Matrix(array4);
        print(Matrix.getSum(matrix11, matrix12));
        System.out.println();

        System.out.println("21. Вычитание матриц статическим методом:");
        Matrix matrix13 = new Matrix(array4);
        Matrix matrix14 = new Matrix(array4);
        print(Matrix.getDifference(matrix13, matrix14));
        System.out.println();

        System.out.println("21. Умножение матриц статическим методом:");
        double[][] array5 = {{3, -1, 2}, {4, 2, 0}, {-5, 6, 1}};
        double[][] array6 = {{8, 1}, {7, 2}, {2, -3}};

        Matrix matrix15 = new Matrix(array5);
        System.out.println("Исходная матрица 1:");
        print(matrix15);
        Matrix matrix16 = new Matrix(array6);
        System.out.println("Исходная матрица 2:");
        print(matrix16);
        System.out.println("Результат:");
        print(Matrix.getProduct(matrix15, matrix16));
        System.out.println();

        System.out.println("22. Умножение матриц статическим методом:");
        double[][] array7 = {{1, 0}, {2, 1}, {-1, 1}};
        double[][] array8 = {{1, 2, 0}, {0, -1, 1}};

        Matrix matrix17 = new Matrix(array7);
        System.out.println("Исходная матрица 1:");
        print(matrix17);
        Matrix matrix18 = new Matrix(array8);
        System.out.println("Исходная матрица 2:");
        print(matrix18);
        System.out.println("Результат:");
        print(Matrix.getProduct(matrix17, matrix18));
        System.out.println();
    }
}