//Original 2048 created by Gabriele Cirulli
//This version was created by Bradley Johnson

import java.util.Scanner;
//Create a new game
public class TileGame
{
  public static void main(String[] args)
  {
    String input="";
    Scanner scan = new Scanner(System.in);
    Board board = new Board(4);
    boolean winner=false;
    System.out.println(board);
    //loops until user wants to exit
    while(!(input.equalsIgnoreCase("exit")))
    {
      //Prints the valid user inputs
      System.out.println("RIGHT|LEFT|UP|DOWN|EXIT");
      input=scan.next();
      //Checks for right input and moves right if possible
      if(input.equalsIgnoreCase("right")||input.equalsIgnoreCase("r"))
      {
        if(board.moveRight())
          System.out.println(board+"\n");
        else
          System.out.println("Invalid Move.");
      }
      //Checks for left input and moves left if valid
      if(input.equalsIgnoreCase("left")||input.equalsIgnoreCase("l"))
      {
        if(board.moveLeft())
          System.out.println(board+"\n");
        else
          System.out.println("Invalid Move.");
      }
      //Checks for up movement and moves up if valid
      if(input.equalsIgnoreCase("up")||input.equalsIgnoreCase("u"))
      {
        if(board.moveUp())
          System.out.println(board+"\n");
        else
          System.out.println("Invalid Move.");
      }
      //Checks for downward move
      if(input.equalsIgnoreCase("down")||input.equalsIgnoreCase("d"))
      {
        if(board.moveDown())
          System.out.println(board+"\n");
        else
          System.out.println("Invalid Move.");
      }
      if((winner==false) && board.hasWon())
      {
        System.out.println("===================================\nYou have won! Type \"exit\" to quit.\n===================================");
        winner=true;
      }
      if(!board.canMove())
      {
        System.out.println("You lost with a score of "+board.getScore()+".");
        input="exit";
      }   
    }
    
  }
}
      
      
  