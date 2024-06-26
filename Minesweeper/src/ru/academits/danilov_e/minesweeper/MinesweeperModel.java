package ru.academits.danilov_e.minesweeper;

import java.util.Random;

public class MinesweeperModel {
    Minesweeper[][] field;
    int minesCount;

    public MinesweeperModel(int fieldSize, int minesCount) {
        field = new Minesweeper[fieldSize][fieldSize];
        this.minesCount = minesCount;

        int mines = 0;
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                field[i][j] = new Minesweeper();
                field[i][j].setOpened(false);
                field[i][j].setHasMine(false);
                field[i][j].setMineCount(0);

                /*int a = (int) (-49 + (Math.random() * 50) + ((this.minesCount * 100) / (fieldSize * fieldSize)) / 2);
                if (a > 0 && mines < minesCount) {
                    field[i][j].setHasMine(true);
                    mines++;
                } else {
                    field[i][j].setHasMine(false);
                }*/
            }
        }
    }

    public void setMines() {
        for (int i = 0; i < minesCount; i++) {
            Random rand = new Random();
            int row = rand.nextInt(field.length);
            int col = rand.nextInt(field.length);

            while (field[row][col].getHasMine()) {
                row = rand.nextInt(field.length);
                col = rand.nextInt(field.length);
            }

            field[row][col].setHasMine(true);
        }
    }

    public void setMineNumber() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j].setMineCount(calcNear(i, j));
            }
        }
    }

    public void showField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j].getOpened() == false) {
                    System.out.print(" f");
                } else {
                    System.out.print("t");
                }

                if (field[i][j].getHasMine() == false) {
                    System.out.print("-f-");
                } else {
                    System.out.print("*T*");
                }

                System.out.print(field[i][j].getMineCount() + " ");
            }

            System.out.println();
        }
    }

    boolean outBounds(int x, int y) {
        return x < 0 || y < 0 || x >= field.length || y >= field.length;
    }

    int calcNear(int x, int y) {
        if (outBounds(x, y)) {
            return 0;
        }

        int i = 0;

        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                if (outBounds(offsetX + x, offsetY + y)) {
                    continue;
                }

                if (field[offsetX + x][offsetY + y].getHasMine()) {
                    i++;
                }
            }
        }

        return i;
    }
}
