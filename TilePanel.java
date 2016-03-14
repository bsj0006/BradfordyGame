import java.awt.*;
import javax.swing.*;

public class TilePanel extends JPanel
{
  public final int TILESIZE=30;
  
  
  public void paint(Graphics page)
  {
    int[][] tileVals=((GameWindow)SwingUtilities.getWindowAncestor(this)).getBoardValues();
    //System.out.println("Painting");
    for(int col=0; col<tileVals.length; col++)
    {
      for(int row=0; row<tileVals[0].length; row++)
      {
        if(tileVals[row][col]>9)
        {
          page.setFont(new Font(Font.SERIF,Font.BOLD,20-(int)Math.log10(tileVals[row][col])));
          page.drawString(""+tileVals[row][col],50+30*col-(int)Math.log10(tileVals[row][col])*2,60+30*row);
        }
        else
        {
          page.setFont(new Font(Font.SERIF,Font.BOLD,20));
          page.drawString(""+tileVals[row][col],50+30*col,60+30*row);
        }
      }
    }
  }
  public void loseScreen()
  {
    this.setForeground(new Color(128,128,128,100));
      
  }
} 
