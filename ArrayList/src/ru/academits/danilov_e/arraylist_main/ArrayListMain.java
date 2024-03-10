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
        int capacity1 = 20;

        ArrayList<String> colorList2 = new ArrayList<>(capacity1);
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
        System.out.println();

        System.out.println("7. Проверка содержит ли ArrayList элемент:");
        System.out.println("Результат для цвета красный: " + colorList2.contains("Красный"));
        System.out.println("Результат для цвета розовый: " + colorList2.contains("Розовый"));
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

        System.out.println("Заменяемый элемент: " + colorList3.set(index2, "Малиновый"));
        System.out.println("Результат:");
        System.out.println(colorList3);
        System.out.println();

        System.out.println("10. Получение индекса по элементу ArrayList:");
        System.out.println("ArrayList:");
        System.out.println(colorList3);
        System.out.println("Индекс элемента \"Голубой\": " + colorList2.indexOf("Голубой"));
        System.out.println();

        System.out.println("11. Получение индекса последнего нахождения элемента ArrayList:");
        int index3 = 9;

        colorList3.set(index3, "Голубой");
        System.out.println("ArrayList:");
        System.out.println(colorList3);
        System.out.println("Последний индекс элемента \"Голубой\": " + colorList2.lastIndexOf("Голубой"));
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

        System.out.println("14. Метод toArray():"); // Если честно я пытался делать cast в E[] и преобразовывать
        // Object[] в String[], но у меня не получилось. Если можно поясните как работать в этим методом public Object[] toArray()
        Object[] el = colorList2.toArray();

        for (Object string : el) {
            System.out.println(string);
        }

        System.out.println();

        System.out.println("15. Добавление элемента \"Пурпурный\" в начало ArrayIndex по индексу:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index4 = 0;

        colorList2.add(index4, "Пурпурный");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("16. Добавление элемента \"Бирюзовый\" в середину ArrayIndex по индексу:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index5 = colorList2.size() / 2;

        colorList2.add(index5, "Бирюзовый");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("17. Добавление элемента \"Бежевый\" предпоследним в ArrayIndex по индексу:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index6 = colorList2.size() - 1;

        colorList2.add(index6, "Бежевый");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("18. Добавление элемента \"Перламутровый\" в конец в ArrayIndex по индексу:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        int index7 = colorList2.size();

        colorList2.add(index7, "Перламутровый");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("19. Удаление элемента \"Пурпурный\" в начале ArrayIndex по значению элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());

        colorList2.remove("Пурпурный");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("20. Удаление элемента \"Синий\" из середины ArrayIndex по значению элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());

        colorList2.remove("Синий");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("21. Удаление элемента \"Перламутровый\" с конца ArrayIndex по значению элемента:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());

        colorList2.remove("Перламутровый");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();

        System.out.println("21. Удаление двойного элемента \"Голубой\" из ArrayIndex по значению элемента с сокращением"
                + " длинны массива:");
        System.out.println("Исходный ArrayList:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());

        colorList2.remove("Голубой");
        System.out.println("Результат:");
        System.out.println(colorList2);
        System.out.println("Размер ArrayList:");
        System.out.println(colorList2.size());
        System.out.println("Размер массива под элементы:");
        System.out.println(colorList2.getArrayLength());
        System.out.println();
    }
}