package ru.academits.danilov_e.temperature.view;

import ru.academits.danilov_e.temperature.controller.ControllerInterface;

import javax.swing.*;
import java.awt.*;

public class DesktopView implements ViewInterface {
    private ControllerInterface controller;
    private JLabel resultLabel;

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignore) {
            }

            JFrame frame = new JFrame("Конвертер температур");

            frame.setMinimumSize(new Dimension(600, 200));

            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JTextField temperatureInputField = new JTextField(10);

            String[] temperaturesType = controller.getTemperaturesTypes();

            JComboBox<String> comboBox1 = new JComboBox<>(temperaturesType);
            JComboBox<String> comboBox2 = new JComboBox<>(temperaturesType);

            JButton convertTemperatureButton = new JButton("Конвертировать");
            convertTemperatureButton.addActionListener(e -> {
                try {
                    double inputTemperature = Double.parseDouble(temperatureInputField.getText());
                    int temperatureTypeFrom = comboBox1.getSelectedIndex();
                    int temperatureTypeTo = comboBox2.getSelectedIndex();

                    controller.convertTemperature(inputTemperature, temperatureTypeFrom, temperatureTypeTo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            });

            JLabel celsiusTemperatureLabel = new JLabel("Введите значение температуры");
            JLabel inputTemperatureText = new JLabel("Выберите шкалу начальной температуры");
            JLabel outputTemperatureText = new JLabel("Выберите шкалу преобразованной температуры");

            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            GridLayout layout = new GridLayout(4, 2, 10, 10);

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
    public void setController(ControllerInterface controller) {
        this.controller = controller;
    }

    @Override
    public void showResultTemperature(double temperature) {
        resultLabel.setText("Температура: " + temperature);
    }

    @Override
    public String[] getTemperaturesTypes(ControllerInterface temperatureController) {
        return new String[0];
    }
}
