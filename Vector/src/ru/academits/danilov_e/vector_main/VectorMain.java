package ru.academits.danilov_e.vector_main;

import ru.academits.danilov_e.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        // Вопрос: я создал переменные, которые используются в нескольких местах кода. Как лучше организовать оформление переменных?
        // Создать блок с перечислением переменных в начале, где я перечисляю все переменные. На мой взгляд это даст
        // удобство доступа и мне не нужно будет искать отдельно переменные в нижней единообразной части кода.
        // Или же я должен создавать переменные в местах близких к моменту их использования?
        int vectorDimension1 = 10;
        int vectorDimension2 = 2;

        int number1 = 3;
        int number2 = -2;

        double[] array1 = {-3.4, 3.3, 5.7, 6.7};
        double[] array2 = {2.4, 1.3, -0.7, 3.7, 1.1, 5.6};
        double[] array3 = {6.7, -3.1, 0.0, 8.8, 2.7, 9.9, 4.2, 5.5};

        System.out.println("1. Конструктор Vector(n), n = 10:");
        Vector vector1 = new Vector(vectorDimension1);
        System.out.println("Результат: " + vector1);
        System.out.println();

        System.out.println("2. Конструктор Vector(array), array = {-3.4, 3.3, 5.7, 6.7}:");
        Vector vector2 = new Vector(array1);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println("3. Конструктор Vector(vector), Vector из пункта 2:");
        Vector vector3 = new Vector(vector2);
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println("4. Конструктор Vector(n, array) при n > array.length:");
        Vector vector4 = new Vector(vectorDimension1, array1);
        System.out.println("Результат: " + vector4);
        System.out.println();

        System.out.println("5. Конструктор Vector(n, array) при n < array.length:");
        Vector vector5 = new Vector(vectorDimension2, array1);
        System.out.println("Результат: " + vector5);
        System.out.println();

        System.out.println("6. Метод genSize() для вектора из пункта 4");
        System.out.println("Размерность вектора: " + vector4.getDimension());
        System.out.println();

        System.out.println("7. Метод genSize() для вектора из пункта 5");
        System.out.println("Размерность вектора: " + vector5.getDimension());
        System.out.println();

        System.out.println("8. Работа метода toString()");
        System.out.println("Результат: " + vector4);
        System.out.println();

        System.out.println("9. Прибавление к вектору другого вектора:");
        Vector vector6 = new Vector(array2);
        System.out.println("Вариант когда первый вектор меньше второго:");
        System.out.println("Вектор к которому прибавляем: " + vector2);
        System.out.println("Вектор который прибавляем: " + vector6);
        vector2.add(vector6);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println("Вариант когда второй вектор меньше первого:");
        System.out.println("Вектор к которому прибавляем: " + vector2);
        System.out.println("Вектор который прибавляем: " + vector5);
        vector2.add(vector5);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println("10. Вычитание из вектора другого вектора:");
        Vector vector7 = new Vector(array2);
        Vector vector8 = new Vector(array1);
        System.out.println("Вариант когда первый вектор меньше второго:");
        System.out.println("Вектор из которого вычитаем: " + vector8);
        System.out.println("Вектор который вычитаем: " + vector7);
        vector8.subtract(vector7);
        System.out.println("Результат: " + vector8);
        System.out.println();

        System.out.println("Вариант когда второй вектор меньше первого:");
        System.out.println("Вектор из которого вычитаем: " + vector7);
        System.out.println("Вектор который вычитаем: " + vector5);
        vector7.subtract(vector5);
        System.out.println("Результат: " + vector7);
        System.out.println();

        System.out.println("11. Умножение вектора на скаляр:");
        System.out.println("Вектор, который умножаем: " + vector3);
        System.out.println("Скаляр: " + number1);
        vector3.multiply(number1);
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println("Вектор, который умножаем: " + vector3);
        System.out.println("Скаляр: " + number2);
        vector3.multiply(number2);
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println("12. Реверс вектора:");
        System.out.println("Реверсируемый вектор: " + vector3);
        vector3.reverse();
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println("13. Получение длины вектора:");
        Vector vector9 = new Vector(array1);
        Vector vector10 = new Vector(vectorDimension1, array2);
        Vector vector11 = new Vector(array3);
        Vector vector12 = new Vector(vectorDimension1, array3);

        System.out.println("Обычный вектор: " + vector9);
        System.out.println("Результат: " + vector9.getLength());
        System.out.println("Вектор дополненный нулями: " + vector10);
        System.out.println("Результат: " + vector10.getLength());
        System.out.println("Вектор с нулем в середине компонент: " + vector11);
        System.out.println("Результат: " + vector11.getLength());
        System.out.println("Вектор дополненный нулями с нулем в середине компонент: " + vector12);
        System.out.println("Результат: " + vector12.getLength());
        System.out.println();

        System.out.println("14. Редактирование компоненты вектора по ее индексу (9, 4.1):");
        int index1 = 9;
        int index2 = 2;
        double value1 = 4.1;
        System.out.println("Вектор: " + vector10);
        vector10.setComponent(index1, value1);
        System.out.println("Результат: " + vector10);
        System.out.println("Получение значения компоненты по индексу 2: " + vector10.getComponent(index2));
        System.out.println();

        System.out.println("15. Метод Equals():");
        int vectorDimension3 = 4;
        Vector vector13 = new Vector(vectorDimension3);
        Vector vector14 = new Vector(array1);
        Vector vector15 = new Vector(array1);
        Vector vector16 = new Vector(vectorDimension1, array1);
        System.out.println("Сравнение с нулевым вектором:");
        System.out.println("Вектор: " + vector13 + " и");
        System.out.println("Вектор: " + vector10);
        System.out.println("Результат: " + vector13.equals(vector10));
        System.out.println("Сравнение одного и того же вектора:");
        System.out.println("Вектор: " + vector14 + " и");
        System.out.println("Вектор: " + vector14);
        System.out.println("Результат: " + vector14.equals(vector14));
        System.out.println("Сравнение одинаковых векторов:");
        System.out.println("Вектор: " + vector14 + " и");
        System.out.println("Вектор: " + vector15);
        System.out.println("Результат: " + vector14.equals(vector15));
        System.out.println("Сравнение исходного вектора и созданного из того же массива, но дополненного нулями:");
        System.out.println("Вектор: " + vector15 + " и");
        System.out.println("Вектор: " + vector16);
        System.out.println("Результат: " + vector15.equals(vector16));
        System.out.println();

        System.out.println("16. Метод hashCode():");
        System.out.println("Вектор: " + vector14);
        System.out.println("Результат: " + vector14.hashCode());
        System.out.println("Вектор: " + vector2);
        System.out.println("Результат: " + vector2.hashCode());
        System.out.println();

        System.out.println("17. Статический метод сложения векторов с получением нового вектора:");
        Vector vector17 = new Vector(array1);
        Vector vector18 = new Vector(array2);
        Vector vector19 = new Vector(array3);
        System.out.println("Первый вектор меньшей размерности, чем второй:");
        System.out.println("Вектор: " + vector17);
        System.out.println("Вектор: " + vector18);
        Vector vector20 = Vector.getSum(vector17, vector18);
        System.out.println("Результат: " + vector20);
        System.out.println("Второй вектор меньшей размерности, чем первый:");
        System.out.println("Вектор: " + vector19);
        System.out.println("Вектор: " + vector18);
        Vector vector21 = Vector.getSum(vector19, vector18);
        System.out.println("Результат: " + vector21);
        System.out.println();

        System.out.println("18. Статический метод вычитания векторов с получением нового вектора:");
        Vector vector22 = new Vector(array1);
        Vector vector23 = new Vector(array2);
        Vector vector24 = new Vector(array3);
        System.out.println("Первый вектор меньшей размерности, чем второй:");
        System.out.println("Вектор: " + vector22);
        System.out.println("Вектор: " + vector23);
        Vector vector25 = Vector.getDifference(vector22, vector23);
        System.out.println("Результат: " + vector25);
        System.out.println("Второй вектор меньшей размерности, чем первый:");
        System.out.println("Вектор: " + vector24);
        System.out.println("Вектор: " + vector23);
        Vector vector26 = Vector.getDifference(vector24, vector23);
        System.out.println("Результат: " + vector26);
        System.out.println();

        System.out.println("19. Скалярное произведение векторов с получением нового вектора:");
        Vector vector27 = new Vector(array1);
        Vector vector28 = new Vector(array2);
        Vector vector29 = new Vector(array3);
        System.out.println("Первый вектор меньшей размерности, чем второй:");
        System.out.println("Вектор: " + vector27);
        System.out.println("Вектор: " + vector28);
        double scalarProduct1 = Vector.getScalarProduct(vector27, vector28);
        System.out.println("Результат: " + scalarProduct1);
        System.out.println("Второй вектор меньшей размерности, чем первый:");
        System.out.println("Вектор: " + vector29);
        System.out.println("Вектор: " + vector28);
        double scalarProduct2 = Vector.getScalarProduct(vector29, vector28);
        System.out.println("Результат: " + scalarProduct2);
        System.out.println();
    }
}