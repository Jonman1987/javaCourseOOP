package ru.academits.danilov_e.hashtable_main;

import ru.academits.danilov_e.hashtable.HashTable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class HashTableMain {
    public static void main(String[] args) {
        System.out.println("1. Создание Хеш-Таблицы с элементом \"Красный\":");
        String color1 = "Красный";
        HashTable<String> colorsTable1 = new HashTable<>();
        colorsTable1.add(color1);
        System.out.println("Количество элементов в таблице: " + colorsTable1.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable1.toArray()));
        System.out.println("Размер таблицы: " + colorsTable1.getTableCapacity());
        System.out.println("Хэш элемента \"Красный\" с учетом размера таблицы: " + color1.hashCode() % colorsTable1.getTableCapacity());
        System.out.println("Позиция элемента \"Красный\" в таблице: " + colorsTable1.getElementIndex(color1));
        System.out.println();

        int capacity1 = 50;
        System.out.println("2. Создание Хеш-Таблицы с элементом \"Белый\" с размерностью " + capacity1 + ":");
        String color2 = "Белый";
        HashTable<String> colorsTable2 = new HashTable<>(capacity1);
        colorsTable2.add(color2);
        System.out.println("Количество элементов в таблице: " + colorsTable2.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable2.toArray()));
        System.out.println("Размер таблицы: " + colorsTable2.getTableCapacity());
        System.out.println("Хэш элемента \"Белый\" с учетом размера таблицы: " + color2.hashCode() % colorsTable2.getTableCapacity());
        System.out.println("Позиция элемента \"Белый\" в таблице: " + colorsTable2.getElementIndex(color2));
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
        System.out.println("Размер таблицы: " + colorsTable1.getTableCapacity());
        System.out.println();

        System.out.println("4. Добавление элементов в таблицу при ее заполненности:");
        System.out.println("В данном пункте я пытался всячески отловить перемещения элементов по таблице в зависимости " +
                "от изменения размерности и отследить изменения хэша элемента в зависимости от размера таблицы.");
        String color7 = "Розовый";
        int capacity2 = 1;
        HashTable<String> colorsTable3 = new HashTable<>(capacity2);
        colorsTable3.add(color7);
        System.out.println("Хэш элемента " + color7 + " с учетом размера таблицы: " + color7.hashCode() % colorsTable3.getTableCapacity());
        System.out.println("Позиция элемента " + color7 + " в таблице: " + colorsTable3.getElementIndex(color7));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("Добавление элемента \"Золотой\":");
        String color8 = "Золотой";
        colorsTable3.add(color8);
        System.out.println("Позиция элемента " + color7 + " в таблице: " + colorsTable3.getElementIndex(color7));
        System.out.println("Хэш элемента " + color8 + " с учетом размера таблицы: " + color8.hashCode() % colorsTable3.getTableCapacity());
        System.out.println("Позиция элемента " + color8 + " в таблице: " + colorsTable3.getElementIndex(color8));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("Добавление элемента " + color1 + ":");
        colorsTable3.add(color1);
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("Добавление элемента " + color2 + ":");
        colorsTable3.add(color2);
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("Добавление элемента " + color3 + ":");
        colorsTable3.add(color3);
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("Добавление элемента " + color4 + ":");
        colorsTable3.add(color4);
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("5. Проверка содержит ли таблица элемент:");
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Элемент: " + color2);
        System.out.println("Результат: " + colorsTable3.contains(color2));
        System.out.println("Элемент: " + color1);
        System.out.println("Результат: " + colorsTable3.contains(color1));
        String color9 = "Серый";
        System.out.println("Элемент: " + color9);
        System.out.println("Результат: " + colorsTable3.contains(color9));
        System.out.println();

        System.out.println("6. Удаление элемента из таблицы:");
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Элемент: " + color1);
        System.out.println("Результат: " + colorsTable3.remove(color1));
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("Элемент: " + color8);
        System.out.println("Результат: " + colorsTable3.remove(color8));
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("Элемент: " + color5);
        System.out.println("Результат: " + colorsTable3.remove(color5));
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("7. Проверка итератора:");
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        Iterator<String> hashTableIterator = colorsTable3.iterator();

        System.out.println("Первый элемент таблицы: " + hashTableIterator.next());
        System.out.println("Второй элемент таблицы и первый элемент списка: " + hashTableIterator.next());
        System.out.println("Второй элемент таблицы и второй элемент списка: " + hashTableIterator.next());
        System.out.println("Третий элемент таблицы: " + hashTableIterator.next());
        System.out.println();

        System.out.println("8. Вхождение коллекции:");
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        LinkedList<String> stringsLinkedList1 = new LinkedList<>();
        stringsLinkedList1.add("Черный");
        stringsLinkedList1.add("Белый");
        System.out.println("Коллекция:");
        System.out.println(stringsLinkedList1);
        System.out.println("Результат: " + colorsTable3.containsAll(stringsLinkedList1));
        stringsLinkedList1.add("Сизый");
        System.out.println("Коллекция:");
        System.out.println(stringsLinkedList1);
        System.out.println("Результат: " + colorsTable3.containsAll(stringsLinkedList1));
        System.out.println();

        System.out.println("9. Добавление коллекции:");
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        LinkedList<String> stringsLinkedList2 = new LinkedList<>();
        stringsLinkedList2.add("Малиновый");
        stringsLinkedList2.add("Бирюзовый");
        stringsLinkedList2.add("Гранатовый");
        stringsLinkedList2.add("Оранжевый");
        stringsLinkedList2.add("Голубой");
        System.out.println("Коллекция:");
        System.out.println(stringsLinkedList2);
        System.out.println("Результат: " + colorsTable3.addAll(stringsLinkedList2));
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println("Количество элементов в таблице: " + colorsTable3.size());
        System.out.println("Размер таблицы: " + colorsTable3.getTableCapacity());
        System.out.println();

        System.out.println("10. Удаление коллекции:");
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        LinkedList<String> stringsLinkedList3 = new LinkedList<>();
        stringsLinkedList3.add("Черный");
        stringsLinkedList3.add("Белый");
        stringsLinkedList3.add("Голубой");
        System.out.println("Коллекция:");
        System.out.println(stringsLinkedList3);
        System.out.println("Результат: " + colorsTable3.removeAll(stringsLinkedList3));
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println();

        System.out.println("11. Использование метода retainAll:");
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        LinkedList<String> stringsLinkedList4 = new LinkedList<>();
        stringsLinkedList4.add("Малиновый");
        stringsLinkedList4.add("Синий");
        stringsLinkedList4.add("Оранжевый");
        System.out.println(stringsLinkedList4);
        System.out.println("Результат: " + colorsTable3.retainAll(stringsLinkedList4));
        System.out.println("Содержимое таблицы:");
        System.out.println(Arrays.toString(colorsTable3.toArray()));
        System.out.println();

        System.out.println("12. Использование метода toArray(T1[] a):");
        String[] array = new String[10];
        System.out.println(Arrays.toString(colorsTable3.toArray(array)));
    }
}