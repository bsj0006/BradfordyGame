//represents the playing board/no UI
public class Board
{
  private int[][] tiles;
  //default size
  private final int DEFAULT=4;
  private final int LOWERLIMIT=2;
  private final int UPPERLIMIT=45;
  private final int GOAL=2048;  
  private int score=0;
  private boolean win=false;
//Constructors for board  
  
  //constructor with size
  public Board(int size)
  {
    //if too small or big, default
    if(size<LOWERLIMIT)
      size=DEFAULT;
    if(size>UPPERLIMIT)
      size=UPPERLIMIT;
    tiles= new int[size][size];
    //starter tiles
    this.newTile();
    this.newTile();
  }
  
  
  
  //moves all to right
  public boolean moveRight()
  {
    boolean moved=false;
    for(int r=0;r<tiles.length;r++)
    {
      //if a tile is moved or combined, new tile added
      if(collapseRight(r))
        moved=true;
      if(combineRight(r))
      {
        moved=true;
        collapseRight(r);
      }
    }
    if(moved)
      this.newTile();
    return moved;
  }
  
  //combines tiles in the row moving to right
  public boolean combineRight(int row)
  {
    boolean success=false;
    for(int pos=tiles[0].length-2;pos>=0;pos--)
    {
      if(tiles[row][pos]!=0&&tiles[row][pos]==tiles[row][pos+1])
      {
        score+=2*tiles[row][pos+1];
        tiles[row][pos+1]*=2;/*tile value doubles*/
        tiles[row][pos]=0;
        success=true;
        if(tiles[row][pos+1]==GOAL&&!win)
          win=true;
      }
    }
    return success;
  }
  
  //method shift all tiles in row to right
  public boolean collapseRight(int row)
  {
    boolean success=false;
    //outer loop controls stopping pos of inner
    for(int stopPt=0;stopPt<tiles[0].length-1;stopPt++)
    {
      //checks each pos up to stopPt
      for(int pos=tiles[0].length-2;pos>=stopPt;pos--)
      {
        //can move, move right
        if(tiles[row][pos]!=0&&tiles[row][pos+1]==0)/*Doesnt moves null spots*/
        {
          tiles[row][pos+1]=tiles[row][pos];
          tiles[row][pos]=0;
          success=true;
        }
      }
    }
    return success;
  }
  
  
  //moves all to left
  public boolean moveLeft()
  {
    boolean moved=false;
    for(int r=0;r<tiles.length;r++)
    {
      //if a tile is moved or combined, new tile added
      if(collapseLeft(r))
        moved=true;
      if(combineLeft(r))
      {
        moved=true;
        collapseLeft(r);
      }
    }
    if(moved)
      this.newTile();
    return moved;
  }
  
  public boolean combineLeft(int row)
  {
    boolean success=false;
    for(int pos=1;pos<tiles[0].length;pos++)
    {
      if(tiles[row][pos]!=0&&tiles[row][pos]==tiles[row][pos-1])
      {
        score+=2*tiles[row][pos-1];
        tiles[row][pos-1]*=2;/*tile value doubles*/
        tiles[row][pos]=0;
        success=true;
        if(tiles[row][pos-1]==GOAL&&!win)
          win=true;
      }
    }
    return success;
  }
  
  public boolean collapseLeft(int row)
  {
    boolean success=false;
    //outer loop controls stopping pos of inner
    for(int stopPt=tiles[0].length-1;stopPt>0;stopPt--)
    {
      //checks each pos up to stopPt
      for(int pos=1;pos<=stopPt;pos++)
      {
        //can move, move left
        if(tiles[row][pos]!=0&&tiles[row][pos-1]==0)/*Doesnt moves null spots*/
        {
          tiles[row][pos-1]=tiles[row][pos];
          tiles[row][pos]=0;
          success=true;
        }
      }
    }
    return success;
  }    
  
  //moves all upward
  public boolean moveUp()
  {
    boolean moved=false;
    for(int c=0;c<tiles[0].length;c++)
    {
      //if a tile is moved or combined, new tile added
      if(collapseUp(c))
        moved=true;
      if(combineUp(c))
      {
        moved=true;
        collapseUp(c);
      }
    }
    if(moved)
      this.newTile();
    return moved;
  }
  
  public boolean combineUp(int col)
  {
    boolean success=false;
    for(int pos=1;pos<tiles.length;pos++)
    {
      if(tiles[pos][col]!=0&&tiles[pos][col]==tiles[pos-1][col])
      {
        score+=2*tiles[pos-1][col];
        tiles[pos-1][col]*=2;/*tile value doubles*/
        tiles[pos][col]=0;
        success=true;
        if(tiles[pos-1][col]==GOAL&&!win)
          win=true;
      }
    }
    return success;
  }
  
  public boolean collapseUp(int col)
  {
    boolean success=false;
    //outer loop controls stopping pos of inner
    for(int stopPt=tiles.length-1;stopPt>0;stopPt--)
    {
      //checks each pos up to stopPt
      for(int pos=1;pos<=stopPt;pos++)
      {
        //can move, move Up
        if(tiles[pos][col]!=0&&tiles[pos-1][col]==0)/*Doesnt moves null spots*/
        {
          tiles[pos-1][col]=tiles[pos][col];
          tiles[pos][col]=0;
          success=true;
        }
      }
    }
    return success;
  }   
  
  public boolean moveDown()
  {
    boolean moved=false;
    for(int c=0;c<tiles[0].length;c++)
    {
      //if a tile is moved or combined, new tile added
      if(collapseDown(c))
        moved=true;
      if(combineDown(c))
      {
        moved=true;
        collapseDown(c);
      }
    }
    if(moved)
      this.newTile();
    return moved;
  }
  
  //combines tiles in the col moving to Down
  public boolean combineDown(int col)
  {
    boolean success=false;
    for(int pos=tiles.length-2;pos>=0;pos--)
    {
      if(tiles[pos][col]!=0&&tiles[pos][col]==tiles[pos+1][col])
      {
        score+=2*tiles[pos+1][col];
        tiles[pos+1][col]*=2;/*tile value doubles*/
        tiles[pos][col]=0;
        success=true;
        if(tiles[pos+1][col]==GOAL&&!win)
          win=true;
      }
    }
    return success;
  }
  
  //method shift all tiles in col to left
  public boolean collapseDown(int col)
  {
    boolean success=false;
    //outer loop controls stopping pos of inner
    for(int stopPt=0;stopPt<tiles.length-1;stopPt++)
    {
      //checks each pos up to stopPt
      for(int pos=tiles.length-2;pos>=stopPt;pos--)
      {
        //can move, move Down
        if(tiles[pos][col]!=0&&tiles[pos+1][col]==0)/*Doesnt moves null spots*/
        {
          tiles[pos+1][col]=tiles[pos][col];
          tiles[pos][col]=0;
          success=true;
        }
      }
    }
    return success;
  }
  
//------------------------------------------------\\
//  Methods for Checking Status and Adding Tiles  \\
//------------------------------------------------\\  
 
  //checks for empty spaces
  public boolean hasEmpty()
  {
    for(int row=0;row<tiles.length;row++)
      for(int col=0;col<tiles[0].length;col++)
      if(tiles[row][col]==0)
      return true;
    return false;
  }
  
  //adds a new tile
  public boolean newTile()
  {
    //checks for available space
    if(this.hasEmpty())
    {
      //chooses the valuee for the new number
      int newNum = (int)(Math.random()*10);
      if(newNum<=6)
        newNum=2;
      if(newNum>6&&newNum<9)
        newNum=4;
      if(newNum==9)
        newNum=8;
      //Loops until a valid spot is found
      while(true)
      { 
        int row=(int)(Math.random()*(tiles.length)); 
        int col=(int)(Math.random()*(tiles[0].length));
        if(tiles[row][col]==0)
        { tiles[row][col]=newNum;
          return true;}
      }
    }
    else{return false;}
  }
  
  //returns whether or not goal has been reached
  public boolean hasWon()
  {
    return win;
  }
  
  
  public int getScore()
  {
    return score;
  }
  
  //checks for equal adjacent tiles
  public boolean canMove()
  {
    //checks for empty space first.
    if(hasEmpty())
      return true;
    //if no empty spots are available.
    else
    {
      //for each row except the last
      for(int row=0;row<tiles.length-1;row++)
      {
        //for each tile in a row
        for(int col=0;col<tiles[0].length;col++)
        {
          //checks if the tile below is equal
          if(tiles[row][col]==tiles[row+1][col])
            return true;
        }
      }
      //for each row
      for(int row=0;row<tiles.length;row++)
      {
        //for each tile except the last column
        for(int col=0;col<tiles[0].length-1;col++)
        {
          //checks to see if there is an equal tile to the right
          if(tiles[row][col]==tiles[row][col+1])
            return true;
        }
      }
      //no possible moves
      return false;
    }
  }
  //prints the board and the current score
  public String toString()
  {
    for(int row=0;row<tiles.length;row++)
    {
      for(int col=0;col<tiles[0].length;col++)
        System.out.print(tiles[row][col]+" ");
      System.out.println();
    }
    return "\nScore:"+score;
  }
  //returns the values on the board
  public int[][] getValues()
  {
    return tiles;
  }
  
}  
  //==================================\\
  //  OBSOLETE METHODS FOR REFERENCE  \\
  //==================================\\
  /**

  //moves specific row to left
  public int shiftLeft(int row)
  {
    int subTotal=0;
    //outer controls starting pos of inner
    for(int starter=1;starter<tiles[0].length;starter++)
    {
      //checks spots after starter
      for(int pos=starter;pos<tiles[0].length;pos++)
      {
        if(tiles[row][pos]!=0)
        {
          //combines same tiles
          if(tiles[row][pos-1]==tiles[row][pos])
          {
            subTotal+=tiles[row][pos-1];
            tiles[row][pos-1]*=2;
            tiles[row][pos]=0;
          }
          //moves tile if next is empty
          if(tiles[row][pos-1]==0)
          {
            tiles[row][pos-1]=tiles[row][pos];
            tiles[row][pos]=0;
          }
        }
      }
    }
    return subTotal;
  }
  
  public int shiftUp(int col)
  {
    int subTotal=0;
    for(int starter=1;starter<tiles.length;starter++)
    {
      for(int pos=1;pos<tiles.length;pos++)
      {
        if(tiles[pos][col]!=0)
        {
          if(tiles[pos-1][col]==tiles[pos][col])
          {
            subTotal+=tiles[pos-1][col];
            tiles[pos-1][col]*=2;
            tiles[pos][col]=0;
          }
          if(tiles[pos-1][col]==0)
          {
            tiles[pos-1][col]=tiles[pos][col];
            tiles[pos][col]=0;
          }
        }
      }
    }
    return subTotal;
  }
  
  public int shiftDown(int col)
  {
    int subTotal=0;
    for(int starter=tiles.length-2;starter>=0;starter--)
    {
      for(int pos=starter;pos>=0;pos--)
      {
        if(tiles[pos][col]!=0)
        {
          if(tiles[pos+1][col]==tiles[pos][col])
          {
            subTotal+=tiles[pos+1][col];
            tiles[pos+1][col]*=2;
            tiles[pos][col]=0;
          }
          if(tiles[pos+1][col]==0)
          {
            tiles[pos+1][col]=tiles[pos][col];
            tiles[pos][col]=0;
          }
        }
      }
    }
    return subTotal;
  }
**/



