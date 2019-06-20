import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*; 
import java.util.Scanner;
import java.util.Random;

// An inner class representing the panel on which the game takes place
class GamePanel extends JPanel implements KeyListener {
    
    BouncingBall ball;
    FrameRate frameRate;
    BouncingBall[] balls;
    
    
    
    Player player;
    //Clock clock;
    
    //constructor
    public GamePanel() { 
        setPreferredSize(new Dimension(1024,768));
        frameRate = new FrameRate();
        //ball = new BouncingBall();
        balls = new BouncingBall[10];
        for (int i = 0; i < balls.length; ++i) {
            int xSign = 0;
            int ySign = 0;
            if (Math.random() < 0.5) {
                xSign = 1;
            } else {
                xSign = -1;
            }
            if (Math.random() < 0.5) {
                ySign = 1;
            } else {
                ySign = -1;
            }
            balls[i] = new BouncingBall(100 + Math.random() * 800, 
                                        100 + Math.random() * 500, 
                                        xSign * (Math.random() + 0.5), 
                                        ySign * (Math.random() + 0.5),
                                        25 + 50 * Math.random(),
                                        25 + 50 * Math.random());
        }
        //clock = new Clock();
        
        player = new Player(100, 100);
        setFocusable(true);      //this should be false for all other components
requestFocusInWindow();

        addKeyListener(this);
    }
    
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g); //required to ensure the panel si correctly redrawn
        
        //update the content
        double deltaTime = Clock.getDeltaTime();
                
        player.move(deltaTime);

        g.setColor(Color.GREEN);
        g.fillRect((int) player.x, (int) player.y, 25, 25);
        
        
        frameRate.update(deltaTime);
        for (int i = 0; i < balls.length; ++i) {
            balls[i].update(deltaTime);
            balls[i].draw(g);
            for (int j = 0; j < balls.length; ++j) {
                balls[i].checkCollision(balls[j]);
            }
            
            if (player.hasCollided(balls[i].boundingBox)) {
                System.out.println(":(");
            }
        }
        
        
        
        //draw the screen
        
        frameRate.draw(g,10,10);

        //request a repaint
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            player.setYVelocity(0);
        }
        if (e.getKeyChar() == 'a') {
            player.setXVelocity(0);
        }
        if (e.getKeyChar() == 's') {
            player.setYVelocity(0);
        }
        if (e.getKeyChar() == 'd') {
            player.setXVelocity(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            player.setYVelocity(-100);
        }
        if (e.getKeyChar() == 'a') {
            player.setXVelocity(-100);
        }
        if (e.getKeyChar() == 's') {
            player.setYVelocity(100);
        }
        if (e.getKeyChar() == 'd') {
            player.setXVelocity(100);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
}