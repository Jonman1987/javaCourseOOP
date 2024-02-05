package ru.academits.Danilov_E.CSV;

import java.io.FileWriter;
import java.io.IOException;

public class CSV {
    private String string;
    private boolean isRowEnd;
    private boolean isCellEnd;
    private boolean isStart;

    public CSV() {
        string = "";
        isRowEnd = true;
        isCellEnd = true;
        isStart = true;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void writeRow(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);

            for (int i = 0; i < string.length(); i++) {
                if (i != string.length() - 1) {
                    if (isRowEnd && isCellEnd && isStart) {
                        fileWriter.write("<tr>");
                        fileWriter.write("<td>");
                        isCellEnd = false;
                        isStart = false;
                    } else if (isCellEnd) {
                        fileWriter.write("<td>");
                        isCellEnd = false;
                    }

                    if (string.charAt(i) == ',' && isRowEnd) {
                        fileWriter.write("</td>");
                        isCellEnd = true;
                        isStart = false;
                        continue;
                    }

                    if (string.charAt(i) == '"' && isRowEnd) {
                        isRowEnd = false;
                        continue;
                    }

                    if (string.charAt(i) == '"' && !isRowEnd && string.charAt(i + 1) == '"') {
                        i++;
                        fileWriter.write(string.charAt(i));
                        continue;
                    }

                    if (string.charAt(i) == '"' && !isRowEnd) {
                        isRowEnd = true;
                        continue;
                    }

                    if (string.charAt(i) == '<') {
                        fileWriter.write("&lt");
                        continue;
                    }

                    if (string.charAt(i) == '>') {
                        fileWriter.write("&gt");
                        continue;
                    }

                    if (string.charAt(i) == '&') {
                        fileWriter.write("&amp");
                        continue;
                    }

                    fileWriter.write(string.charAt(i));
                } else {
                    if (!isRowEnd) {
                        if (string.charAt(i) == '"') {
                            fileWriter.write("<br/>");
                        } else {
                            fileWriter.write(string.charAt(i));
                            fileWriter.write("<br/>");
                        }
                    } else if (string.charAt(i) == ',') {
                        fileWriter.write("</td>");
                        isCellEnd = true;
                        isStart = true;
                        fileWriter.write("<td>");
                        fileWriter.write("</td>");
                        fileWriter.write("</tr>");
                    } else {
                        fileWriter.write(string.charAt(i));
                        fileWriter.write("</td>");
                        fileWriter.write("</tr>");
                        isCellEnd = true;
                        isStart = true;
                    }
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeHeader(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write("<html>");
            fileWriter.write("<head>");
            fileWriter.write("<title>Задача CSV</title>");
            fileWriter.write("<meta charset=\"utf-8\">");
            fileWriter.write("</head>");
            fileWriter.write("<body>");
            fileWriter.write("<table border=\"1\">");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFooter(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write("</table>");
            fileWriter.write("</body>");
            fileWriter.write("</html>");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
