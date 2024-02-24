package ru.academits.danilov_e.csv;

import java.io.*;

public class Csv {
    public static void convert(String inputPath, String outputPath) {

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            try (FileWriter fileWriter = new FileWriter(outputPath, true)) {
                createFile(fileWriter);
                header(fileWriter);
                write(fileWriter, reader);
                footer(fileWriter);
            }
        } catch (IOException e) { // Как я понимаю, так как я перенес BufferedRider/FileWriter в методы класса,
            // то теперь я могу ловить IOException в методах конвертера, а не в main (Пункт 9 замечаний). Иначе IDEA ругается
            // на отсутствие IOException когда я пытаюсь поймать FileNotFound Exception
            System.out.println("Ошибка открытия файла");
        }
    }

    private static void createFile(FileWriter fileWriter) { // Указал static т.к. не придумал как по другому обратиться к методу не имея объекта класса
        try {
            fileWriter.write("");
        } catch (IOException e) { // См. комментарий выше
            System.out.println("Ошибка создания файла Csv");
        }
    }

    private static void header(FileWriter fileWriter) {
        try {
            fileWriter.write("<!DOCTYPE html>\n");
            fileWriter.write("<html lang=\"en\">\n");
            fileWriter.write("\t<head>\n");
            fileWriter.write("\t\t<meta charset=\"utf-8\">\n");
            fileWriter.write("\t\t<title>Задача CSV</title>\n");
            fileWriter.write("</head>\n");
            fileWriter.write("\n");
            fileWriter.write("<body>\n");
            fileWriter.write("\t<table border = \"1\">\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи header файла");
        }
    }

    private static void write(FileWriter fileWriter, BufferedReader reader) {
        try {
            String line;
            boolean isTableStringOpen = false;
            boolean isTableCellOpen = false;
            boolean isQuotesOpen = false;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);

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
        } catch (IOException e) {
            System.out.println("Ошибка записи данный в файл");
        }
    }

    private static void footer(FileWriter fileWriter) {
        try {
            fileWriter.write("\t</table>\n");
            fileWriter.write("</body>\n");
            fileWriter.write("</html>");
        } catch (IOException e) {
            System.out.println("Ошибка записи footer файла");
        }
    }
}
