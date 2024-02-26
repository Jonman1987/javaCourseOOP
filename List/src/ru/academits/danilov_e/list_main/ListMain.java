package ru.academits.danilov_e.list_main;

import ru.academits.danilov_e.list.List;

public class ListMain {
    public static void main(String[] args) {
        System.out.println("1. Создаем пустой лист:");
        List list1 = new List();
        System.out.println("Размер данного листа: " + list1.getSize() + ".");
        System.out.println();

        System.out.println("2. Создаем лист со значениями:");
        List list2 = new List(1, 2);
        System.out.println("Печатаем List: " + list2);
        System.out.println("Размер данного листа: " + list2.getSize() + ".");
        System.out.println();

        System.out.println("3. Получение первого элемента на пустом List:");
        //System.out.println(list1.getFirst());
        System.out.println("Необходимо раскомментировать код.");
        System.out.println();

        System.out.println("4. Получение первого элемента на не пустом List:");
        System.out.println("Печатаем List: " + list2);
        System.out.println("Первый элемент: " + list2.getFirst() + ".");
        System.out.println();


    }
}