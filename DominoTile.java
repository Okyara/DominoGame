/*Name of program:   Domino Game
  Programmer's name: Oksana Yaremchuk
  Current date:      03/20/2008
  Computer system:   Windows XP
  Environment:       Eclipse 3.3.1
  */

public class DominoTile 
{
    public static final  int minValue = 0;
    public static final int maxValue =6;

    //Default value of leftVal and rightVal is 0.
    private int leftVal = minValue;
    private int rightVal = minValue;

    public DominoTile(int left, int right)
    {
            if(left >= minValue && left <= maxValue)
            {
                    leftVal = left;
            }

            if(right >= minValue && right <= maxValue)
            {
                    rightVal = right;
            }
    }//Constructor

    public void flip()
    {
            int temp;
            temp = rightVal;
            rightVal = leftVal;
            leftVal  = temp;
    }//flip

    public int getLeftVal(){return leftVal;}
    public int getRightVal(){return rightVal;}

    public String toString()
    {
            String leftStr;
            String rightStr;

            if(leftVal >= 1  && leftVal <= maxValue)
                leftStr = "[" +leftVal;
            else leftStr = "[ ";

            if(rightVal >= 1  && rightVal <= maxValue)
                 rightStr = rightVal + "]";
            else rightStr = " ]";

            return leftStr + "|"+ rightStr;
    }//toString

}//class DominoTile


