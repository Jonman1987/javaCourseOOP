package matrix_main;

import matrix_class.Matrix;
import ru.academits.danilov_e.vector.Vector;

public class MatrixMain {
    public static void main(String[] args) {
        double[] array1 = {-3.4, 3.3, 5.7, 6.7};
        double[] array2 = {2.4, 1.3, -0.7, 3.7, 1.1, 5.6};
        double[] array3 = {6.7, -3.1, 0.0, 8.8, 2.7, 9.9, 4.2, 5.5};

        int n = 4;
        int m = 8;

        double[][] myArray = {{1.1, 2.2, 3.3}, {4.4, 5.5, 6.6}, {7.7, 8.8}};

        System.out.println("1. Конструктор матрицы нулей размером n = " + n + ", m = " + m + ":");
        Matrix matrix01 = new Matrix(n, m);

        matrix01.print();
        System.out.println();

        System.out.println("2. Конструктор матрицы из двумерного массива с добавлением нулей в меньшем векторе:");
        Matrix matrix2 = new Matrix(myArray);

        matrix2.print();
        System.out.println();

        System.out.println("3. Конструктор копирования:");
        Matrix matrix1 = new Matrix(matrix2);

        matrix1.print();
        System.out.println();

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectors = {vector1, vector2, vector3};

        System.out.println("4. Конструктор из массива векторов-строк разной длины с добавлением нулей:");
        Matrix matrix3 = new Matrix(vectors);

        matrix3.print();
        System.out.println();

        System.out.println("5. Получение высоты матрицы из пункта 4:");
        System.out.println("Высота: " + matrix3.getHeight());
        System.out.println();

        System.out.println("6. Получение ширины матрицы из пункта 4:");
        System.out.println("Ширина: " + matrix3.getWidth());
        System.out.println();

        System.out.println("7. Получение вектора строки из пункта 4 по индексу 1:");
        System.out.println(matrix3.getVector(1));
        System.out.println();

        System.out.println("8. Получение вектора-строки матрицы из примера 3 по индексу 1:");
        System.out.println("Матрица:");
        matrix1.print();
        System.out.println("Вектор:");
        System.out.println(matrix1.getVector(1));
        System.out.println();

        System.out.println("9. Задание вектора-строки матрицы из примера 3 по индексу 2:");
        System.out.println("Матрица:");
        matrix1.print();
        System.out.println("Вектор:");
        System.out.println(new Vector(3));
        matrix1.setVector(2, new Vector(3));
        System.out.println("Новая матрица:");
        matrix1.print();
        System.out.println();

        System.out.println("10. Получение вектора-столбца матрицы из примера 9 по индексу 1:");
        System.out.println(matrix1.getColumnVector(1));
        System.out.println();

        System.out.println("11. Транспонирование матрицы из примера 4:");
        System.out.println("Матрица:");
        matrix3.print();
        matrix3.transposition();
        System.out.println("Результат:");
        matrix3.print();
        System.out.println();

        System.out.println("12. Умножение матрицы из примера 2 на скаляр 2:");
        System.out.println("Матрица:");
        matrix2.print();
        matrix2.multiply(2);
        System.out.println("Результат:");
        matrix2.print();
        System.out.println();

        System.out.println("13. Вычисление определителя матрицы:");
        Matrix matrix4 = new Matrix(new double[][]{{1, -2, 3}, {0, 7, 4}, {5, 3, -3}});
        System.out.println("Матрица:");
        matrix4.print();
        System.out.println("Определитель: " + matrix4.matrixDeterminant());
        System.out.println();

        System.out.println("14. Переопределение toString:");
        System.out.println(matrix2);
        System.out.println();

        System.out.println("15. Умножение матрицы из пункта 12 на вектор из пункта 10:");
        Vector vector4 = matrix1.getColumnVector(1);
        matrix2.multiply(vector4);
        System.out.println(matrix2);
        System.out.println();

        System.out.println("16. Сложение матрицы из пункта 9 с самой собой:");
        Matrix matrix5 = new Matrix(myArray);
        Matrix matrix6 = new Matrix(myArray);
        System.out.println("Исходная матрица:");
        matrix5.print();
        matrix5.add(matrix6);
        System.out.println("Результат:");
        matrix5.print();
        System.out.println();

        // Закомментировал, чтобы не вылетала ошибка.
        System.out.println("17. Сложение матриц разной размерности:");
        /*Matrix2 matrix7 = new Matrix2(2, 4);
        matrix5.add(matrix7);*/
        System.out.println("Необходимо раскомментировать код.");
        System.out.println();

        System.out.println("18. Вычитание матрицы из пункта 9 с самой собой:");
        Matrix matrix8 = new Matrix(myArray);
        Matrix matrix9 = new Matrix(myArray);
        System.out.println("Исходная матрица:");
        matrix8.print();
        matrix8.subtract(matrix9);
        System.out.println("Результат:");
        matrix8.print();
        System.out.println();

        // Закомментировал, чтобы не вылетала ошибка.
        System.out.println("19. Вычитание матриц разной размерности:");
        /*Matrix2 matrix10 = new Matrix2(2, 4);
        matrix5.subtract(matrix10);*/
        System.out.println("Необходимо раскомментировать код.");
        System.out.println();

        System.out.println("20. Сложение матриц статическим методом:");
        Matrix matrix11 = new Matrix(myArray);
        Matrix matrix12 = new Matrix(myArray);
        Matrix.add(matrix11, matrix12).print();
        System.out.println();

        System.out.println("21. Вычитание матриц статическим методом:");
        Matrix matrix13 = new Matrix(myArray);
        Matrix matrix14 = new Matrix(myArray);
        Matrix.subtract(matrix13, matrix14).print();
        System.out.println();

        System.out.println("21. Умножение матриц статическим методом:");
        double[][] array4 = {{3, -1, 2}, {4, 2, 0}, {-5, 6, 1}};
        double[][] array5 = {{8, 1}, {7, 2}, {2, -3}};

        Matrix matrix15 = new Matrix(array4);
        System.out.println("Исходная матрица 1:");
        matrix15.print();
        Matrix matrix16 = new Matrix(array5);
        System.out.println("Исходная матрица 2:");
        matrix16.print();
        System.out.println("Результат:");
        Matrix.multiply(matrix15, matrix16).print();
        System.out.println();
    }
}