import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {

    private JButton[] buttons = new JButton[9];
    private boolean isXTurn = true;
    private int moves = 0; // To keep track of total moves

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3, 5, 5)); // Added gaps between grid elements

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    if (clickedButton.getText().equals("")) {
                        moves++; // Increment total moves
                        if (isXTurn) {
                            clickedButton.setText("X");
                        } else {
                            clickedButton.setText("O");
                        }
                        isXTurn = !isXTurn;
                        checkWin();
                    }
                }
            });
            add(buttons[i]);
        }

        setVisible(true);
    }

    private void checkWin() {
        for (int i = 0; i < 8; i++) {
            String line = null;
            switch (i) {
                case 0:
                    line = buttons[0].getText() + buttons[1].getText() + buttons[2].getText();
                    break;
                case 1:
                    line = buttons[3].getText() + buttons[4].getText() + buttons[5].getText();
                    break;
                case 2:
                    line = buttons[6].getText() + buttons[7].getText() + buttons[8].getText();
                    break;
                case 3:
                    line = buttons[0].getText() + buttons[3].getText() + buttons[6].getText();
                    break;
                case 4:
                    line = buttons[1].getText() + buttons[4].getText() + buttons[7].getText();
                    break;
                case 5:
                    line = buttons[2].getText() + buttons[5].getText() + buttons[8].getText();
                    break;
                case 6:
                    line = buttons[0].getText() + buttons[4].getText() + buttons[8].getText();
                    break;
                case 7:
                    line = buttons[2].getText() + buttons[4].getText() + buttons[6].getText();
                    break;
            }
            if (line.equals("XXX") || line.equals("OOO")) {
                JOptionPane.showMessageDialog(this, line.charAt(0) + " wins!");
                int reply = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    resetGame();
                    return; // Return after a win to prevent checking for draw
                } else {
                    System.exit(0); // Close the game if the answer is "No"
                }
            }
        }
        if (moves == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            int reply = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Tic Tac Toe", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0); // Close the game if the answer is "No"
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
        }
        isXTurn = true;
        moves = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}

