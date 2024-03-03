package ru.academits.danilov_e.csv;

import java.io.*;

public class ConverterFromCsvToHtml {
    public static void convert(String inputFileName, String outputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             FileWriter fileWriter = new FileWriter(outputFileName, false)) {
            printHeader(fileWriter);
            printTable(fileWriter, reader);
            printFooter(fileWriter);
        }
    }

    private static void printHeader(FileWriter fileWriter) throws IOException {
        fileWriter.write("<!DOCTYPE html>\n");
        fileWriter.write("<html lang=\"en\">\n");
        fileWriter.write("\t<head>\n");
        fileWriter.write("\t\t<meta charset=\"utf-8\">\n");
        fileWriter.write("\t\t<title>Задача CSV</title>\n");
        fileWriter.write("</head>\n");
        fileWriter.write("\n");
        fileWriter.write("<body>\n");
        fileWriter.write("\t<table border = \"1\">\n");
    }

    private static void printTable(FileWriter fileWriter, BufferedReader reader) throws IOException {
        String line;
        boolean isTableStringOpen = false;
        boolean isTableCellOpen = false;
        boolean isQuotesOpen = false;

        while ((line = reader.readLine()) != null) {
            if (!isTableStringOpen) {
                fileWriter.write("\t\t<tr>\n");
                isTableStringOpen = true;
            }

            for (int i = 0; i < line.length(); i++) {
                if (!isTableCellOpen) {
                    fileWriter.write("\t\t\t<td>");
                    isTableCellOpen = true;
                }

                if (i != line.length() - 1) {
                    if (line.charAt(i) == '"' && isQuotesOpen && line.charAt(i + 1) == '"') {
                        fileWriter.write('"');
                        i++;

                        continue;
                    } else if (line.charAt(i) == '"' && isQuotesOpen && line.charAt(i + 1) != '"') {
                        isQuotesOpen = false;

                        continue;
                    } else if (line.charAt(i) == '"' && isQuotesOpen) {
                        isQuotesOpen = false;

                        continue;
                    } else if (line.charAt(i) == '"') {
                        isQuotesOpen = true;

                        continue;
                    }
                } else {
                    if (line.charAt(i) == '"') {
                        isQuotesOpen = false;

                        continue;
                    }
                }

                if (line.charAt(i) == ',' && !isQuotesOpen) {
                    fileWriter.write("</td>\n");
                    isTableCellOpen = false;

                    if (i == line.length() - 1) {
                        fileWriter.write("\t\t\t<td>");
                    }

                    continue;
                }

                if (line.charAt(i) == '<') {
                    fileWriter.write("&lt;");

                    continue;
                }

                if (line.charAt(i) == '>') {
                    fileWriter.write("&gt;");

                    continue;
                }

                if (line.charAt(i) == '&') {
                    fileWriter.write("&amp;");

                    continue;
                }

                fileWriter.write(line.charAt(i));
            }

            if (!isQuotesOpen) {
                fileWriter.write("</td>\n");
                isTableCellOpen = false;
                fileWriter.write("\t\t</tr>\n");
                isTableStringOpen = false;
            } else {
                fileWriter.write("<br/>");
            }
        }
    }

    private static void printFooter(FileWriter fileWriter) throws IOException {
        fileWriter.write("\t</table>\n");
        fileWriter.write("</body>\n");
        fileWriter.write("</html>");
    }
}
