package ru.academits.danilov_e.arraylist_main;

import ru.academits.danilov_e.arraylist.ArrayList;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayListMain {
    public static String toString(ArrayList<?> arrayList) {
        if (arrayList.isEmpty()) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (Object item : arrayList) {
            if (item != null) {
                stringBuilder.append(item).append(", ");
            } else {
                stringBuilder.append("null").append(", ");
            }
        }

        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1)
                .append(']').toString();
    }

    public static <E> void printResult(ArrayList<E> list, boolean isPrintResult) {
        System.out.println(((isPrintResult) ? "Результат: " : "Исходный list: ") + toString(list));
        System.out.println("Размер: " + list.size() + ". Вместимость: " + list.getCapacity());
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("1. Создание пустого ArrayList:");
        ArrayList<String> colorsList1 = new ArrayList<>();
        printResult(colorsList1, true);

        int capacity1 = 10;
        System.out.println("2. Создание пустого ArrayList с заданным значением capacity = " + capacity1 + ":");

        ArrayList<String> colorsList2 = new ArrayList<>(capacity1);
        printResult(colorsList2, true);

        String color1 = "Красный";
        System.out.println("3. Добавление элемента " + color1 + " в ArrayList:");

        colorsList1.add(color1);
        printResult(colorsList1, true);

        System.out.println("4. Увеличение размера массива под элементы при его заполненности:");

        colorsList2.add("Красный");
        colorsList2.add("Зеленый");
        colorsList2.add("Желтый");
        colorsList2.add("Оранжевый");
        colorsList2.add("Голубой");
        colorsList2.add("Синий");
        colorsList2.add("Фиолетовый");
        colorsList2.add("Коричневый");
        colorsList2.add("Серый");
        colorsList2.add("Белый");
        colorsList2.add("Черный");

        printResult(colorsList2, true);

        System.out.println("5. Конструктор копирования ArrayList:");
        ArrayList<String> colorsList3 = new ArrayList<>(colorsList2);
        printResult(colorsList3, true);

        System.out.println("6. Проверка метода isEmpty():");
        ArrayList<String> colorsList4 = new ArrayList<>();
        System.out.println("ArrayList: " + toString(colorsList4));
        System.out.println("Результат для пустого ArrayList = " + colorsList4.isEmpty());
        System.out.println("ArrayList: " + toString(colorsList3));
        System.out.println("Результат для не пустого ArrayList = " + colorsList3.isEmpty());
        System.out.println();

        System.out.println("7. Проверка содержит ли ArrayList элемент:");
        String color2 = "Красный";
        String color3 = "Розовый";

        System.out.println("ArrayList: " + toString(colorsList2));
        System.out.println("Результат для цвета " + color2 + ": " + colorsList2.contains(color2));
        System.out.println("Результат для цвета " + color3 + ": " + colorsList2.contains(color3));
        System.out.println();

        System.out.println("8. Получение элемента ArrayList по индексу:");
        System.out.println("ArrayList: " + toString(colorsList3));
        int index1 = 1;

        System.out.println("Второй элемент: " + colorsList2.get(index1));
        System.out.println();

        System.out.println("9. Замена элемента ArrayList по индексу:");
        System.out.println("ArrayList: " + toString(colorsList3));
        int index2 = 1;
        String color4 = "Малиновый";

        System.out.println("Заменяемый элемент: " + colorsList3.set(index2, color4));
        System.out.println("Результат: " + toString(colorsList3));
        System.out.println();

        System.out.println("10. Получение индекса по элементу ArrayList:");
        System.out.println("ArrayList: " + toString(colorsList3));
        String color5 = "Голубой";

        System.out.println("Индекс элемента " + color5 + ": " + colorsList2.indexOf(color5));
        System.out.println();

        System.out.println("11. Получение индекса последнего нахождения элемента ArrayList:");
        int index3 = 9;

        colorsList3.set(index3, color5);
        System.out.println("ArrayList: " + toString(colorsList3));
        System.out.println("Последний индекс элемента " + color5 + ": " + colorsList3.lastIndexOf(color5));
        System.out.println();

        System.out.println("12. Перемещение итератора по ArrayList:");
        Iterator<String> iterator = colorsList2.iterator();
        System.out.println("ArrayList: " + toString(colorsList2));
        System.out.println("Первый элемент: " + iterator.next());
        System.out.println("Второй элемент: " + iterator.next());
        System.out.println("Третий элемент: " + iterator.next());
        System.out.println();

        System.out.println("13. Очистка ArrayList:");
        printResult(colorsList3, false);
        colorsList3.clear();
        printResult(colorsList3, true);

        System.out.println("14. Метод toArray():");
        Object[] objectsArray = colorsList2.toArray();

        for (Object string : objectsArray) {
            System.out.println(string);
        }

        System.out.println();

        String color6 = "Пурпурный";

        System.out.println("15. Добавление элемента " + color6 + " в начало ArrayIndex по индексу:");
        printResult(colorsList2, false);
        int index4 = 0;

        colorsList2.add(index4, color6);
        printResult(colorsList2, true);

        String color7 = "Голубой";
        System.out.println("16. Добавление элемента " + color7 + " в середину ArrayIndex по индексу:");
        printResult(colorsList2, false);
        int index5 = colorsList2.size() / 2;

        colorsList2.add(index5, color7);
        printResult(colorsList2, true);

        String color8 = "Бежевый";
        System.out.println("17. Добавление элемента " + color8 + " предпоследним в ArrayIndex по индексу:");
        printResult(colorsList2, false);
        int index6 = colorsList2.size() - 1;

        colorsList2.add(index6, color8);
        printResult(colorsList2, true);

        String color9 = "Перламутровый";
        System.out.println("18. Добавление элемента " + color9 + " в конец в ArrayIndex по индексу:");
        printResult(colorsList2, false);
        int index7 = colorsList2.size();

        colorsList2.add(index7, color9);
        printResult(colorsList2, true);

        String color10 = "Пурпурный";
        System.out.println("19. Удаление элемента " + color10 + " в начале ArrayIndex по значению элемента:");
        printResult(colorsList2, false);

        colorsList2.remove(color10);
        printResult(colorsList2, true);

        String color11 = "Синий";
        System.out.println("20. Удаление элемента " + color11 + " из середины ArrayIndex по значению элемента:");
        printResult(colorsList2, false);

        colorsList2.remove(color11);
        printResult(colorsList2, true);

        String color12 = "Перламутровый";
        System.out.println("21. Удаление элемента " + color12 + " с конца ArrayIndex по значению элемента:");
        printResult(colorsList2, false);

        colorsList2.remove(color12);
        printResult(colorsList2, true);

        String color13 = "Голубой";
        System.out.println("22. Удаление двойного элемента " + color13 + " из ArrayIndex по значению элемента с сокращением"
                + " длинны массива:");
        printResult(colorsList2, false);

        colorsList2.remove(color13);
        printResult(colorsList2, true);

        System.out.println("23. Удаление элемента с начала из ArrayIndex по индексу элемента:");
        printResult(colorsList2, false);
        int index8 = 0;

        System.out.println("Удаленный элемент:" + colorsList2.remove(index8));
        printResult(colorsList2, true);

        System.out.println("24. Удаление элемента с середины из ArrayIndex по индексу элемента:");
        printResult(colorsList2, false);
        int index9 = 4;

        System.out.println("Удаленный элемент:" + colorsList2.remove(index9));
        printResult(colorsList2, true);

        System.out.println("25. Удаление элемента с конца из ArrayIndex по индексу элемента:");
        printResult(colorsList2, false);
        int index10 = colorsList2.size() - 1;

        System.out.println("Удаленный элемент:" + colorsList2.remove(index10));
        printResult(colorsList2, true);

        System.out.println("25. Проверка совпадения коллекции в ArrayList полном совпадении:");
        printResult(colorsList2, false);

        ArrayList<String> colorsList5 = new ArrayList<>();
        colorsList5.add("Желтый");
        colorsList5.add("Серый");
        System.out.println("Искомая коллекция: " + toString(colorsList5));
        System.out.println("Размер ArrayList: " + colorsList5.size() + ". Размер массива под элементы: " + colorsList5.getCapacity());
        System.out.println("Результат: " + colorsList2.containsAll(colorsList5));
        System.out.println();

        System.out.println("26. Проверка совпадения коллекции в ArrayList при частичном совпадении:");
        printResult(colorsList2, false);

        ArrayList<String> colorsList6 = new ArrayList<>();
        colorsList6.add("Желтый");
        colorsList6.add("Сливовый");
        System.out.println("Искомая коллекция: " + toString(colorsList6));
        System.out.println("Размер ArrayList: " + colorsList6.size() + ". Размер массива под элементы: " + colorsList6.getCapacity());
        System.out.println("Результат: " + colorsList2.containsAll(colorsList6));
        System.out.println();

        System.out.println("27. Проверка совпадения коллекции в ArrayList при отсутствии совпадении:");
        printResult(colorsList2, false);

        ArrayList<String> colorsList7 = new ArrayList<>();
        colorsList7.add("Золотой");
        colorsList7.add("Сливовый");
        System.out.println("Искомая коллекция: " + toString(colorsList7));
        System.out.println("Размер ArrayList: " + colorsList7.size() + ". Размер массива под элементы: " + colorsList7.getCapacity());
        System.out.println("Результат: " + colorsList2.containsAll(colorsList7));
        System.out.println();

        System.out.println("28. Добавляем коллекцию в начало ArrayList:");
        printResult(colorsList2, false);

        ArrayList<String> colorsList8 = new ArrayList<>();
        colorsList8.add("Золотой");
        colorsList8.add("Сливовый");
        System.out.println("Искомая коллекция: " + toString(colorsList8));
        System.out.println("Размер ArrayList: " + colorsList8.size() + ". Размер массива под элементы: " + colorsList8.getCapacity());
        colorsList2.addAll(colorsList8);
        printResult(colorsList2, true);

        System.out.println("29. Добавляем коллекцию в ArrayList с индекса:");
        printResult(colorsList2, false);
        int index11 = 2;

        ArrayList<String> colorsList9 = new ArrayList<>();
        colorsList9.add("Серебряный");
        colorsList9.add("Кирпичный");
        System.out.println("Искомая коллекция: " + toString(colorsList9));
        System.out.println("Размер ArrayList: " + colorsList9.size() + ". Размер массива под элементы: " + colorsList9.getCapacity());
        System.out.println("Индекс начальной позиции: " + index11);
        colorsList2.addAll(index11, colorsList9);
        printResult(colorsList2, true);

        System.out.println("30. Удаление коллекции в ArrayList с индекса при полном совпадении:");
        colorsList2.add("Серебряный");
        printResult(colorsList2, false);

        ArrayList<String> colorsList10 = new ArrayList<>();
        colorsList10.add("Серебряный");
        colorsList10.add("Кирпичный");
        System.out.println("Искомая коллекция: " + toString(colorsList10));
        System.out.println("Размер ArrayList: " + colorsList10.size() + ". Размер массива под элементы: " + colorsList10.getCapacity());
        colorsList2.removeAll(colorsList10);
        printResult(colorsList2, true);

        System.out.println("31. Удаление из коллекции элементов не находящихся в другой коллекции :");
        printResult(colorsList2, false);

        ArrayList<String> colorsList11 = new ArrayList<>();
        colorsList11.add("Желтый");
        colorsList11.add("Бежевый");
        colorsList11.add("Малиновый");
        colorsList11.add("Сливовый");
        colorsList11.add("Графитовый");
        System.out.println("Искомая коллекция: " + toString(colorsList11));
        System.out.println("Размер ArrayList: " + colorsList11.size() + ". Размер массива под элементы: " + colorsList11.getCapacity());
        System.out.println("Результат операции: " + colorsList2.retainAll(colorsList11));
        printResult(colorsList2, true);

        System.out.println("32. Удаление из коллекции элементов не находящихся в другой коллекции :");
        printResult(colorsList2, false);

        ArrayList<String> colorsList12 = new ArrayList<>();
        colorsList12.add("Сизый");
        colorsList12.add("Гранатовый");

        System.out.println("Искомая коллекция: " + toString(colorsList12));
        System.out.println("Размер ArrayList: " + colorsList12.size() + ". Размер массива под элементы: " + colorsList12.getCapacity());
        System.out.println("Результат операции: " + colorsList2.retainAll(colorsList12));
        printResult(colorsList2, true);

        System.out.println("33. Функция public <T> T[] toArray(T[] a):");
        colorsList2.add("Красный");
        colorsList2.add("Зеленый");
        colorsList2.add("Желтый");
        colorsList2.add("Оранжевый");
        colorsList2.add("Голубой");
        System.out.println("Исходный ArrayList: " + toString(colorsList2));

        String[] colorsArray1 = new String[3];
        System.out.println("Случай с созданием нового массива: " + Arrays.toString(colorsList2.toArray(colorsArray1)));
        String[] colorsArray2 = new String[10];
        System.out.println("Случай с использованием старого массива: " + Arrays.toString(colorsList2.toArray(colorsArray2)));
        System.out.println();

        System.out.println("34. Нулевая размерность");
        ArrayList<String> colorsList13 = new ArrayList<>(0);
        printResult(colorsList13, false);
        colorsList13.add("Сизый");
        printResult(colorsList13, false);
        colorsList13.trimToSize();
        printResult(colorsList13, false);
    }
}