package ru.academits.danilov_e.temperatureview;

import ru.academits.danilov_e.temperaturecontroller.TemperatureController;

import javax.swing.*;

public class TemperatureDesktopView implements TemperatureView{
    private TemperatureController temperatureController;
    private JLabel resultLabel;
    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch (Exception ignore){

            }

            JFrame frame = new JFrame("Конвертер температур");

            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();

            JLabel celsiusTemperatureLabel = new JLabel("Введите значение температуры");

            JTextField celsiusTemperatureField = new JTextField(30);

            String[] temperaturesType = {"Градусы Цельсия", "Градусы Кельвина", "Градусы Фаренгейта"};

            JComboBox<String> comboBox1 = new JComboBox<>(temperaturesType);
            JComboBox<String> comboBox2 = new JComboBox<>(temperaturesType);

            JButton convertTemperatureButton = new JButton("Конвертировать");
            convertTemperatureButton.addActionListener(e -> {
                try {
                    double inputTemperature = Double.parseDouble(celsiusTemperatureField.getText());
                    int temperatureTypeFrom = comboBox1.getSelectedIndex();
                    int temperatureTypeTo = comboBox2.getSelectedIndex();

                    temperatureController.convertTemperature(inputTemperature, temperatureTypeFrom, temperatureTypeTo);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "Температура должна быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE );
                }

            });

            resultLabel = new JLabel();


            panel.add(celsiusTemperatureLabel);
            panel.add(celsiusTemperatureField);
            panel.add(comboBox1);
            panel.add(comboBox2);
            panel.add(convertTemperatureButton);
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
    public void showKelvinTemperature(double kelvinTemperature) {
        resultLabel.setText("Температура в градусах Кельвинах: " + kelvinTemperature);
        System.out.println();
    }
}
