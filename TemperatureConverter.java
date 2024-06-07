import javax.swing.*;
import java.awt.*;

public class TemperatureConverter extends JFrame {

    private final JTextField temperatureField;
    private final JComboBox<String> fromUnitComboBox;
    private final JComboBox<String> toUnitComboBox;
    private final JButton convertButton;
    private final JLabel resultLabel;

    public TemperatureConverter() {
        super("Temperature Converter");

        // Initialize components
        temperatureField = new JTextField(10);
        fromUnitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        toUnitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        // Set background color
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue color

        // Layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10); // Add padding

        // Add components with constraints
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Enter Temperature:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(temperatureField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("From:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        add(fromUnitComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        add(new JLabel("To:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        add(toUnitComboBox, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        add(convertButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        add(resultLabel, constraints);

        // Action listener for convert button
        convertButton.addActionListener(e -> convertTemperature());

        // Set window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(temperatureField.getText());
            String fromUnit = (String) fromUnitComboBox.getSelectedItem();
            String toUnit = (String) toUnitComboBox.getSelectedItem();

            double convertedValue = convert(temperature, fromUnit, toUnit);
            resultLabel.setText("Result: " + String.format("%.2f %s", convertedValue, toUnit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid temperature value. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double convert(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Celsius":
                if (toUnit.equals("Fahrenheit")) {
                    return (value * 9 / 5) + 32;
                } else if (toUnit.equals("Kelvin")) {
                    return value + 273.15;
                }
                break;
            case "Fahrenheit":
                if (toUnit.equals("Celsius")) {
                    return (value - 32) * 5 / 9;
                } else if (toUnit.equals("Kelvin")) {
                    return (value + 459.67) * 5 / 9;
                }
                break;
            case "Kelvin":
                if (toUnit.equals("Celsius")) {
                    return value - 273.15;
                } else if (toUnit.equals("Fahrenheit")) {
                    return (value * 9 / 5) - 459.67;
                }
                break;
        }
        return value; 
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}
