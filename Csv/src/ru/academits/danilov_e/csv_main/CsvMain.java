package ru.academits.danilov_e.csv_main;

import ru.academits.danilov_e.csv.ConverterFromCsvToHtml;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CsvMain {
    private static String getOutputFileName(String inputFileName) {

        StringBuilder stringBuilder = new StringBuilder(inputFileName);

        int dotIndex = stringBuilder.indexOf(".");
        stringBuilder.delete(dotIndex + 1, stringBuilder.length());
        stringBuilder.append("html");

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Вы не указали файл для конвертации.");
        } else {
            String fileName = args[0];
            String outputFileName = getOutputFileName(fileName);
            try {
                ConverterFromCsvToHtml.convert(fileName, outputFileName);
            } catch (FileNotFoundException e){
                throw new FileNotFoundException("Файл не найден");
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
    }
}
