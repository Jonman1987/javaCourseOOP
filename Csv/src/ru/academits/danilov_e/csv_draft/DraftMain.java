package ru.academits.danilov_e.csv_draft;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DraftMain {
    public static void main(String[] args) {
        Scanner scanner;

        String path = "Csv/src/ru/academits/danilov_E/csv_main/";
        String inputFileName = "input.csv";
        String outputFileName = "output.html";

        try {
            scanner = new Scanner(new FileInputStream(path + inputFileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Draft.createFile(path + outputFileName); // Я решил сделать через статический метод для разнообразия, возможно так делать нельзя
        Draft.writeHeader(path + outputFileName);
        Draft csv = new Draft();

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            csv.setString(currentLine);
            csv.writeRow(path + outputFileName);
        }

        Draft.writeFooter(path + outputFileName);
    }
}