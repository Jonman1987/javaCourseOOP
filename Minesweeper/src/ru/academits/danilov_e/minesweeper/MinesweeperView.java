package ru.academits.danilov_e.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MinesweeperView {
    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignore) {
            }

            ImageIcon mineIcon = new ImageIcon("Minesweeper/src/ru/academits/danilov_e/minesweeper/resource/minered.png");

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

            JButton[] buttons = new JButton[100];

            for(int i = 0; i < (x*y); i++){
                buttons[i] = new JButton("" + i);
              //  buttons[i].setRolloverEnabled(false);
              //  buttons[i].setFocusPainted(false);
              //  buttons[i].setFocusable(false);
                //buttons[i].setEnabled(false);

              //  buttons[i].setPreferredSize(new Dimension(1, 1));
                panel.add(buttons[i]);



            }


           /* buttons[0].addActionListener(e -> {
                try {
                    ImageIcon mineIcon = new ImageIcon("Minesweeper/src/ru/academits/danilov_e/minesweeper/resource/minered.png");

                  //  buttons[0].setBounds(30, 30, 10, 10);
                    buttons[0].setBorder(BorderFactory.createEmptyBorder());
                    buttons[0].setContentAreaFilled(false);
                    buttons[0].setFocusable(false);
                    buttons[0].setIcon(mineIcon);
                    //Desktop dt = Desktop.getDesktop();
                   // dt.open(new File("Minesweeper/src/ru/academits/danilov_e/minesweeper/resource/minered.png"));
                   // System.out.println("Done.");
                  //  buttons[0].add(buttons[0]);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "ХЗ", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }*/



            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
