package ru.academits.danilov_e.csv_main;

import ru.academits.danilov_e.csv.Csv;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvMain {
    public static void main(String[] args) {
        Scanner scanner;

        String path = "Csv/src/ru/academits/danilov_E/csv_main/";
        String inputFileName = "input.txt";
        String outputFileName = "output.html";

        try {
            scanner = new Scanner(new FileInputStream(path + inputFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Csv.createFile(path + outputFileName); // Я решил сделать через статический метод для разнообразия, возможно так делать нельзя
        Csv.writeHeader(path + outputFileName);
        Csv csv = new Csv();

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            csv.setString(currentLine);
            csv.writeRow(path + outputFileName);
        }

        Csv.writeFooter(path + outputFileName);
    }
}