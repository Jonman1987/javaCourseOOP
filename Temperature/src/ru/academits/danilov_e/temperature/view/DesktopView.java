package ru.academits.danilov_e.temperature.view;

import ru.academits.danilov_e.temperature.controller.Controller;

import javax.swing.*;
import java.awt.*;

public class DesktopView implements View {
    private Controller controller;
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

            String[] temperaturesTypes = controller.getTemperaturesTypes();

            JComboBox<String> comboBox1 = new JComboBox<>(temperaturesTypes);
            JComboBox<String> comboBox2 = new JComboBox<>(temperaturesTypes);

            JButton convertTemperatureButton = new JButton("Конвертировать");
            convertTemperatureButton.addActionListener(e -> {
                try {
                    double inputTemperature = Double.parseDouble(temperatureInputField.getText());
                    int indexFrom = comboBox1.getSelectedIndex();
                    int indexTo = comboBox2.getSelectedIndex();

                    controller.convertTemperature(inputTemperature, indexFrom, indexTo);
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
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void showResultTemperature(double temperature) {
        resultLabel.setText("Температура: " + Math.ceil(temperature * 100) / 100);
    }
}