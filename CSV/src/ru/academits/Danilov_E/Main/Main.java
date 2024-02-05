package ru.academits.Danilov_E.Main;

import ru.academits.Danilov_E.CSV.CSV;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;

        String path = "CSV/src/ru/academits/Danilov_E/Main/";
        String inputFileName = "input.txt";
        String outputFileName = "output.html";

        try {
            scanner = new Scanner(new FileInputStream(path + inputFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        CSV.createFile(path + outputFileName); // Я решил сделать через статический метод для разнообразия, возможно так делать нельзя
        CSV.writeHeader(path + outputFileName);
        CSV csv = new CSV();

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            csv.setString(currentLine);
            csv.writeRow(path + outputFileName);
        }

        CSV.writeFooter(path + outputFileName);
    }
}