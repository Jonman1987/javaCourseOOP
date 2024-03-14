package ru.academits.danilov_e.arraylisthome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static ArrayList<Integer> getListWithoutRepeats(ArrayList<Integer> integerList) {
        ArrayList<Integer> integerListWithoutRepeats = new ArrayList<>(integerList.size());

        for (Integer element : integerList) {
            if (!integerListWithoutRepeats.contains(element)) {
                integerListWithoutRepeats.add(element);
            }
        }

        return integerListWithoutRepeats;
    }

    public static ArrayList<String> readFile(String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            ArrayList<String> list = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                list.add(line);
            }

            return list;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "ArrayListHome/src/input.txt";

        System.out.println("Задача ArrayListHome пункт 1.");

        try {
            ArrayList<String> stringList = readFile(inputFilePath);
            System.out.println(stringList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + inputFilePath + " не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла " + inputFilePath);
        }

        System.out.println();

        System.out.println("Задача ArrayListHome пункт 2.");
        ArrayList<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        System.out.println("Список:");
        System.out.println(integerList);
        System.out.println("Список без четных чисел:");
        removeEvenNumbers(integerList);
        System.out.println(integerList);
        System.out.println();

        System.out.println("Задача ArrayListHome пункт 3.");
        ArrayList<Integer> integerListWithRepeats = new ArrayList<>(Arrays.asList(1, 9, 2, 1, 7, 1, 3, 2, 4, 5, 5, 6, 7, 8, 9));
        System.out.println("Список с повторами:");
        System.out.println(integerListWithRepeats);
        System.out.println("Список без повторов:");
        System.out.println(getListWithoutRepeats(integerListWithRepeats));
    }
}