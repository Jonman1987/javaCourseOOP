package ru.academits.danilov_e.minesweeper;

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

                int a = (int) (-49 + (Math.random() * 50) + ((this.minesCount * 100) / (fieldSize * fieldSize)) / 2);
                if (a > 0 && mines < minesCount) {
                    field[i][j].setHasMine(true);
                    mines++;
                } else {
                    field[i][j].setHasMine(false);
                }
            }
        }
    }

    public void setMineNumber() {

    }

    public void showField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j].getOpened() == false) {
                    System.out.print("   f");
                } else {
                    System.out.print("t");
                }

                if (field[i][j].getHasMine() == false) {
                    System.out.print("f");
                } else {
                    System.out.print("*T*");
                }

                System.out.print(field[i][j].getMineCount() + "  ");
            }

            System.out.println();
        }
    }
}
