package ru.academits.danilov_e.csv_main;

import ru.academits.danilov_e.csv.CsvToHtmlConverter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CsvMain {
    private static String getOutputFileName(String inputFileName) {
        int lastIndex = inputFileName.lastIndexOf('.');

        if (lastIndex == -1) {
            return inputFileName + ".html";
        }

        if (lastIndex == inputFileName.length() - 1) {
            return inputFileName + "html";
        }

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
            outputFileName = args[1];
        } else {
            outputFileName = getOutputFileName(inputFileName);
        }

        try {
            CsvToHtmlConverter.convert(inputFileName, outputFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Файл " + inputFileName + " не найден");
        } catch (IOException e) {
            System.out.printf(e.toString());
        }
    }
}
