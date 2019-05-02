//Graphics Imports
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

/* [DisplayGrid.java]
 * @version 3.0
 * @author Mangat, Allen Liu
 * @since May 1, 2019
 * Displays a World with entities as a window
 */
class DisplayGrid { 
    
    private JFrame frame;
    private int maxX,maxY, GridToScreenRatio;
    private World world;
    private int turnCounter = 0;
    
    DisplayGrid(World w) { 
        this.world = w;
        
        maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
        maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
        GridToScreenRatio = maxY / (world.getWidth()+1);  //ratio to fit in screen as square map
        
        System.out.println("Map size: " + world.getWidth() + " by " + world.getHeight() + 
                           "\nScreen size: " + maxX + "x" + maxY + " Ratio: " + GridToScreenRatio);
        
        this.frame = new JFrame("Map of World");
        
        GridAreaPanel worldPanel = new GridAreaPanel();
        
        frame.getContentPane().add(worldPanel);
        
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
    
    /**
     * [incrementTurn]
     * increments the turn counter in the window
     */
    public void incrementTurn() {
        turnCounter++;
        frame.setTitle("Map of the World: Turn " + this.turnCounter);
    }
    
    public void endSimulation(String extraMessage) {
        frame.setTitle(String.format("Simulation Ended (Turn %d) - %s", this.turnCounter, extraMessage));
    }
    /**
     * [GridAreaPanel.java]
     * @version 2.0
     * @author Allen Liu
     * @since April 30, 2019
     * A panel that draws the ecosim world
     */
    class GridAreaPanel extends JPanel implements MouseListener {
      
    private int mX = 0;
    private int mY = 0;
    
    private int selectedX = 0;
    private int selectedY = 0;
    
    Entity selected = null;
    
    private final Color background = new Color(42, 224, 79);
    
    public GridAreaPanel() {
        addMouseListener(this);
    }
    
    /**
     * [paintComponent]
     * repaints the grid, updating any tiles if a turn has passed, or if another mouse click is made
     */
    public void paintComponent(Graphics g) {        
      //super.repaint();
      setDoubleBuffered(true); 
      g.setColor(background);
      
      g.fillRect(0, 0, world.getWidth() * GridToScreenRatio, world.getHeight() * GridToScreenRatio);
      
      
      for(int i = 0; i<world.getWidth();i++) { 
          for(int j = 0; j<world.getHeight();j++) { 
              Entity e = world.getEntityAt(i, j);
              if (e != null) {
                  g.drawImage(e.getSprite(), i*GridToScreenRatio, j*GridToScreenRatio,
                              GridToScreenRatio, GridToScreenRatio,this);
              }
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
              g.setColor(Color.RED);
              g.drawString(selected.getEntityType() + " (DEAD)", maxY + 50, 170);
          } else {
              g.setColor(Color.BLACK);
              g.drawString(selected.getEntityType(), maxY + 50, 170);
          }   
          g.drawString(selected.toString(), maxY + 50, 200);
          g.drawString(selected.getPhenotype(), maxY + 50, 230);
          g.drawString(selected.getGenotype(), maxY + 50, 260);
      }
      
      //g.fillRect(mX, mY, 100, 100);
    }
    
    /**
     * [mousePressed]
     * runs whenever the mouse is pressed using a MouseEvent, 
     * allowing for entity selection
     * @param e the MouseEvent that is triggered
     */
    public void mousePressed(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
        selectedX = mX / GridToScreenRatio;
        selectedY = mY / GridToScreenRatio;
        selected = world.getEntityAt(selectedX, selectedY);
    }

    /**
     * [mouseReleased]
     * unimplemented
     * @param e the MouseEvent that is triggered
     */
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * [mouseEntered]
     * unimplemented
     * @param e the MouseEvent that is triggered
     */
    public void mouseEntered(MouseEvent e) {
    }
    
    /**
     * [mouseExited]
     * unimplemented
     * @param e the MouseEvent that is triggered
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * [mouseClicked]
     * unimplemented
     * @param e the MouseEvent that is triggered
     */
    public void mouseClicked(MouseEvent e) {
    }
    
  }//end of GridAreaPanel
  
} //end of DisplayGrid