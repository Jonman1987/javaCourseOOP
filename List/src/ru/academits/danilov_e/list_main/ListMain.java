package ru.academits.danilov_e.list_main;

import ru.academits.danilov_e.list.List;

public class ListMain {
    public static void main(String[] args) {
        System.out.println("1. Создаем пустой List:");
        List list1 = new List();
        System.out.println("Размер данного List: " + list1.getSize() + ".");
        System.out.println();

        System.out.println("2. Создаем лист со значениями:");
        List list2 = new List(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("Печатаем List: " + list2);
        System.out.println("Размер данного List: " + list2.getSize() + ".");
        System.out.println();

        System.out.println("3. Получение первого элемента на пустом List:");
        // System.out.println(list1.getFirst());
        System.out.println("Необходимо раскомментировать код.");
        System.out.println();

        System.out.println("4. Получение первого элемента на не пустом List:");
        System.out.println("Печатаем List: " + list2);
        System.out.println("Первый элемент: " + list2.getFirst() + ".");
        System.out.println();

        System.out.println("5. Получение элемента по индексу:");
        System.out.println("Печатаем List: " + list2);
        System.out.println("Пятый элемент: " + list2.get(4) + "."); // Могу переписать, чтобы выдавался без учета значения 0
        System.out.println("Вызов на пустом массиве:");
        // System.out.println("Пятый элемент: " + list1.get(4) + ".");
        System.out.println("Необходимо раскомментировать код.");
        System.out.println("Указание не допустимого индекса:");
        // System.out.println("Двадцатый элемент: " + list2.get(19) + ".");
        // System.out.println("Минус первый элемент: " + list2.get(-1) + ".");
        System.out.println("Необходимо раскомментировать код.");
        System.out.println();

        System.out.println("6. Замена элемента по номеру позиции:");
        System.out.println("Печатаем List: " + list2);
        System.out.println("Заменяемый элемент: " + list2.set(4, "Слово") + ".");
        System.out.println("Печатаем List: " + list2);
        System.out.println("Вызов на пустом массиве:");
        // System.out.println("Заменяемый элемент: " + list1.set(0, "Слово") + ".");
        System.out.println("Необходимо раскомментировать код.");
        System.out.println("Указание не допустимого индекса:");
        // System.out.println("Заменяемый элемент: " + list2.set(23, "Слово") + ".");
        System.out.println("Необходимо раскомментировать код.");
        System.out.println();

        System.out.println("6. Удаление элемента по индексу:");
        System.out.println("Печатаем List: " + list2);
        System.out.println("Размер данного List: " + list2.getSize() + ".");
        System.out.println("Длинна используемого массива: " + list2.getArrayLength() + ".");
        System.out.println("Удаляем элемент: " + list2.remove(2) + ".");
        System.out.println("Печатаем List: " + list2);
        System.out.println("Размер измененного List: " + list2.getSize() + ".");
        System.out.println("Длинна используемого массива: " + list2.getArrayLength() + ".");
        System.out.println("Создадим ситуацию, когда массив сначала увеличился до размера 20 элементов.");
        System.out.println("Затем из этих 20 элементов мы удалили 9. И теперь возникает ситуация, когда удаляя еще " +
                "один элемент массива, мы должны сократить размер используемого массива.");
        List list3 = new List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 11);
        list3.setArrayLength(20);
        System.out.println("Печатаем List: " + list3);
        System.out.println("Размер данного List: " + list3.getSize() + ".");
        System.out.println("Длинна используемого массива: " + list3.getArrayLength() + ".");
        System.out.println("Удаляем элемент: " + list3.remove(2) + ".");
        System.out.println("Печатаем List: " + list3);
        System.out.println("Размер данного List: " + list3.getSize() + ".");
        System.out.println("Длинна используемого массива: " + list3.getArrayLength() + ".");
    }
}