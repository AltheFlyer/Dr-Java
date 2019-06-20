import java.util.*;
import java.awt.*;

//A class to represent the object moving around on the screen
class BouncingBall {
    double xPosition, yPosition;
    double xSpeed, ySpeed;
    double width, height;
    Rectangle boundingBox;
    
    public BouncingBall(double x, double y, double xV, double yV, double width, double height) {
        xPosition = x;
        yPosition = y;
        xSpeed=xV;
        ySpeed=yV;
        this.width = width;
        this.height = height;
        boundingBox = new Rectangle((int) xPosition, (int) yPosition, (int) width, (int) height);
    }
    
    public void update(double elapsedTime){
        xPosition += xSpeed * elapsedTime * 100;
        yPosition += ySpeed * elapsedTime * 100;
        //update the content
        if (xPosition < 0) {
            xSpeed *= -1;
            xPosition = 0;
        } else if (xPosition > 1000) {
            xSpeed *= -1;
            xPosition= 1000;
        }  else if (yPosition < 0) {
            ySpeed *= -1;
            yPosition = 0;
        } else if (yPosition > 700) {
            ySpeed *= -1;
            yPosition = 700;
        }
        boundingBox.setLocation((int) xPosition, (int) yPosition);
    }
    
    public void reverseDirection() {
        xSpeed *= -1;
        ySpeed *= -1;
    }
    
    public void draw(Graphics g) { 
        g.setColor(Color.BLUE); //There are many graphics commands that Java can use
        g.fillRect((int)xPosition, (int) yPosition, (int) this.width, (int) this.height); //notice the y is a variable that we control from our animate method          
    }
    
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }
    
    public void move(double x, double y) {
        xPosition += x;
        yPosition += y;
        boundingBox.setLocation((int) xPosition, (int) yPosition);
    }
    
    public void checkCollision(BouncingBall b) {
        if (this.getBoundingBox().intersects(b.getBoundingBox())) {
            double right = Math.abs((xPosition + width) - (b.xPosition + b.width));
            double left = Math.abs((xPosition) - (b.xPosition + b.width));
            double top = Math.abs((yPosition) - (b.yPosition + b.height));
            double bottom = Math.abs((yPosition + height) - (b.yPosition));
            
            int side = 0;
            double min = Integer.MAX_VALUE;
            if (right < min) {
                side = 0;
                min = right;
            } 
            if (left < min) {
                min = left;
                side = 1;
            }
            if (top < min) {
                min = top;
                side = 2;
            } 
            if (bottom < min) {
                min = bottom;
                side = 3;
            }
            
            
            if (side == 0) {
                this.move(-min, 0);
            } else if (side == 1) {
                this.move(min, 0);
            } else if (side == 2) {
                this.move(0, min);
            } else if (side == 3) {
                this.move(0, -min);
            }
            
            double aMass = width * height;
            double bMass = b.width * b.height;
            
            
            double aXSpeed = ((aMass - bMass) / (aMass + bMass)) * this.xSpeed +
                ((2 * bMass) / (aMass + bMass)) * b.xSpeed;
            
            double aYSpeed = ((aMass - bMass) / (aMass + bMass)) * this.ySpeed +
                ((2 * bMass) / (aMass + bMass)) * b.ySpeed;
            
            b.xSpeed = ((2 * aMass) / (aMass + bMass)) * this.xSpeed + 
                ((bMass - aMass)/(aMass + bMass)) * b.xSpeed;
            b.ySpeed = ((2 * aMass) / (aMass + bMass)) * this.ySpeed + 
                ((bMass - aMass)/(aMass + bMass)) * b.ySpeed;
               

            this.xSpeed = aXSpeed;
            this.ySpeed = aYSpeed;
            
            //this.reverseDirection();
            //b.reverseDirection();
        }
    }
}