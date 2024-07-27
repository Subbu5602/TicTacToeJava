import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    TicTacToe(){
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(800,800);
       frame.getContentPane().setBackground(new Color(50,50,50));
       frame.setLayout(new BorderLayout());
       frame.setVisible(true);

       textfield.setBackground(new Color(25,25,25));
       textfield.setForeground(new Color(25,255,0));
       textfield.setFont(new Font("Ink Free",Font.BOLD,75));
       textfield.setHorizontalAlignment(JLabel.CENTER);
       textfield.setText("Tic - Tac - Toe");
       textfield.setOpaque(true);

       panel.setLayout(new BorderLayout());
       panel.setBounds(0,0,800,100);

       button_panel.setLayout(new GridLayout(3,3));
       button_panel.setBackground(new Color(25,25,25));

       for (int i=0;i<9;i++){
           buttons[i] = new JButton();
           button_panel.add(buttons[i]);
           buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
           buttons[i].setFocusable(false);
           buttons[i].addActionListener(this);
       }

       panel.add(textfield);
       frame.add(panel,BorderLayout.NORTH);
       frame.add(button_panel);

       firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++){
            if(e.getSource()==buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O Turn");
                        check();
                    }
                }
                else {
                    if(buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X Turn");
                        check();
                    }
                }

            }
        }
    }

    public void firstTurn(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(random.nextInt(2)==0){
            player1_turn = true;
            textfield.setText("X Turn");
        }
        else{
            player1_turn = false;
            textfield.setText("O Turn");
        }
    }

    public void check(){

        // X Win Conditions

        if((buttons[0].getText()=="X") && (buttons[1].getText()=="X") && (buttons[2].getText()=="X")){ // Top Row
            xWins(0,1,2);
        }
        
        if((buttons[3].getText()=="X") && (buttons[4].getText()=="X") && (buttons[5].getText()=="X")){ // Middle Row
            xWins(3,4,5);
        }
        
        if((buttons[6].getText()=="X") && (buttons[7].getText()=="X") && (buttons[8].getText()=="X")){ // Bottom Row
            xWins(6,7,8);
        }

        if((buttons[0].getText()=="X") && (buttons[3].getText()=="X") && (buttons[6].getText()=="X")){ // Left Column
            xWins(0,3,6);
        }

        if((buttons[1].getText()=="X") && (buttons[4].getText()=="X") && (buttons[7].getText()=="X")){ // Middle Column
            xWins(1,4,7);
        }

        if((buttons[2].getText()=="X") && (buttons[5].getText()=="X") && (buttons[8].getText()=="X")){ // Right Column
            xWins(2,5,8);
        }

        if((buttons[0].getText()=="X") && (buttons[4].getText()=="X") && (buttons[8].getText()=="X")){ // Left to Right Diagonal
            xWins(0,4,8);
        }

        if((buttons[2].getText()=="X") && (buttons[4].getText()=="X") && (buttons[6].getText()=="X")){ // Right to Left Diagonal
            xWins(2,4,6);
        }

        // O Win Conditions

        if((buttons[0].getText()=="O") && (buttons[1].getText()=="O") && (buttons[2].getText()=="O")){ // Top Row
            oWins(0,1,2);
        }

        if((buttons[3].getText()=="O") && (buttons[4].getText()=="O") && (buttons[5].getText()=="O")){ // Middle Row
            oWins(3,4,5);
        }

        if((buttons[6].getText()=="O") && (buttons[7].getText()=="O") && (buttons[8].getText()=="O")){ // Bottom Row
            oWins(6,7,8);
        }

        if((buttons[0].getText()=="O") && (buttons[3].getText()=="O") && (buttons[6].getText()=="O")){ // Left Column
            oWins(0,3,6);
        }

        if((buttons[1].getText()=="O") && (buttons[4].getText()=="O") && (buttons[7].getText()=="O")){ // Middle Column
            oWins(1,4,7);
        }

        if((buttons[2].getText()=="O") && (buttons[5].getText()=="O") && (buttons[8].getText()=="O")){ // Right Column
            oWins(2,5,8);
        }

        if((buttons[0].getText()=="O") && (buttons[4].getText()=="O") && (buttons[8].getText()=="O")){ // Left to Right Diagonal
            oWins(0,4,8);
        }

        if((buttons[2].getText()=="O") && (buttons[4].getText()=="O") && (buttons[6].getText()=="O")){ // Right to Left Diagonal
            oWins(2,4,6);
        }
        
    }

    public void xWins(int a,int b,int c){
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        //Disabling Buttons after Winning

        for (int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }

        textfield.setText("X Wins !!");
    }


    public void oWins(int a,int b,int c){
        buttons[a].setBackground(Color.green);
        buttons[b].setBackground(Color.green);
        buttons[c].setBackground(Color.green);

        //Disabling Buttons after Winning

        for (int i=0;i<9;i++){
            buttons[i].setEnabled(false);
        }

        textfield.setText("O Wins !!");
    }
}
