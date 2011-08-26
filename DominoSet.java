/*Name of program:   Domino Game
  Programmer's name: Oksana Yaremchuk
  Current date:      02/12/2008
  Computer system:   Windows XP
  Environment:       Eclipse 3.3.1
 */
public class DominoSet 
{
	private DominoTile []dominoTileObjects = new DominoTile[28];
	
	int currIndex;
	
	public DominoSet()
	{
            int cnt = 0;
		
            for (int leftVal = DominoTile.minValue; leftVal <= DominoTile.maxValue; leftVal++)
	    {
                for (int rightVal = leftVal; rightVal <= DominoTile.maxValue; rightVal++)
                {
                    dominoTileObjects[cnt++] = new DominoTile(leftVal,rightVal);
                }
            }

            currIndex = 0;
	
        }//Constructor

	public void shuffle()
	{
            for(int i = 0; i < dominoTileObjects.length; i++)
            {
                DominoTile temp=dominoTileObjects[i];
                int index2Swap=(int)(Math.random()*27);
                dominoTileObjects[i] = dominoTileObjects[index2Swap];
                dominoTileObjects[index2Swap]=temp;
                currIndex = 0;
            }
	}//Shuffle
	
	public DominoTile drawTile()
	{
            if(currIndex < dominoTileObjects.length)
                    return dominoTileObjects[currIndex++];
            else return null;
        }
	
	public boolean canDraw()
	{
            return currIndex < dominoTileObjects.length;
	}
}//end DominoSet

