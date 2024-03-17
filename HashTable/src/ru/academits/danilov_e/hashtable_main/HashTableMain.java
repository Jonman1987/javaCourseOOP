package ru.academits.danilov_e.hashtable_main;

import ru.academits.danilov_e.hashtable.HashTable;

import java.util.Arrays;

public class HashTableMain {
    public static void main(String[] args) {
        System.out.println("1. Создание Хеш-Таблицы с элементом \"Красный\":");
        String color1 = "Красный";
        HashTable<String> colorsTable1 = new HashTable<>(color1);
        System.out.println("Количество элементов в таблице: " + colorsTable1.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable1.toArray()));
        System.out.println("Размер таблицы: " + colorsTable1.tableLength());
        System.out.println("Хэш элемента \"Красный\" с учетом размера таблицы: " + color1.hashCode() % colorsTable1.tableLength());
        System.out.println("Позиция элемента \"Красный\" в таблице: " + colorsTable1.tableIndex(color1));
        System.out.println();

        int capacity1 = 50;
        System.out.println("2. Создание Хеш-Таблицы с элементом \"Белый\" с размерностью " + capacity1 + ":");
        String color2 = "Белый";
        HashTable<String> colorsTable2 = new HashTable<>(color2, capacity1);
        System.out.println("Количество элементов в таблице: " + colorsTable2.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable2.toArray()));
        System.out.println("Размер таблицы: " + colorsTable2.tableLength());
        System.out.println("Хэш элемента \"Белый\" с учетом размера таблицы: " + color2.hashCode() % colorsTable2.tableLength());
        System.out.println("Позиция элемента \"Белый\" в таблице: " + colorsTable2.tableIndex(color2));
        System.out.println();

        System.out.println("3. Добавление элементов в таблицу:");

        String color3 = "Черный";
        String color4 = "Синий";
        String color5 = "Зеленый";
        String color6 = "Черный";

        colorsTable1.add(color3);
        colorsTable1.add(color4);
        colorsTable1.add(color5);
        colorsTable1.add(color6);

        System.out.println("Количество элементов в таблице: " + colorsTable1.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable1.toArray()));
        System.out.println("Размер таблицы: " + colorsTable1.tableLength());
        System.out.println();

        System.out.println("4. Добавление элементов в таблицу при ее заполненности:");
        System.out.println("В данном пункте я пытался всячески отловить перемещения элементов по таблице в зависимости " +
                "от изменения размерности и отследить изменения хэша элемента в зависимости от размера таблицы.");
        String color7 = "Розовый";
        int capacity2 = 1;
        HashTable<String> colorsTable3 = new HashTable<>(color7, capacity2);
        System.out.println("Хэш элемента " + color7 + " с учетом размера таблицы: " + color7.hashCode() % colorsTable3.tableLength());
        System.out.println("Позиция элемента " + color7 + " в таблице: " + colorsTable3.tableIndex(color7));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.tableLength());
        System.out.println();

        System.out.println("Добавление элемента \"Золотой\":");
        String color8 = "Золотой";
        colorsTable3.add(color8);
        System.out.println("Позиция элемента " + color7 + " в таблице: " + colorsTable3.tableIndex(color7));
        System.out.println("Хэш элемента " + color8 + " с учетом размера таблицы: " + color8.hashCode() % colorsTable3.tableLength());
        System.out.println("Позиция элемента " + color8 + " в таблице: " + colorsTable3.tableIndex(color8));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.tableLength());
        System.out.println();

        System.out.println("Добавление элемента " + color1 + ":");
        colorsTable3.add(color1);
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.tableLength());
        System.out.println();

        System.out.println("Добавление элемента " + color2 + ":");
        colorsTable3.add(color2);
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.tableLength());
        System.out.println();

        System.out.println("Добавление элемента " + color3 + ":");
        colorsTable3.add(color3);
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.tableLength());
        System.out.println();

        System.out.println("Добавление элемента " + color4 + ":");
        colorsTable3.add(color4);
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.tableLength());
        System.out.println();
    }
}