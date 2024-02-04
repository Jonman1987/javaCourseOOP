package ru.academits.Danilov_E.Main;

import ru.academits.Danilov_E.Vector.Vector;

public class Main {
    public static void main(String[] args) {

        int n = 10;
        int number1 = 3;
        int number2 = -2;

        double[] array1 = new double[]{-3.4, 3.3, 5.7, 6.7};
        double[] array2 = new double[]{2.4, 1.3, -0.7, 3.7, 1.1, 5.6};
        double[] array3 = new double[]{6.7, -3.1, 0.0, 8.8, 2.7, 9.9, 4.2, 5.5};

        System.out.println("1. Конструктор Vector(n), n = 10:");
        Vector vector1 = new Vector(n);
        System.out.println("Результат: " + vector1); // Не совсем понимаю почему мой вектор выводится даже без метода toString().
        // Я так понимаю, что он используется по умолчанию?
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
        Vector vector4 = new Vector(n, array1);
        System.out.println("Результат: " + vector4);
        System.out.println();

        System.out.println("5. Конструктор Vector(n, array) при n < array.length:");
        Vector vector5 = new Vector(2, array1);
        System.out.println("Результат: " + vector5);
        System.out.println();

        System.out.println("6. Метод genSize() для вектора из пункта 4");
        System.out.println("Размерность вектора: " + vector4.getSize());
        System.out.println();

        System.out.println("7. Метод genSize() для вектора из пункта 5");
        System.out.println("Размерность вектора: " + vector5.getSize());
        System.out.println();

        System.out.println("8. Работа метода toString()");
        System.out.println("Результат: " + vector4);
        System.out.println();

        System.out.println("9. Прибавление к вектору другого вектора:");
        Vector vector6 = new Vector(array2);
        System.out.println("Вариант когда первый вектор меньше второго:");
        System.out.println("Вектор к которому прибавляем: " + vector2);
        System.out.println("Вектор который прибавляем: " + vector6);
        vector2.makeAddition(vector6);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println("Вариант когда второй вектор меньше первого:");
        System.out.println("Вектор к которому прибавляем: " + vector2);
        System.out.println("Вектор который прибавляем: " + vector5);
        vector2.makeAddition(vector5);
        System.out.println("Результат: " + vector2);
        System.out.println();

        System.out.println("10. Вычитание из вектора другого вектора:");
        Vector vector7 = new Vector(array2);
        Vector vector8 = new Vector(array1);
        System.out.println("Вариант когда первый вектор меньше второго:");
        System.out.println("Вектор из которого вычитаем: " + vector8);
        System.out.println("Вектор который вычитаем: " + vector7);
        vector8.makeSubtraction(vector7);
        System.out.println("Результат: " + vector8);
        System.out.println();

        System.out.println("Вариант когда второй вектор меньше первого:");
        System.out.println("Вектор из которого вычитаем: " + vector7);
        System.out.println("Вектор который вычитаем: " + vector5);
        vector7.makeSubtraction(vector5);
        System.out.println("Результат: " + vector7);
        System.out.println();

        System.out.println("11. Умножение вектора на скаляр:");
        System.out.println("Вектор, который умножаем: " + vector3);
        System.out.println("Скаляр: " + number1);
        vector3.makeMultiplication(number1);
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println("Вектор, который умножаем: " + vector3);
        System.out.println("Скаляр: " + number2);
        vector3.makeMultiplication(number2);
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println("12. Реверс вектора:");
        System.out.println("Реверсируемый вектор: " + vector3);
        vector3.makeReverse();
        System.out.println("Результат: " + vector3);
        System.out.println();

        System.out.println("13. Получение длины вектора:");
        Vector vector9 = new Vector(array1);
        Vector vector10 = new Vector(10, array2);
        Vector vector11 = new Vector(array3);
        Vector vector12 = new Vector(10, array3);

        System.out.println("Обычный вектор: " + vector9);
        System.out.println("Результат: " + vector9.getVectorLength());
        System.out.println("Вектор дополненный нулями: " + vector10);
        System.out.println("Результат: " + vector10.getVectorLength());
        System.out.println("Вектор с нулем в середине компонент: " + vector11);
        System.out.println("Результат: " + vector11.getVectorLength());
        System.out.println("Вектор дополненный нулями с нулем в середине компонент: " + vector12);
        System.out.println("Результат: " + vector12.getVectorLength());
        System.out.println();

        System.out.println("13. Редактирование компоненты вектора по ее индексу (9, 4.1):");
        System.out.println("Вектор: " + vector10);
        vector10.editVectorComponent(9, 4.1);
        System.out.println("Результат: " + vector10);
        System.out.println();

        System.out.println("14. Метод Equals():");
        Vector vector13 = new Vector(4);
        Vector vector14 = new Vector(array1);
        Vector vector15 = new Vector(array1);
        Vector vector16 = new Vector(10, array1);
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

        System.out.println("15. Метод hashCode():");
        System.out.println("Вектор: " + vector14);
        System.out.println("Результат: " + vector14.hashCode());
        System.out.println("Вектор: " + vector2);
        System.out.println("Результат: " + vector2.hashCode());
        System.out.println();

        System.out.println("16. Статический метод сложения векторов с получением нового вектора:");
        Vector vector17 = new Vector(array1);
        Vector vector18 = new Vector(array2);
        Vector vector19 = new Vector(array3);
        System.out.println("Первый вектор меньшей размерности, чем второй:");
        System.out.println("Вектор: " + vector17);
        System.out.println("Вектор: " + vector18);
        Vector vector20 = Vector.makeAddition(vector17, vector18);
        System.out.println("Результат: " + vector20);
        System.out.println("Второй вектор меньшей размерности, чем первый:");
        System.out.println("Вектор: " + vector19);
        System.out.println("Вектор: " + vector18);
        Vector vector21 = Vector.makeAddition(vector19, vector18);
        System.out.println("Результат: " + vector21);
        System.out.println();

        System.out.println("17. Статический метод вычитания векторов с получением нового вектора:");
        Vector vector22 = new Vector(array1);
        Vector vector23 = new Vector(array2);
        Vector vector24 = new Vector(array3);
        System.out.println("Первый вектор меньшей размерности, чем второй:");
        System.out.println("Вектор: " + vector22);
        System.out.println("Вектор: " + vector23);
        Vector vector25 = Vector.makeSubtraction(vector22, vector23);
        System.out.println("Результат: " + vector25);
        System.out.println("Второй вектор меньшей размерности, чем первый:");
        System.out.println("Вектор: " + vector24);
        System.out.println("Вектор: " + vector23);
        Vector vector26 = Vector.makeSubtraction(vector24, vector23);
        System.out.println("Результат: " + vector26);
        System.out.println();

        System.out.println("17. Скалярное произведение векторов с получением нового вектора:");
        Vector vector27 = new Vector(array1);
        Vector vector28 = new Vector(array2);
        Vector vector29 = new Vector(array3);
        System.out.println("Первый вектор меньшей размерности, чем второй:");
        System.out.println("Вектор: " + vector27);
        System.out.println("Вектор: " + vector28);
        Vector vector30 = Vector.makeMultiplication(vector27, vector28);
        System.out.println("Результат: " + vector30);
        System.out.println("Второй вектор меньшей размерности, чем первый:");
        System.out.println("Вектор: " + vector29);
        System.out.println("Вектор: " + vector28);
        Vector vector31 = Vector.makeMultiplication(vector29, vector28);
        System.out.println("Результат: " + vector31);
        System.out.println();
    }
}