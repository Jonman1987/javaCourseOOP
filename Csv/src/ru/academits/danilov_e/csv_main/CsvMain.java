package ru.academits.danilov_e.csv_main;

import ru.academits.danilov_e.csv.ConverterFromCsvToHtml;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CsvMain {
    private static String getOutputFileName(String inputFileName) {
        int lastIndex = inputFileName.lastIndexOf('.');

        return inputFileName.substring(0, lastIndex + 1) + "html";
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Вы не указали файл для конвертации.");

            return;
        }

        if (args.length > 2) {
            System.out.println("Вы указали большое количество файлов. Укажите имя одного файла в " +
                    "формате *.csv или двух файлов, где имя второго файла в формате *.html это файл результата конвертации.");

            return;
        }

        String inputFileName = args[0];
        String outputFileName;

        if (args.length == 2) {
            inputFileName = args[0];
            outputFileName = args[1];

            if (!outputFileName.substring(outputFileName.lastIndexOf('.') + 1).equalsIgnoreCase("html")) {
                System.out.println("Вы указали выходной файл не в формате *.html.");
            }
        } else {
            outputFileName = getOutputFileName(inputFileName);
        }

        try {
            ConverterFromCsvToHtml.convert(inputFileName, outputFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + inputFileName + " не найден");
        } catch (IOException e) {
            System.out.printf(e.toString());
        }
    }
}
