/*Name of program:   Domino Game
  Programmer's name: Oksana Yaremchuk
  Current date:      03/23/2008
  Computer system:   Windows XP
  Environment:       Eclipse 3.3.1
 */

import java.util.ArrayList;
import javax.swing.*;
public class DominoGame 
{
   
    public DominoSet set;

    public ArrayList<DominoTile> playedTiles;

    public JLabel label;

    public DominoPlayer player;

    public DominoPlayer computer;
	
    public DominoGame()
    {
         set = new DominoSet();

         playedTiles = new ArrayList<DominoTile>();

         player = new DominoPlayer("Player");

         computer = new DominoPlayer("Computer");
    }//Constructor

    public void initGame()
    {
        set.shuffle();
        playedTiles.clear();
        player.clearHand();
        player.clearScore();
        computer.clearHand();
        computer.clearScore();
    }//initGame

    public void deal()
    {
        while(true)
         {
             player.dealTile(set.drawTile());
             computer.dealTile(set.drawTile());

             if(player.tileCount() == 7 && computer.tileCount() == 7)
                return;
         }
    }//end deal

    public boolean doBegMove(DominoTile t)
    {
         if(playedTiles.size() == 0)
         {
                 playedTiles.add(t);
                 return true;
         }

         DominoTile firstTile = playedTiles.get(0);

         if(t.getRightVal() == firstTile.getLeftVal())
         {
                 playedTiles.add(0, t);
                 return true;
         }

         if(t.getLeftVal() == firstTile.getLeftVal())
         {
                 t.flip();
                 playedTiles.add(0, t);
                 return true;
         }else{
                 return false;
        }
    }//doBegMove
	 
    public boolean doEndMove(DominoTile t)
    {
         DominoTile lastTile = playedTiles.get(playedTiles.size()-1);
         if(t.getLeftVal() == lastTile.getRightVal())
         {
                 playedTiles.add(t);
                 return true;
         }

         if(t.getRightVal() == lastTile.getRightVal())
         {
                 t.flip();
                 playedTiles.add(t);
                 return true;
         } else{
                 return false;
         }
    }//doEndMove
	
    public boolean playComputer()
    {
          DominoTile firstTile = playedTiles.get(0);

          DominoTile lastTile = playedTiles.get(playedTiles.size()-1);

          int computerChoiceTileIndex = computer.findTile(firstTile.getLeftVal());

         if(computerChoiceTileIndex != -1)
         {
                 DominoTile tileToInsert = computer.getArrayElement(computerChoiceTileIndex);

                 doBegMove(tileToInsert);

                 computer.removeTile(computerChoiceTileIndex);
         }else{
                computerChoiceTileIndex = computer.findTile(lastTile.getRightVal());

                if(computerChoiceTileIndex != -1)
                {
                     DominoTile tileToInsert = computer.getArrayElement(computerChoiceTileIndex);

                    doEndMove(tileToInsert);

                    computer.removeTile(computerChoiceTileIndex);
                }else
                    return false;
         }

         computer.addToScore(playedTiles.get(0).getLeftVal() + playedTiles.get(playedTiles.size()-1).getRightVal());

         return true;

    }//end playPlayer
	 
    public void doFirstMove()
    {
         deal();
         doBegMove(set.drawTile());

    }//end doFirstMove
	
    public String ToStringDisplayResults()
    {
         String ToReturnDisplayResults = new String();

         if(player.getScore() > computer.getScore())
                 ToReturnDisplayResults=ToReturnDisplayResults+"You won!";

         if(computer.getScore() > player.getScore())
                 ToReturnDisplayResults=ToReturnDisplayResults+"Computer won!";

         if(player.getScore() == computer.getScore())
                 ToReturnDisplayResults=ToReturnDisplayResults+"It's a tie!";

         //displayPlayedTiles();
         ToReturnDisplayResults=  ToReturnDisplayResults+"Your final Score = " +player.getScore();
         ToReturnDisplayResults= ToReturnDisplayResults+", Computer's Final Score = " +computer.getScore();
         return ToReturnDisplayResults;

    }//end displayResults


    public String ToStringPlayedTiles()
    {
         String toReturn=new String();

         for(int i = 0; i < playedTiles.size(); i++)
         {
                 DominoTile aTile = playedTiles.get(i);
                 toReturn=toReturn+aTile.toString();
         }
        return toReturn;

    }//end displayPlayedTiles

}//class GameInterface




