package ru.academits.danilov_e.csv;

import java.io.*;

public class ConverterFromCsvToHtml {
    public static void convert(String inputFileName, String outputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             PrintWriter printWriter = new PrintWriter(outputFileName)) {
            printHeader(printWriter);
            printTable(printWriter, reader);
            printFooter(printWriter);
        }
    }

    private static void printHeader(PrintWriter printWriter) {
        printWriter.println("<!DOCTYPE html>");
        printWriter.println("<html lang=\"en\">");
        printWriter.println("<head>");
        printWriter.println("\t<meta charset=\"utf-8\">");
        printWriter.println("\t<title>Задача CSV</title>");
        printWriter.println("</head>");
        printWriter.println("<body>");
        printWriter.println("\t<table border = \"1\">");
    }

    private static void printTable(PrintWriter printWriter, BufferedReader reader) throws IOException {
        String line;
        boolean isTableRowOpen = false;
        boolean isTableCellOpen = false;
        boolean isQuotesOpen = false;

        while ((line = reader.readLine()) != null) {
            if (!isTableRowOpen) {
                printWriter.println("\t\t<tr>");
                isTableRowOpen = true;
            }

            for (int i = 0; i < line.length(); i++) {
                if (!isTableCellOpen) {
                    printWriter.print("\t\t\t<td>");
                    isTableCellOpen = true;
                }

                if (i != line.length() - 1) {
                    if (line.charAt(i) == '"' && isQuotesOpen && line.charAt(i + 1) == '"') {
                        printWriter.print('"');
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
                    printWriter.println("</td>");
                    isTableCellOpen = false;

                    if (i == line.length() - 1) {
                        printWriter.print("\t\t\t<td>");
                    }

                    continue;
                }

                if (line.charAt(i) == '<') {
                    printWriter.print("&lt;");

                    continue;
                }

                if (line.charAt(i) == '>') {
                    printWriter.print("&gt;");

                    continue;
                }

                if (line.charAt(i) == '&') {
                    printWriter.print("&amp;");

                    continue;
                }

                printWriter.write(line.charAt(i));
            }

            if (!isQuotesOpen) {
                printWriter.println("</td>");
                isTableCellOpen = false;
                printWriter.println("\t\t</tr>");
                isTableRowOpen = false;
            } else {
                printWriter.print("<br/>");
            }
        }
    }

    private static void printFooter(PrintWriter printWriter) {
        printWriter.println("\t</table>");
        printWriter.println("</body>");
        printWriter.print("</html>");
    }
}
