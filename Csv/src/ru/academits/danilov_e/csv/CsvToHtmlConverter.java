package ru.academits.danilov_e.csv;

import java.io.*;

public class CsvToHtmlConverter {
    public static void convert(String inputFileName, String outputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
             PrintWriter writer = new PrintWriter(outputFileName)) {
            printHeader(writer);
            printTable(writer, reader);
            printFooter(writer);
        }
    }

    private static void printHeader(PrintWriter writer) {
        writer.println("<!DOCTYPE html>");
        writer.println("<html lang=\"en\">");
        writer.println("<head>");
        writer.println("\t<meta charset=\"utf-8\">");
        writer.println("\t<title>Задача CSV</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("\t<table border=\"1\">");
    }

    private static void printTable(PrintWriter writer, BufferedReader reader) throws IOException {
        String line;
        boolean isTableRowOpen = false;
        boolean isTableCellOpen = false;
        boolean isQuotesOpen = false;

        while ((line = reader.readLine()) != null) {
            if (!isTableRowOpen) {
                writer.println("\t\t<tr>");
                isTableRowOpen = true;
            }

            for (int i = 0; i < line.length(); i++) {
                if (!isTableCellOpen) {
                    writer.print("\t\t\t<td>");
                    isTableCellOpen = true;
                }

                if (line.charAt(i) == '"') {
                    if (i != line.length() - 1) {
                        if (isQuotesOpen && line.charAt(i + 1) == '"') {
                            writer.print('"');
                            i++;
                            continue;
                        }

                        if (line.charAt(i + 1) != '"') {
                            isQuotesOpen = !isQuotesOpen;
                            continue;
                        }
                    } else {
                        if(i == (line.length() - 1) && line.charAt(i) == '"' && !isQuotesOpen){
                            isQuotesOpen = true;
                            continue;
                        }

                        isQuotesOpen = false;
                        continue;
                    }

                    isQuotesOpen = !isQuotesOpen;
                    continue;
                }

                if (line.charAt(i) == ',' && !isQuotesOpen) {
                    writer.println("</td>");
                    isTableCellOpen = false;

                    if (i == line.length() - 1) {
                        writer.print("\t\t\t<td>");
                    }
                    continue;
                }

                if (line.charAt(i) == '<') {
                    writer.print("&lt;");
                    continue;
                }

                if (line.charAt(i) == '>') {
                    writer.print("&gt;");
                    continue;
                }

                if (line.charAt(i) == '&') {
                    writer.print("&amp;");
                    continue;
                }

                writer.write(line.charAt(i));
            }

            if (!isQuotesOpen) {
                writer.println("</td>");
                isTableCellOpen = false;
                writer.println("\t\t</tr>");
                isTableRowOpen = false;
            } else {
                writer.print("<br/>");
            }
        }
    }

    private static void printFooter(PrintWriter writer) {
        writer.println("\t</table>");
        writer.println("</body>");
        writer.print("</html>");
    }
}
