package ru.academits.danilov_e.arraylisthome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static ArrayList<?> getListWithoutRepeats(ArrayList<?> objectsList) {
        ArrayList<Object> objectsListWithoutRepeats = new ArrayList<>(objectsList.size());

        for (Object object : objectsList) {
            if (!objectsListWithoutRepeats.contains(object)) {
                objectsListWithoutRepeats.add(object);
            }
        }

        return objectsListWithoutRepeats;
    }

    public static ArrayList<String> getFileLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<String> fileLinesArrayList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                fileLinesArrayList.add(line);
            }

            return fileLinesArrayList;
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
        String filePath = "ArrayListHome/src/input.txt";

        System.out.println("Задача ArrayListHome пункт 1.");

        try {
            ArrayList<String> fileLinesList = getFileLines(filePath);
            System.out.println(fileLinesList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + filePath + " не найден");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("Задача ArrayListHome пункт 2.");
        ArrayList<Integer> integersList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

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