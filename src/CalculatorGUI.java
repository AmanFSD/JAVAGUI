import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI {

    private JFrame frame;
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[4];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JPanel panel;

    private double num1=0, num2=0, result=0;
    private char operator;

    public CalculatorGUI() {
        frame = new JFrame("Calculator");
        textField = new JTextField();

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText().concat(e.getActionCommand()));
                }
            });
        }

        for (int i = 0; i < 4; i++) {
            functionButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (!textField.getText().isEmpty()) {
                        num1 = Double.parseDouble(textField.getText());
                        operator = e.getActionCommand().charAt(0);
                        textField.setText("");
                    }
                }
            });
        }

        equButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().isEmpty()) {
                    num2 = Double.parseDouble(textField.getText());
                    switch (operator) {
                        case '+': result = num1 + num2; break;
                        case '-': result = num1 - num2; break;
                        case '*': result = num1 * num2; break;
                        case '/': result = num1 / num2; break;
                    }
                    textField.setText(String.valueOf(result));
                }
            }
        });

        clrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                num1 = num2 = result = 0;
            }
        });

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = textField.getText();
                if (!str.isEmpty()) {
                    textField.setText(str.substring(0, str.length() - 1));
                }
            }
        });

        decButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().contains(".")) {
                    textField.setText(textField.getText().concat("."));
                }
            }
        });

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel);
        frame.add(delButton, BorderLayout.WEST);
        frame.add(clrButton, BorderLayout.EAST);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
