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

        if (integerList.isEmpty()) {
            return integerListWithoutRepeats;
        }

        for (Integer arrayListElement : integerList) {
            if (!integerListWithoutRepeats.contains(arrayListElement)) {
                integerListWithoutRepeats.add(arrayListElement);
            }
        }

        return integerListWithoutRepeats;
    }

    public static ArrayList<String> addFileLines(String inputPath) throws IOException {
        String line;
        ArrayList<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        }

        return list;
    }

    public static void removeEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                list.remove(list.get(i));
            }
        }
    }

    public static void main(String[] args) {
        String inputFilePath = "ArrayListHome/src/input.txt";
        ArrayList<String> list = null;

        System.out.println("Задача ArrayListHome пункт 1.");

        try {
            list = addFileLines(inputFilePath);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + inputFilePath + " не найден");
        } catch (IOException e) {
            String error = e.toString();
            System.out.println(error);
        }

        System.out.println(list);
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