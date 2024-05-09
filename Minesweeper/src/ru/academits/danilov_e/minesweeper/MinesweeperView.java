package ru.academits.danilov_e.minesweeper;

import javax.swing.*;
import java.awt.*;

public class MinesweeperView {
    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignore) {
            }

            JFrame frame = new JFrame("Сапер");
            frame.setMinimumSize(new Dimension(600, 200));

            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            int x = 10;
            int y = 10;

            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            GridLayout layout = new GridLayout(x, y, 0, 0);
            panel.setLayout(layout);

            JButton[] dsfsd = new JButton[10];

            for(int i = 0; i < (x*y); i++){
                dsfsd[i] = new JButton("" + (i + 1));
                dsfsd[i].setPreferredSize(new Dimension(1, 1));
                panel.add(dsfsd[i]);
            }





            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
