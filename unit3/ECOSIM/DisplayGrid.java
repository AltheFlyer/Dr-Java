
/* [DisplayGrid.java]
 * A Small program for Display a 2D String Array graphically
 * @author Mangat
 */

// Graphics Imports
//import javax.swing.*;
//import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class DisplayGrid { 

  private JFrame frame;
  private int maxX,maxY, GridToScreenRatio;
  private String[][] world;
  
  DisplayGrid(String[][] w) { 
    this.world = w;
    
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = maxY / (world.length+1);  //ratio to fit in screen as square map
    
    System.out.println("Map size: "+world.length+" by "+world[0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
    
    this.frame = new JFrame("Map of World");
    
    GridAreaPanel worldPanel = new GridAreaPanel();
    
    frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }
  
  
  public void refresh() { 
    frame.repaint();
  }
  
  public void refresh(String[][] grid) {
      world = grid;
      frame.repaint();
  }
      
  class GridAreaPanel extends JPanel implements MouseListener {
      
    int mX = 0;
    int mY = 0;
    
    public GridAreaPanel() {
        addMouseListener(this);
    }
    
    public void paintComponent(Graphics g) {        
      //super.repaint();
      
      setDoubleBuffered(true); 
      g.setColor(Color.BLACK);
      
      
      for(int i = 0; i<world[0].length;i=i+1) { 
          for(int j = 0; j<world.length;j=j+1) { 
              
              if (world[i][j].equals("Wolf")) {    //This block can be changed to match character-color pairs
                  g.setColor(Color.RED);
              } else if (world[i][j].equals("Sheep")) {
                  g.setColor(Color.BLUE);
              } else if (world[i][j].equals("Grass")) {
                  g.setColor(Color.GREEN);
              } else {
                  g.setColor(Color.WHITE);
              }
              
              g.fillRect(i*GridToScreenRatio, j*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
              g.setColor(Color.BLACK);
              g.drawString(world[i][j], i*GridToScreenRatio, (j + 1)*GridToScreenRatio);
              g.drawRect(i*GridToScreenRatio, j*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
          }
      }
      
      //g.fillRect(mX, mY, 100, 100);
    }
    
     public void mousePressed(MouseEvent e) {
       System.out.println("Mouse pressed; # of clicks: "
                    + e.getClickCount());
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released; # of clicks: "
                    + e.getClickCount());
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked (# of clicks: "
                    + e.getClickCount() + ")");
        mX = e.getX();
        mY = e.getY();
        System.out.println(mX + " " + mY);
    }
    
  }//end of GridAreaPanel
  
} //end of DisplayGrid