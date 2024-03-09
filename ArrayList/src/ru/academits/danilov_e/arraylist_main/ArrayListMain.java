package ru.academits.danilov_e.arraylist_main;

import ru.academits.danilov_e.arraylist.ArrayList;

public class ArrayListMain<E> {
    public static void main(String[] args) {
        System.out.println("1. Создание пустого ArrayList:");
        ArrayList<String> colorList1 = new ArrayList<>();
        System.out.println("Результат:");
        System.out.println(colorList1);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList1.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList1.getArrayLength());
        System.out.println();

        System.out.println("2. Создание пустого ArrayList с заданным значением capacity = 20:");
        ArrayList<String> colorList2 = new ArrayList<>(20);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("3. Добавление элемента \"Красный\" в ArrayList:");
        colorList1.add("Красный");
        System.out.println("Результат:");
        System.out.println(colorList1);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList1.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList1.getArrayLength());
        System.out.println();

        System.out.println("4. Увеличение размера массива под элементы при его заполненности:");
        colorList2.add("Красный");
        colorList2.add("Зеленый");
        colorList2.add("Желтый");
        colorList2.add("Оранжевый");
        colorList2.add("Голубой");
        colorList2.add("Синий");
        colorList2.add("Фиолетовый");
        colorList2.add("Коричневый");
        colorList2.add("Серый");
        colorList2.add("Белый");
        colorList2.add("Черный");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("5. Конструктор копирования ArrayList:");
        ArrayList<String> colorList3 = new ArrayList<>(colorList2);
        System.out.println("Результат:");
        System.out.println(colorList3);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList3.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList3.getArrayLength());
        System.out.println();

        System.out.println("6. Проверка метода isEmpty():");
        ArrayList<String> colorList4 = new ArrayList<>();
        System.out.println("Результат для пустого ArrayList = " + colorList4.isEmpty());
        System.out.println("Результат для не пустого ArrayList = " + colorList3.isEmpty());

    }
}