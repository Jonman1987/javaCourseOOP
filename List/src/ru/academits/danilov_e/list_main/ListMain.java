package ru.academits.danilov_e.list_main;

import ru.academits.danilov_e.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        System.out.println("1. Создаем List:");
        System.out.println("Конструктор на массиве:");
        SinglyLinkedList<String> colorsList1 = new SinglyLinkedList<>(new String[]{"Синий", "Красный", "Черный"});
        System.out.println(colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
        System.out.println("Одиночный конструктор:");
        SinglyLinkedList<String> colorsList2 = new SinglyLinkedList<>("Бирюзовый");
        System.out.println(colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();

        System.out.println("2. Получаем значение размера списка:");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();

        System.out.println("3. Получаем значение узла первого элемента:");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Значение: " + colorsList1.getFirst());
        System.out.println();
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Значение: " + colorsList2.getFirst());
        System.out.println();

        System.out.println("4. Получаем значение узла по номеру index:");
        int index1 = 2;
        int index2 = 1;
        int index6 = 0;
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Индекс узла: " + index1);
        System.out.println("Значение: " + colorsList1.get(index1));
        System.out.println("Индекс узла: " + index2);
        System.out.println("Значение: " + colorsList1.get(index2));
        System.out.println();
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Индекс узла: " + index6);
        System.out.println("Значение: " + colorsList2.get(index6));
        System.out.println("Индекс узла: " + index6);
        System.out.println("Значение: " + colorsList2.get(index6));
        System.out.println();

        System.out.println("5. Изменяем значение узла по номеру index:");
        int index3 = 2;
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println("Индекс узла: " + index3);
        System.out.println("Возвращаемое значение старого узла: " + colorsList1.set(index3, "Зеленый"));
        System.out.println("Новое значение: " + colorsList1.get(index3));
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println("Индекс узла: " + index6);
        System.out.println("Возвращаемое значение старого узла: " + colorsList2.set(index6, "Терракотовый"));
        System.out.println("Новое значение: " + colorsList2.get(index6));
        System.out.println("Новый список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();

        System.out.println("6. Удаление узла по номеру index:");
        int index4 = 2;
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Индекс узла: " + index4);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println("Возвращаемое значение удаляемого узла: " + colorsList1.delete(index4));
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
        /*System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Индекс узла: " + index6);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println("Возвращаемое значение удаляемого узла: " + colorsList2.delete(index6));
        System.out.println("Новый список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());*/
        System.out.println("Необходимо раскомментировать код");
        System.out.println();

        System.out.println("7. Вставка элемента в начало:");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        colorsList1.addFirst("Пурпурный");
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        colorsList2.addFirst("Розовый");
        System.out.println("Новый список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();

        System.out.println("8. Вставка элемента по номеру index:");
        int index5 = 3;
        int index7 = 1;
        int index8 = 2;
        int index9 = 4;
        String newColor1 = "Бордовый";
        String newColor2 = "Сиреневый";
        String newColor3 = "Серый";
        String newColor4 = "Белый";
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Индекс узла: " + index5);
        System.out.println("Размер списка: " + colorsList1.getCount());
        colorsList1.add(index5, newColor1);
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();
        System.out.println("Вставка в середину:");
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Индекс узла: " + index7);
        System.out.println("Размер списка: " + colorsList2.getCount());
        colorsList2.add(index7, newColor2);
        System.out.println("Новый список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();
        System.out.println("Вставка предпоследним:");
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Индекс узла: " + index8);
        System.out.println("Размер списка: " + colorsList2.getCount());
        colorsList2.add(index8, newColor3);
        System.out.println("Новый список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();
        System.out.println("Вставка в конец:");
        System.out.println("Изначальный список: " + colorsList2);
        System.out.println("Индекс узла: " + index9);
        System.out.println("Размер списка: " + colorsList2.getCount());
        colorsList2.add(index9, newColor4);
        System.out.println("Новый список: " + colorsList2);
        System.out.println("Размер списка: " + colorsList2.getCount());
        System.out.println();

        System.out.println("9. Удаление узла по значению:");
        String string1 = "Синий";
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println("Искомое удаляемое значение: " + string1);
        System.out.println("Результат: " + colorsList1.deleteByData(string1));
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();

        System.out.println("10. Удаление первого узла:");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println("Возвращаемое значение удаляемого узла: " + colorsList1.deleteFirst());
        System.out.println("Новый список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        System.out.println();

        System.out.println("11. Разворот List:");
        SinglyLinkedList<String> colorsList3 = new SinglyLinkedList<>(new String[]{"Фиолетовый", "Желтый","Зеленый"});
        System.out.println("Изначальный список: " + colorsList3);
        System.out.println("Размер списка: " + colorsList3.getCount());
        colorsList3.reverse();
        System.out.println("Новый список: " + colorsList3);
        System.out.println("Размер списка: " + colorsList3.getCount());
        System.out.println();

        System.out.println("12. Копирование List:");
        colorsList1.addFirst("Бирюзовый");
        colorsList1.addFirst("Оранжевый");
        System.out.println("Изначальный список: " + colorsList1);
        System.out.println("Размер списка: " + colorsList1.getCount());
        SinglyLinkedList<String> colorsList4 = colorsList1.copy();
        System.out.println("Новый список: " + colorsList4);
        System.out.println("Размер списка: " + colorsList4.getCount());
    }
}
