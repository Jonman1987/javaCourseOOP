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
    }
}