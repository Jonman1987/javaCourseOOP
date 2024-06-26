package ru.academits.danilov_e.arraylisthome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static <E> ArrayList<E> getListWithoutRepeats(ArrayList<E> list) {
        ArrayList<E> listWithoutRepeats = new ArrayList<>(list.size());

        for (E item : list) {
            if (!listWithoutRepeats.contains(item)) {
                listWithoutRepeats.add(item);
            }
        }

        return listWithoutRepeats;
    }

    public static ArrayList<String> getFileLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> fileLinesList = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                fileLinesList.add(line);
            }

            return fileLinesList;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbersList) {
        for (int i = numbersList.size() - 1; i >= 0; i--) {
            if (numbersList.get(i) % 2 == 0) {
                numbersList.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "ArrayListHome/src/input.txt";

        System.out.println("Задача ArrayListHome пункт 1.");

        try {
            ArrayList<String> fileLinesList = getFileLines(filePath);
            System.out.println(fileLinesList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + filePath + " не найден");
        } catch (IOException e) {
            System.out.println("Ошибка считывания данных из файла " + filePath + ":");
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Задача ArrayListHome пункт 2.");
        ArrayList<Integer> integersList = new ArrayList<>(Arrays.asList(1, 1, 0, -2, -1, 2, 2, 2, 3, 4, 5, 6, 7, 8, 9));

        System.out.println("Список:");
        System.out.println(integersList);
        System.out.println("Список без четных чисел:");
        removeEvenNumbers(integersList);
        System.out.println(integersList);
        System.out.println();

        System.out.println("Задача ArrayListHome пункт 3.");
        ArrayList<Integer> integersListWithRepeats = new ArrayList<>(Arrays.asList(1, 9, 2, 1, 7, 1, 3, 2, 4, 5, 5, 6, 7, 8, 9));
        System.out.println("Список с повторами:");
        System.out.println(integersListWithRepeats);
        System.out.println("Список без повторов:");
        System.out.println(getListWithoutRepeats(integersListWithRepeats));
    }
}