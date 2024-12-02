//start a little slow for a bug-free experience, because thread will sleep for 400 ms before starting
// Import necessary packages for GUI components and event handling
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    // Random object to determine the first player's turn
    Random random = new Random();

    // Main frame for the game window
    JFrame frame = new JFrame();

    // Panel for holding the title label at the top of the frame
    JPanel panel = new JPanel();

    // Panel for holding the Tic-Tac-Toe buttons
    JPanel button_panel = new JPanel();

    // Label to display game status messages (e.g., "X Turn", "O Wins")
    JLabel textfield = new JLabel();

    // Array of buttons representing the 9 cells of the Tic-Tac-Toe board
    JButton[] buttons = new JButton[9];

    // Boolean to track whose turn it is (true for player 1 / "X", false for player 2 / "O")
    boolean player1_turn;

    // Constructor to set up the game board
    TicTacToe() {
        // Setting up the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50)); // get container holding the components, make it have a Dark background color
        frame.setLayout(new BorderLayout()); // Use BorderLayout for the frame
        frame.setVisible(true);

        // Setting up the textfield (label) that displays game status(turns, who won etc.)
        textfield.setBackground(new Color(25, 25, 25)); // Dark background for the label
        textfield.setForeground(new Color(25, 255, 0)); // Bright green text color
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75)); // Large font size
        textfield.setHorizontalAlignment(JLabel.CENTER); // Center-align the text
        textfield.setText("Tic - Tac - Toe"); // Initial text display
        textfield.setOpaque(true); // Make the label opaque to display the background color

        // Panel that holds the textfield at the top of the frame
        panel.setLayout(new BorderLayout());
        panel.setBounds(0, 0, 800, 100); // Set bounds for the panel

        // Panel for the Tic-Tac-Toe grid layout
        button_panel.setLayout(new GridLayout(3, 3)); // Create a 3x3 grid layout for buttons
        button_panel.setBackground(new Color(25, 25, 25)); // Dark background for the grid

        // Create and add buttons to the grid
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120)); // Large font for button labels
            buttons[i].setFocusable(false); // Remove focus outline for buttons
            buttons[i].addActionListener(this); // Add action listener for button click events
        }

        // Add the textfield to the top panel and add panels to the frame
        panel.add(textfield); //add textfield to panel
        frame.add(panel, BorderLayout.NORTH); //add panel to frame, in its north direction
        frame.add(button_panel); //add buttons to frame

        // Decide who goes first
        firstTurn();
    }

    // Override the actionPerformed method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Loop through all buttons to check which one was clicked
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) { // Check if the button was clicked
                if (player1_turn) { // Check if it's player 1's turn (X)
                    if (buttons[i].getText().equals("")) { // Ensure the button is not already clicked
                        buttons[i].setForeground(new Color(255, 0, 0)); // X will be in red color
                        buttons[i].setText("X"); // Mark the button with X
                        player1_turn = false; // Change turn to player 2
                        textfield.setText("O Turn"); // Update the status message
                        check(); // Check if the game has been won
                    }
                } else { // Else it's player 2's turn (O)
                    if (buttons[i].getText().equals("")) { // Ensure the button is not already clicked
                        buttons[i].setForeground(new Color(0, 0, 255)); // O will be in blue color
                        buttons[i].setText("O"); // Mark the button with O
                        player1_turn = true; // Change turn to player 1
                        textfield.setText("X Turn"); // Update the status message
                        check(); // Check if the game has been won
                    }
                }
            }
        }
    }

    // Method to decide the first turn (randomly chooses X or O)
    public void firstTurn() {
        try {
            Thread.sleep(400); // Pause for 2 seconds before starting
        } catch (InterruptedException e) {
            throw new RuntimeException(e); // Handle exception if the sleep is interrupted
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true; // Player 1 (X) starts
            textfield.setText("X Turn");
        } else {
            player1_turn = false; // Player 2 (O) starts
            textfield.setText("O Turn");
        }
    }

    // Method to check if there is a winner
    public void check() {
        // Check all possible win conditions for X
        //call xWins and pass to it the winning combo
        if ((buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X"))) {
            xWins(0, 1, 2); // Top Row
        }
        if ((buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X"))) {
            xWins(3, 4, 5); // Middle Row
        }
        if ((buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X"))) {
            xWins(6, 7, 8); // Bottom Row
        }
        if ((buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X"))) {
            xWins(0, 3, 6); // Left Column
        }
        if ((buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X"))) {
            xWins(1, 4, 7); // Middle Column
        }
        if ((buttons[2].getText().equals("X") && buttons[5].getText().equals("X") && buttons[8].getText().equals("X"))) {
            xWins(2, 5, 8); // Right Column
        }
        if ((buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X"))) {
            xWins(0, 4, 8); // Left to Right Diagonal
        }
        if ((buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X"))) {
            xWins(2, 4, 6); // Right to Left Diagonal
        }

        // Check all possible win conditions for O
        //if O wins, call oWins and pass the winning combo
        if ((buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O"))) {
            oWins(0, 1, 2); // Top Row
        }
        if ((buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O"))) {
            oWins(3, 4, 5); // Middle Row
        }
        if ((buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O"))) {
            oWins(6, 7, 8); // Bottom Row
        }
        if ((buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O"))) {
            oWins(0, 3, 6); // Left Column
        }
        if ((buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O"))) {
            oWins(1, 4, 7); // Middle Column
        }
        if ((buttons[2].getText().equals("O") && buttons[5].getText().equals("O") && buttons[8].getText().equals("O"))) {
            oWins(2, 5, 8); // Right Column
        }
        if ((buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O"))) {
            oWins(0, 4, 8); // Left to Right Diagonal
        }
        if ((buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O"))) {
            oWins(2, 4, 6); // Right to Left Diagonal
        }
    }

    // Method to display that X has won the game and disable further moves
    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green); // Change winning buttons to green
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        // Disable all buttons after the game is won
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textfield.setText("X Wins !!"); // Display the winning message
    }

    // Method to display that O has won the game and disable further moves
    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.green); // Change winning buttons to green
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        // Disable all buttons after the game is won
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textfield.setText("O Wins !!"); // Display the winning message
    }
}
