// Graphics Imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


import java.io.File;
import java.io.IOException;



/* [DisplayGrid.java]
 * @version 2.0
 * @author Mangat, Allen Liu
 * @since April 26, 2019
 * Displays a World with entities as a window
 */
class DisplayGrid { 
    
    private JFrame frame;
    private int maxX,maxY, GridToScreenRatio;
    private World world;
    int turnCounter = 0;
    JLabel turnLabel;
    
    DisplayGrid(World w) { 
        this.world = w;
        
        maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
        maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
        GridToScreenRatio = maxY / (world.getWidth()+1);  //ratio to fit in screen as square map
        
        System.out.println("Map size: "+world.getWidth() +" by "+world.getHeight() + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
        
        this.frame = new JFrame("Map of World");
        
        GridAreaPanel worldPanel = new GridAreaPanel();
        worldPanel.setPreferredSize(new Dimension(maxY + 200, maxY));
        
        turnLabel = new JLabel("Turn 0");
        turnLabel.setPreferredSize(new Dimension(150, maxY));
        
        FlowLayout layout = new FlowLayout();
        
        frame.setLayout(layout);
        
        frame.getContentPane().add(worldPanel);
        frame.getContentPane().add(turnLabel);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setVisible(true);
    }
    
    /**
     * [refresh]
     * repaints the screen with any updated world values
     */
    public void refresh() { 
        frame.repaint();
    }
    
    public void incrementTurn() {
        turnCounter++;
        frame.setTitle("Map of the World: Turn " + turnCounter);
    }
    
    public void pushAlert(String alert) {
        System.out.println(alert);
    }
    
    class GridAreaPanel extends JPanel implements MouseListener {
      
    int mX = 0;
    int mY = 0;
    
    int selectedX = 0;
    int selectedY = 0;
    
    Entity selected = null;
    
    public GridAreaPanel() {
        addMouseListener(this);
    }
    
    public void paintComponent(Graphics g) {        
      //super.repaint();
      setDoubleBuffered(true); 
      g.setColor(Color.BLACK);
      
      for(int i = 0; i<world.getWidth();i++) { 
          for(int j = 0; j<world.getHeight();j++) { 
              Entity e = world.getEntityAt(i, j);
              if (e != null) {
                  g.drawImage(e.getSprite() ,i*GridToScreenRatio,j*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }
              /*
              if (e instanceof Wolf) {    //This block can be changed to match character-color pairs
                  g.setColor(Color.RED);
              } else if (e instanceof Sheep) {
                  //g.setColor(Color.BLUE);
                  g.setColor(((Sheep)(e)).getWoolColor());
              } else if (e instanceof Grass) {
                  g.setColor(Color.GREEN);
              } else {
                  g.setColor(Color.WHITE);
              }
              
              g.fillRect(i*GridToScreenRatio, j*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
              g.setColor(Color.BLACK);
              //g.drawString(type, i*GridToScreenRatio, (j + 1)*GridToScreenRatio);
              g.drawRect(i*GridToScreenRatio, j*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
              //g.drawImage(sheepImage,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              */
          }
      }
      if (selected != null && selected.getHealth() > 0) {
          g.setColor(Color.BLUE);
          g.drawOval(selected.getX() * GridToScreenRatio, 
                     selected.getY() * GridToScreenRatio, 
                     GridToScreenRatio, 
                     GridToScreenRatio);
      }
      if (selected != null) {
          if (selected.getHealth() <= 0) {
              g.drawString(selected.getEntityType() + " (DEAD)", this.getWidth() - 150, 170);
          } else {
              g.drawString(selected.getEntityType(), this.getWidth() - 150, 170);
          }   
          g.drawString(selected.toString(), this.getWidth() - 150, 200);
          g.drawString(selected.getPhenotype(), this.getWidth() - 150, 230);
          g.drawString(selected.getGenotype(), this.getWidth() - 150, 260);
      }
      
      //g.fillRect(mX, mY, 100, 100);
    }
    
    public void mousePressed(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
        selectedX = mX / GridToScreenRatio;
        selectedY = mY / GridToScreenRatio;
        selected = world.getEntityAt(selectedX, selectedY);
    }

    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse released; # of clicks: "
        //            + e.getClickCount());
    }

    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse entered");
    }

    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse exited");
    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse clicked (# of clicks: "
        //            + e.getClickCount() + ")");

    }
    
  }//end of GridAreaPanel
  
} //end of DisplayGrid