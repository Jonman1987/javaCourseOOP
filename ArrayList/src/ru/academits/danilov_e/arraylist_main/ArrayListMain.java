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

        int capacity1 = 20;
        System.out.println("2. Создание пустого ArrayList с заданным значением capacity = " + capacity1 + ":");

        ArrayList<String> colorList2 = new ArrayList<>(capacity1);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        String color1 = "Красный";
        System.out.println("3. Добавление элемента " + color1 + " в ArrayList:");

        colorList1.add(color1);
        System.out.println("Результат:");
        System.out.println(colorList1);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList1.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList1.getArrayLength());
        System.out.println();

        System.out.println("4. Увеличение размера массива под элементы при его заполненности:");
        String color2 = "Красный";
        String color3 = "Зеленый";
        String color4 = "Желтый";
        String color5 = "Оранжевый";
        String color6 = "Голубой";
        String color7 = "Синий";
        String color8 = "Фиолетовый";
        String color9 = "Коричневый";
        String color10 = "Серый";
        String color11 = "Белый";
        String color12 = "Черный";


        colorList2.add(color2);
        colorList2.add(color3);
        colorList2.add(color4);
        colorList2.add(color5);
        colorList2.add(color6);
        colorList2.add(color7);
        colorList2.add(color8);
        colorList2.add(color9);
        colorList2.add(color10);
        colorList2.add(color11);
        colorList2.add(color12);

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
        System.out.println();

        System.out.println("7. Проверка содержит ли ArrayList элемент:");
        String color13 = "Красный";
        String color14 = "Розовый";

        System.out.println("Результат для цвета " + color13 + ": " + colorList2.contains(color13));
        System.out.println("Результат для цвета " + color14 + ": " + colorList2.contains(color14));
        System.out.println();

        System.out.println("8. Получение элемента ArrayList по индексу:");
        System.out.println("ArrayList:");
        System.out.println(colorList3);
        int index1 = 1;

        System.out.println("Второй элемент: " + colorList2.get(index1));
        System.out.println();

        System.out.println("9. Замена элемента ArrayList по индексу:");
        System.out.println("ArrayList:");
        System.out.println(colorList3);
        int index2 = 1;
        String color15 = "Малиновый";

        System.out.println("Заменяемый элемент: " + colorList3.set(index2, color15));

        System.out.println("Результат:");
        System.out.println(colorList3);
        System.out.println();

        System.out.println("10. Получение индекса по элементу ArrayList:");
        System.out.println("ArrayList:");
        System.out.println(colorList3);
        String color16 = "Голубой";

        System.out.println("Индекс элемента " + color16 + ": " + colorList2.indexOf(color16));
        System.out.println();

        System.out.println("11. Получение индекса последнего нахождения элемента ArrayList:");
        int index3 = 9;

        colorList3.set(index3, color16);
        System.out.println("ArrayList:");
        System.out.println(colorList3);
        System.out.println("Последний индекс элемента " + color16 + ": " + colorList2.lastIndexOf(color16));
        System.out.println();

        System.out.println("12. Перемещение итератора по ArrayList:");
        ArrayList<String>.ArrayListIterator iterator = colorList2.iterator();
        System.out.println("ArrayList:");
        System.out.println(colorList3);
        System.out.println("Первый элемент:");
        System.out.println(iterator.next());
        System.out.println("Второй элемент:");
        System.out.println(iterator.next());
        System.out.println("Третий элемент:");
        System.out.println(iterator.next());
        System.out.println("Повторное обращение к третьему элементу:");
        System.out.println(iterator.current());
        System.out.println();

        System.out.println("13. Очистка ArrayList:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList3);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList3.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList3.getArrayLength());
        colorList3.clear();
        System.out.println("Результат:");
        System.out.println(colorList3);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList3.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList3.getArrayLength());
        System.out.println();

        System.out.println("14. Метод toArray():");
        Object[] el = colorList2.toArray();

        for (Object string : el) {
            System.out.println(string);
        }

        System.out.println();

        String color17 = "Пурпурный";
        System.out.println("15. Добавление элемента " + color17 + " в начало ArrayIndex по индексу:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index4 = 0;

        colorList2.add(index4, color17);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        String color18 = "Бирюзовый";
        System.out.println("16. Добавление элемента " + color18 + " в середину ArrayIndex по индексу:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index5 = colorList2.size() / 2;

        colorList2.add(index5, color18);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        String color19 = "Бежевый";
        System.out.println("17. Добавление элемента " + color19 + " предпоследним в ArrayIndex по индексу:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index6 = colorList2.size() - 1;

        colorList2.add(index6, color19);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        String color20 = "Перламутровый";
        System.out.println("18. Добавление элемента " + color20 + " в конец в ArrayIndex по индексу:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index7 = colorList2.size();

        colorList2.add(index7, color20);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        String color21 = "Пурпурный";
        System.out.println("19. Удаление элемента " + color21 + " в начале ArrayIndex по значению элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());

        colorList2.remove(color21);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        String color22 = "Синий";
        System.out.println("20. Удаление элемента " + color22 + " из середины ArrayIndex по значению элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());

        colorList2.remove(color22);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        String color23 = "Перламутровый";
        System.out.println("21. Удаление элемента " + color23 + " с конца ArrayIndex по значению элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());

        colorList2.remove(color23);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        String color24 = "Голубой";
        System.out.println("22. Удаление двойного элемента " + color24 + " из ArrayIndex по значению элемента с сокращением"
                + " длинны массива:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());

        colorList2.remove(color24);
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("23. Удаление элемента с начала из ArrayIndex по индексу элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index8 = 0;

        System.out.println("Удаленный элемент:" + colorList2.remove(index8));
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("24. Удаление элемента с середины из ArrayIndex по индексу элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index9 = 4;

        System.out.println("Удаленный элемент:" + colorList2.remove(index9));
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("25. Удаление элемента с конца из ArrayIndex по индексу элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index10 = colorList2.size() - 1;

        System.out.println("Удаленный элемент:" + colorList2.remove(index10));
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();
    }
}