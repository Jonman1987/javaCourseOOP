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
            frame.setMinimumSize(new Dimension(600, 400));

            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            int x = 9;
            int y = 9;

            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            GridLayout layout = new GridLayout(x, y, 0, 0);
            panel.setLayout(layout);

            JButton[] dsfsd = new JButton[100];

            for(int i = 0; i < (x*y); i++){
                dsfsd[i] = new JButton();
                dsfsd[i].setRolloverEnabled(false);
                dsfsd[i].setFocusPainted(false);
                dsfsd[i].setFocusable(false);
                dsfsd[i].setEnabled(false);

                dsfsd[i].setPreferredSize(new Dimension(1, 1));
                panel.add(dsfsd[i]);
            }





            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
