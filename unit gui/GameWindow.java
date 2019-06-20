import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.Scanner;
import java.util.Random;

//This class represents the game window
class GameWindow extends JFrame { 
    
    //Window constructor
    public GameWindow() { 
        setTitle("Simple Game Loop Example");
        //setSize(1280,1024);  // set the size of my window to 400 by 400 pixels
        setResizable(true);  // set my window to allow the user to resize it
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed
        
        getContentPane().add(new GamePanel());
        pack(); //makes the frame fit the contents
        setVisible(true);
    }
}