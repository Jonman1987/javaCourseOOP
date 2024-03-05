package ru.academits.danilov_e.csv_main;

import ru.academits.danilov_e.csv.ConverterFromCsvToHtml;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CsvMain {
    private static String getOutputFileName(String inputFileName) {
        int lastIndex = inputFileName.lastIndexOf('.');
        inputFileName = inputFileName.substring(0, lastIndex + 1) + "html";

        return inputFileName;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Вы не указали файл для конвертации.");
        } else if (args.length > 2) {
            throw new IllegalArgumentException("Вы указали большое количество файлов. Укажите имя одного файла в " +
                    "формате *.csv или двух файлов, где имя второго файла в формате *.html это файл результата конвертации.");
        } else {
            String fileName = args[0];
            String outputFileName;

            if (!fileName.substring(fileName.lastIndexOf('.') + 1).equalsIgnoreCase("csv")) {
                throw new IllegalArgumentException("Вы указали файл не в формате *.csv.");
            }

            if (args.length == 2) {
                fileName = args[0];
                outputFileName = args[1];

                if (!outputFileName.substring(outputFileName.lastIndexOf('.') + 1).equalsIgnoreCase("html")) {
                    throw new IllegalArgumentException("Вы указали выходной файл не в формате *.html.");
                }
            } else {
                outputFileName = getOutputFileName(fileName);
            }

            try {
                ConverterFromCsvToHtml.convert(fileName, outputFileName);
            } catch (FileNotFoundException e) {
                System.out.println("Файл " + fileName + " не найден");
            } catch (IOException e) {
                String error = e.toString();
                System.out.println(error);
            }
        }
    }
}
