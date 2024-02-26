package ru.academits.danilov_e.arraylisthome_main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHomeMain {
    public static ArrayList<Integer> getArrayListWithoutRepeats(ArrayList<Integer> integerList) {
        ArrayList<Integer> integerListWithoutRepeats = new ArrayList<>();

        integerListWithoutRepeats.add(integerList.getFirst());

        for (int i = 1; i < integerList.size(); i++) {
            if (!integerListWithoutRepeats.contains(integerList.get(i))) {
                integerListWithoutRepeats.add(integerList.get(i));
            }
        }

        return integerListWithoutRepeats;
    }

    public static void addToListLinesFromFile(ArrayList<String> list, String inputPath) {
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка открытия файла");
        }
    }

    public static void removeEvenNumbersFromList(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(i); // Я прочитал, что это не безопасный способ удаления, поэтому есть предупреждение
            }
        }
    }

    public static void main(String[] args) {
        String inputPath = "ArrayListHome/src/input.txt";
        ArrayList<String> list = new ArrayList<>();

        System.out.println("Задача ArrayListHome пункт 1.");
        addToListLinesFromFile(list, inputPath);
        System.out.println(list);
        System.out.println();

        System.out.println("Задача ArrayListHome пункт 2.");
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        System.out.println("Список:");
        System.out.println(integerList);
        System.out.println("Список без четных чисел:");
        removeEvenNumbersFromList(integerList);
        System.out.println(integerList);
        System.out.println();

        System.out.println("Задача ArrayListHome пункт 3.");
        ArrayList<Integer> integerListWithRepeats = new ArrayList<>(Arrays.asList(1, 9, 2, 1, 7, 1, 3, 2, 4, 5, 5, 6, 7, 8, 9));
        System.out.println("Список с повторами:");
        System.out.println(integerListWithRepeats);
        System.out.println("Список без повторов:");
        System.out.println(getArrayListWithoutRepeats(integerListWithRepeats));
    }
}