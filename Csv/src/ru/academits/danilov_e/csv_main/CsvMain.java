package ru.academits.danilov_e.csv_main;

import ru.academits.danilov_e.csv.Csv;

public class CsvMain {
    private static String getOutputFilename(String inputFileName) {
        StringBuilder stringBuilder = new StringBuilder(inputFileName);

        int dotIndex = stringBuilder.indexOf(".");
        stringBuilder.delete(dotIndex + 1, stringBuilder.length());
        stringBuilder.append("html");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Вы не указали файл для конвертации.");
        } else {
            String path = args[0];
            String outputFileName = getOutputFilename(path);
            Csv.convert(path, outputFileName); // Я попытался поймать IOException в main, IDEA не дает это сделать
        }
    }
}
