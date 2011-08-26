/*Name of program:   Domino Game
  Programmer's name: Oksana Yaremchuk
  Current date:      03/23/2008
  Computer system:   Windows XP
  Environment        Eclipse 3.3.1
  Creates gui for the domino game.
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DominoGui extends JFrame implements ActionListener
{
	
    public static void main(String[] args)
    {
            DominoGui frame = new DominoGui();

            frame.setTitle("Lab7 Domino Game");
            frame.setSize(400, 250);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end main

    private int userChoicePosition = -1;
    private int userChoiceTileIndex = -1;
    private boolean successfullMove = false;

    private DominoGame game;
    private ArrayList <JRadioButton> userHand;

    private JLabel playedTilesLabel;
    private JRadioButton placeLeft;
    private JRadioButton placeRight;
    private ButtonGroup userHandButtonGroup;
    private JButton placeTileButton;
    private JButton endGameButton;
    private JButton startNewGameButton;
    private JLabel YourScoreLabel;

    //Class Constructor.
    public DominoGui()
    {

        game = new DominoGame();
        game.initGame();
        game.doFirstMove();

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.add(playedTilesLabel = new JLabel(game.ToStringPlayedTiles()));

        //create user hand panel.
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());

        userHandButtonGroup = new ButtonGroup();

        //userHand = new JRadioButton[7];
        userHand = new ArrayList<JRadioButton>();

        for (int i= 0; i < 7; i++)
        {
            JRadioButton anotherButton= new JRadioButton();
            userHand.add(anotherButton);
            p2.add(userHand.get(i));
            userHand.get(i).setText(game.player.getArrayElement(i).toString());
            userHandButtonGroup.add(userHand.get(i));
            userHand.get(i).addActionListener(this);

        }//for

        JPanel p3 = new JPanel();

        p3.setLayout(new FlowLayout());

        p3.add(placeLeft = new JRadioButton("Place Left"));
        placeLeft.addActionListener(this);

        p3.add(placeRight = new JRadioButton("Place Right"));
        placeRight.addActionListener(this);

        ButtonGroup leftRightButtonGroup = new ButtonGroup();

        leftRightButtonGroup.add(placeLeft);

        leftRightButtonGroup.add(placeRight);

        p3.add(placeTileButton = new JButton("Place Tile"));
        placeTileButton.addActionListener(this);

        p3.add(endGameButton = new JButton("End Game"));
        endGameButton.addActionListener(this);

        p3.add(startNewGameButton = new JButton("Start New Game"));
        startNewGameButton.addActionListener(this);
        startNewGameButton.setVisible(false);


        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout());
        p4.add(YourScoreLabel = new JLabel("Your score so far = 0"));

        getContentPane().setLayout(new GridLayout(4,1));
        getContentPane().add(p1);         //add component (p1) to the frame.
        getContentPane().add(p2);         //add component (p2) to the frame.
        getContentPane().add(p3);         //add component (p3) to the frame.
        getContentPane().add(p4);         //add component (p4) to the frame.

     }//End constructor for DominoGui
	

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == endGameButton)
        {
            YourScoreLabel.setText(game.ToStringDisplayResults());
            startNewGameButton.setVisible(true);
            endGameButton.setVisible(false);
            placeTileButton.setVisible(false);
        }


        for (int i=0; i< game.player.tileCount(); i++)
        {
            if (e.getSource()==userHand.get(i))
            {
                userChoiceTileIndex=i;
            }
        }

        if(e.getSource()==placeLeft)
        {
            userChoicePosition=1; //1 is left (beg)
        }

        if (e.getSource()==placeRight)
        {
            userChoicePosition=2; //2 is right (end)
        }


        if (e.getSource()==placeTileButton)
        {

                if (userChoicePosition==1)
                {
                    successfullMove = game.doBegMove(game.player.getArrayElement(userChoiceTileIndex));
                }else{

                    successfullMove = game.doEndMove(game.player.getArrayElement(userChoiceTileIndex));
                }

                if(successfullMove)
                {
                    game.player.removeTile(userChoiceTileIndex);

                    DominoTile firstTile = game.playedTiles.get(0);
                    DominoTile lastTile = game.playedTiles.get(game.playedTiles.size()-1);

                    game.player.addToScore(firstTile.getLeftVal() + lastTile.getRightVal());

                    userHand.get(userChoiceTileIndex).setVisible(false);
                    userHand.remove(userChoiceTileIndex);

                    if (game.playComputer())
                    {
                          playedTilesLabel.setText(game.ToStringPlayedTiles());
                          YourScoreLabel.setText("Computer placed a tile. Your score so far=" + game.player.getScore());
                    }else{
                          YourScoreLabel.setText(game.ToStringDisplayResults());
                          startNewGameButton.setVisible(true);
                          endGameButton.setVisible(false);
                          placeTileButton.setVisible(false);
                    }

                } //If successfull move.

        } //if click tile button.

        if (e.getSource()==startNewGameButton)
        {
                dispose();
                DominoGui frame = new DominoGui();

                frame.setTitle("Lab7 Domino Game");
                frame.setSize(400, 250);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } //if startNewGameButton.

    }//ActionPerformed
        
}//DominoGui