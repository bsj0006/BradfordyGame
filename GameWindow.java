import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.*;

public class GameWindow extends JFrame implements ActionListener, KeyListener
{
  private JLabel score;
  private JMenuBar menu;
  private JMenu moveMenu,helpMenu;
  private JMenuItem help, left, right, up, down;
  private TilePanel tilePanel;
  private JPanel losePanel,winPanel;
  
  public boolean winner;
  public Board board;
  
  public GameWindow()
  {
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setTitle("20 Bradfordy-Eight!");
    winner=false;
    board = new Board(2);
    tilePanel=new TilePanel();
    this.add(tilePanel);
    this.addMenu();
    this.setSize(300,300);
    this.setVisible(true);
    
    losePanel=new JPanel();
    JButton lose =new JButton("You Lost");
    lose.setToolTipText("Accept defeat and restart.");
    lose.setAlignmentX(0.5f);
    lose.addActionListener(this);
    losePanel.add(lose);
    this.setFocusable(true);
    this.addKeyListener(this);
  }
  
  private void addMenu()
  {
    menu=new JMenuBar();
    moveMenu=new JMenu("Move");
    helpMenu=new JMenu("Help");
    help=new JMenuItem("Restart");
    right=new JMenuItem("Move right");
    left=new JMenuItem("Move left");
    up=new JMenuItem("Move up");
    down=new JMenuItem("Move down");
    
    right.addActionListener(this);
    right.setMnemonic('r');
    left.addActionListener(this);
    left.setMnemonic('l');
    up.addActionListener(this);
    up.setMnemonic('u');
    down.addActionListener(this);
    down.setMnemonic('d');
    help.addActionListener(this);
    helpMenu.setMnemonic('h');
    moveMenu.setMnemonic('m');
    
    moveMenu.add(right);
    moveMenu.add(left);
    moveMenu.add(up);
    moveMenu.add(down);
    menu.add(moveMenu);
    helpMenu.add(help);
    menu.add(helpMenu);
    score=new JLabel("    Score: "+board.getScore(),JLabel.CENTER);
    menu.add(score);
    this.setJMenuBar(menu);
  }  
  
  public void keyPressed(KeyEvent e)
  {}
  public void keyTyped(KeyEvent e)
  {}
  public void keyReleased(KeyEvent e)
  {
    if(e.getKeyCode()==KeyEvent.VK_UP)
    {
      if(board.moveUp())
      {
        update();
      }
      else
      {
        up.setEnabled(false);
      }
    } 
    
    if(e.getKeyCode()==KeyEvent.VK_DOWN)
    {
      if(board.moveDown())
      {
        update();
      }
      else
      {
        up.setEnabled(false);
      }
    } 
    
    if(e.getKeyCode()==KeyEvent.VK_LEFT)
    {
      if(board.moveLeft())
      {
        update();
      }
      else
      {
        up.setEnabled(false);
      }
    } 
    
    if(e.getKeyCode()==KeyEvent.VK_RIGHT)
    {
      if(board.moveRight())
      {
        update();
      }
      else
      {
        up.setEnabled(false);
      }
    } 
  }
  
  public void actionPerformed(ActionEvent event)
  {
    if(event.getActionCommand().equals("Move up"))
    {
      if(board.moveUp())
      {
        update();
      }
      else
      {
        up.setEnabled(false);
      }
    }
    if(event.getActionCommand().equals("Move down"))
    {
      if(board.moveDown())
      {
        update();
      }
      else
      {
        down.setEnabled(false);
      }
    }
    if(event.getActionCommand().equals("Move right"))
    {
      if(board.moveRight())
      {
        update();
      }
      else
      {
        right.setEnabled(false);
      }
    }
    if(event.getActionCommand().equals("Move left"))
    {
      if(board.moveLeft())
      {
        update();
      }
      else
      {
        left.setEnabled(false);
      }
    }
    if(event.getActionCommand().equals("Restart"))
    {
      restart();
    }
    if(event.getActionCommand().equals("You Lost"))
    {
      restart();
    }
  }
  
  public void disableButtons()
  {
    moveMenu.setEnabled(false);
    help.setEnabled(false);
  }  
  
  public void enableButtons()
  {
    moveMenu.setEnabled(true);
    up.setEnabled(true);
    down.setEnabled(true);
    left.setEnabled(true);
    right.setEnabled(true);
    help.setEnabled(true);    
  }
  
  public String toString()
  {
    return board.toString();
  }
  
  public int[][] getBoardValues()
  {
    return board.getValues();
  }
  
  public void update()
  {
    enableButtons();
    score.setText("    Score: "+board.getScore());
    if((winner==false) && board.hasWon())
    {
      //winner options
      winner=true;
    }
    if(!board.canMove())
    {
      disableButtons();
      //game over
      this.add(losePanel);
      this.validate();
    }
    tilePanel.repaint();
    this.pack();
    this.setSize(300,300);
  } 
  public void restart()
  {
    winner=false;
    board = new Board(4);
    this.remove(losePanel);
    enableButtons();
    score.setText("    Score: "+board.getScore());
    tilePanel.repaint();
    this.pack();
    this.setSize(300,300);
  }
  public static void main(String[] args)
  {
    GameWindow game = new GameWindow();
    //System.out.println(game);
  }
  
  
}





