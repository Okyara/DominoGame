/*Name of program:   Domino Game
  Programmer's name: Oksana Yaremchuk
  Current date:      03/20/2008
  Computer system:   Windows XP
  Environment:        Eclipse3.3.1
*/
import java.util.ArrayList;

public class DominoPlayer 
{
	private String name;
	private int score;
	private ArrayList<DominoTile>array = new ArrayList<DominoTile>(14);

	public DominoPlayer(String n)
	{
		if(n != null)
		{
                    name = n;
		}else{
                    name= "Player";
                }
	}
	
	public DominoPlayer()
	{
		name= "Player";
	}
	
	public String getName() {return name;}
	
	public DominoTile getArrayElement(int index){return array.get(index);}
	
	public int getScore() {return score;}

	public DominoTile removeTile(int index)
	{
		if(index >= 0 && index<array.size())
		{
                    DominoTile tempDominoTile = getArrayElement(index);
                    array.remove(index);
		
                    return tempDominoTile;
		}else{
                    return null;
                }
	}

	public int tileCount()
	{
		return  array.size();
	}//tileCount
	
	public boolean dealTile(DominoTile tile)
	{
		if(tile != null)
                {
		    array.add(tile);
		    return true;
	        }else{
                    return false;
		}
	}//dealTile
	
	public int findTile(int value)
	{
		for(int i = 0; i < array.size(); i++)
		{
			DominoTile tile = getArrayElement(i);
			
			if( value == tile.getLeftVal() || value==tile.getRightVal())
                            return i;
		}

                return -1;
	}//findTile
	
	public void clearHand(){array.clear();}
	public void clearScore(){score = 0;}
	public void addToScore(int addend){score +=  addend;}

	public String toString()
	{
		String playersInfo = name +"'s info: ";
			
		for(int i = 0; i < array.size(); i++)
		{
			DominoTile tile = getArrayElement(i);
			playersInfo += " index " + i+ "=" +tile.toString() + ",";
		}

                playersInfo += " score = " +score;

                return playersInfo;
	}//toString
	
} //end DominoPlayer

