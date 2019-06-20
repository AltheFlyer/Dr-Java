import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BasicGUI5 { 
  
  /* All Jcomponents (buttons, fields, etc) that you want to access in the actionPerformed method
   * must be partially declared as class variable, otherwise they are only visible in the main method
   * the compnents are still initialized and set up in the main method 
   * note - they must be declared as static variables in this part of the program
  */

  static int y=0;   // this variable is used in many methods, it must be declared here
  
  static myGraphicsPanel rightPanel;  //our custom panel needs to be accessed in multiple methods
  
  static JButton button;
  // ****
  
  
  public static void main(String[] args) {
  
    // **** Create a new Window and set it up
    JFrame myWindow = new JFrame("This is the frame!"); //create a new window with a title
    
    myWindow.setSize(500,500);  // set the size of my window to 400 by 400 pixels
    myWindow.setResizable(true);  // set my window to allow the user to resize it
    myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed
    

    myWindow.setLayout(new GridLayout(0,1));  // <-- my frame is set up as a grid layout. 0 means unlimited rows    
    // ****
    
    
    // **** Create a some Panels. Leaving the layout as default
    JPanel leftPanel = new JPanel();
    rightPanel = new myGraphicsPanel();  //this is our custom panel to display graphics
    
    
    // ****
    
     // **** Create a button witha listener
    button = new JButton(" animate "); 
    button.addActionListener(new buttonListener());
    //****

    // **** Now adding all the components to the panels           
    leftPanel.add(button);    
    // ****
    
    // **** Add the main panel to the frame, the order is important
    myWindow.add(leftPanel); 
    myWindow.add(rightPanel); 
    
    // ****
    
    // **** finally, display the window on the screen
    myWindow.setVisible(true); // make the window visible on the screen
        

    // ***** animation code starts here **** (this can also be a game-loop!)
    
    
    int yChange=5;
    
    while(true) { 
        if (y>400) {
            yChange=-5;
        }else if(y<0){
            yChange=5;
        }
      
      y = y + yChange;
      //System.out.println(y);
      
      try{Thread.sleep(10);    //These two lines add a small delay
      } catch (Exception exc){}
      

      myWindow.repaint(); //now repaint the screen before looping again

    }
    // ***************************************
    
  
  } // *** end of main method
  

  
  
  
  //****************** An internal class to repond to the button event ******
  /*
   * Each listener requires the code below in order to respond to an event. The name of this
   * class much match the listener that was added to the JComponent. In our case, the button.
   * Note - this is not in the main method, but is still within our class
   *  
   */
  
  static class buttonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {     //this is the only method in this class and it will run automatically when the button is activated
    
    // **** This is where the code to respond to the button event goes
      y = 0;  //y gets reset
      //System.out.println("Animating...");   
     // animate();  //if the user clicks the button then run the animate method (which runs forever)
      
    // ****
    }
  } // ******* end of action listener
  
  //****************** An internal class to contain all the graphics code ******
  /*
   * To accomplish animation in Java, you need to design a 'custom panel'. this panel can be used like
   * any other panel is in your main program. This panel will have all the drawing commands in it and
   * the graphics will be seen on it when displayed in your GUI
   *  
   */
  static class myGraphicsPanel extends JPanel {
    public void paintComponent(Graphics g) { 
      super.paintComponent(g); //required
      
      g.setColor(Color.BLUE); //There are many graphics commands that Java can use
      g.fillRect(50, y, 25, 25); //notice the y is a variable that we control from our animate method          
    }
  }
  
  
}