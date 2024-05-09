package ru.academits.danilov_e;

import ru.academits.danilov_e.minesweeper.MinesweeperModel;
import ru.academits.danilov_e.minesweeper.MinesweeperView;

public class MinesweeperMain {
    public static void main(String[] args) {
        /*MinesweeperModel model = new MinesweeperModel(5, 10);

        model.showField();*/
        MinesweeperView view = new MinesweeperView();
        view.run();
    }
}