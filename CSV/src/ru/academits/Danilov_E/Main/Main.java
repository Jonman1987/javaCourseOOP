package ru.academits.Danilov_E.Main;

import ru.academits.Danilov_E.CSV.CSV;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream("CSV/src/ru/academits/Danilov_E/Main/input.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        FileWriter writer;

        try {
            writer = new FileWriter("CSV/src/ru/academits/Danilov_E/Main/output.html", true);
            CSV.createFile("CSV/src/ru/academits/Danilov_E/Main/output.html");
            CSV.writeHeader("CSV/src/ru/academits/Danilov_E/Main/output.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CSV csv = new CSV();

        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            System.out.println(currentLine);
            csv.setString(currentLine);
            csv.writeRow("CSV/src/ru/academits/Danilov_E/Main/output.html");
        }

        CSV.writeFooter("CSV/src/ru/academits/Danilov_E/Main/output.html");
    }
}