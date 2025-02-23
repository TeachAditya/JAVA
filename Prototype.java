import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator {
    private JFrame frame;
    private JTextField display;
    private double memory = 0;

    public ScientificCalculator() {
        frame = new JFrame("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/", "sqrt",
                "4", "5", "6", "*", "x^2",
                "1", "2", "3", "-", "x^3",
                "0", ".", "=", "+", "1/x",
                "C", "MC", "MR", "MS", "M+",
                "sin", "cos", "tan", "log", "ln",
                "sin⁻¹", "cos⁻¹", "tan⁻¹", "e^x", "π",
                "n!", "|x|", "+/-", "xʸ", "10^x"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = ((JButton) e.getSource()).getText();

            try {
                switch (command) {
                    case "C":
                        display.setText("");
                        break;
                    case "MC":
                        memory = 0;
                        break;
                    case "MR":
                        display.setText(String.valueOf(memory));
                        break;
                    case "MS":
                        memory = Double.parseDouble(display.getText());
                        break;
                    case "M+":
                        memory += Double.parseDouble(display.getText());
                        break;
                    case "sqrt":
                        display.setText(String.valueOf(Math.sqrt(Double.parseDouble(display.getText()))));
                        break;
                    case "x^2":
                        display.setText(String.valueOf(Math.pow(Double.parseDouble(display.getText()), 2)));
                        break;
                    case "x^3":
                        display.setText(String.valueOf(Math.pow(Double.parseDouble(display.getText()), 3)));
                        break;
                    case "1/x":
                        double num = Double.parseDouble(display.getText());
                        display.setText(num != 0 ? String.valueOf(1 / num) : "Error");
                        break;
                    case "sin":
                        display.setText(String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(display.getText())))));
                        break;
                    case "cos":
                        display.setText(String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(display.getText())))));
                        break;
                    case "tan":
                        display.setText(String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(display.getText())))));
                        break;
                    case "log":
                        display.setText(String.valueOf(Math.log10(Double.parseDouble(display.getText()))));
                        break;
                    case "ln":
                        display.setText(String.valueOf(Math.log(Double.parseDouble(display.getText()))));
                        break;
                    case "e^x":
                        display.setText(String.valueOf(Math.exp(Double.parseDouble(display.getText()))));
                        break;
                    case "π":
                        display.setText(String.valueOf(Math.PI));
                        break;
                    case "n!":
                        display.setText(String.valueOf(factorial((int) Double.parseDouble(display.getText()))));
                        break;
                    case "|x|":
                        display.setText(String.valueOf(Math.abs(Double.parseDouble(display.getText()))));
                        break;
                    case "+/-":
                        display.setText(String.valueOf(Double.parseDouble(display.getText()) * -1));
                        break;
                    case "xʸ":
                        display.setText(display.getText() + "^ ");
                        break;
                    case "10^x":
                        display.setText(String.valueOf(Math.pow(10, Double.parseDouble(display.getText()))));
                        break;
                    case "=":
                        display.setText(String.valueOf(evalExpression(display.getText())));
                        break;
                    default:
                        display.setText(display.getText() + command);
                        break;
                }
            } catch (Exception ex) {
                display.setText("Error");
            }
        }
    }

    private int factorial(int n) {
        if (n < 0) return -1;
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    private double evalExpression(String expression) {
        try {
            return Double.parseDouble(expression);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScientificCalculator::new);
    }
}
