package ru.academits.danilov_e.csv_main;

import ru.academits.danilov_e.csv.Csv;

public class CsvMain {
    public static void main(String[] args) {
        String path = "Csv/src/ru/academits/danilov_e/csv/input.csv";
        Csv.convert(path); // Я попытался поймать IOException в main, IDEA не дает это сделать
    }
}
