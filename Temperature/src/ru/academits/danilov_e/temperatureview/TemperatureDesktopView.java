package ru.academits.danilov_e.temperatureview;

import ru.academits.danilov_e.temperaturecontroller.TemperatureController;

import javax.swing.*;
import java.awt.*;

public class TemperatureDesktopView implements TemperatureView {
    private TemperatureController temperatureController;
    private JLabel resultLabel;

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignore) {

            }

            JFrame frame = new JFrame("Конвертер температур");

            frame.setSize(600, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JTextField temperatureInputField = new JTextField(10);

            String[] temperaturesType = {"Градусы Цельсия", "Градусы Кельвина", "Градусы Фаренгейта"};

            JComboBox<String> comboBox1 = new JComboBox<>(temperaturesType);
            JComboBox<String> comboBox2 = new JComboBox<>(temperaturesType);

            JButton convertTemperatureButton = new JButton("Конвертировать");
            convertTemperatureButton.addActionListener(e -> {
                try {
                    double inputTemperature = Double.parseDouble(temperatureInputField.getText());
                    int temperatureTypeFrom = comboBox1.getSelectedIndex();
                    int temperatureTypeTo = comboBox2.getSelectedIndex();

                    temperatureController.convertTemperature(inputTemperature, temperatureTypeFrom, temperatureTypeTo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка"
                            , JOptionPane.ERROR_MESSAGE);
                }

            });

            JLabel celsiusTemperatureLabel = new JLabel("Введите значение температуры");
            JLabel inputTemperatureText = new JLabel("Выберите шкалу начальной температуры");
            JLabel outputTemperatureText = new JLabel("Выберите шкалу преобразованной температуры");

            JPanel panel = new JPanel();

            GridLayout layout = new GridLayout(4,2);

            panel.setLayout(layout);

            panel.add(celsiusTemperatureLabel);
            panel.add(temperatureInputField);
            panel.add(inputTemperatureText);
            panel.add(comboBox1);
            panel.add(outputTemperatureText);
            panel.add(comboBox2);
            panel.add(convertTemperatureButton);
            resultLabel = new JLabel();

            panel.add(resultLabel);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    @Override
    public void setController(TemperatureController temperatureController) {
        this.temperatureController = temperatureController;
    }

    @Override
    public void showTemperatureResult(double temperature) {
        resultLabel.setText("Температура: " + temperature);
        System.out.println();
    }
}
