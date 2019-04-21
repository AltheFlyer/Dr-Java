
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
  private World world;
  
  DisplayGrid(World w) { 
    this.world = w;
    
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = maxY / (world.getWidth()+1);  //ratio to fit in screen as square map
    
    System.out.println("Map size: "+world.getWidth() +" by "+world.getHeight() + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
    
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
  
  public void refresh(World w) {
      world = w;
      frame.repaint();
  }
      
  class GridAreaPanel extends JPanel implements MouseListener {
      
    int mX = 0;
    int mY = 0;
    
    int selectedX = 0;
    int selectedY = 0;
    
    public GridAreaPanel() {
        addMouseListener(this);
    }
    
    public void paintComponent(Graphics g) {        
      //super.repaint();
      
      setDoubleBuffered(true); 
      g.setColor(Color.BLACK);
      
      
      for(int i = 0; i<world.getWidth();i=i+1) { 
          for(int j = 0; j<world.getHeight();j=j+1) { 
              String type = world.getEntityString(i, j);
              
              if (type.equals("Wolf")) {    //This block can be changed to match character-color pairs
                  g.setColor(Color.RED);
              } else if (type.equals("Sheep")) {
                  g.setColor(Color.BLUE);
              } else if (type.equals("Grass")) {
                  g.setColor(Color.GREEN);
              } else {
                  g.setColor(Color.WHITE);
              }
              
              g.fillRect(i*GridToScreenRatio, j*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
              g.setColor(Color.BLACK);
              g.drawString(type, i*GridToScreenRatio, (j + 1)*GridToScreenRatio);
              g.drawRect(i*GridToScreenRatio, j*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
          }
      }
      
      g.drawRect(world.getActiveX() * GridToScreenRatio + 3, world.getActiveY() * GridToScreenRatio + 3, GridToScreenRatio - 3, GridToScreenRatio - 3);
      if (world.hasEntity(world.getActiveX(), world.getActiveY())) {
          g.drawString(world.getEntityAt(world.getActiveX(), world.getActiveY()).toString(), 900, 200);
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
        selectedX = mX / GridToScreenRatio;
        selectedY = mY / GridToScreenRatio;
        world.setActiveTile(mX / GridToScreenRatio, mY / GridToScreenRatio);
        System.out.println(selectedX + " " + selectedY);
    }
    
  }//end of GridAreaPanel
  
} //end of DisplayGrid