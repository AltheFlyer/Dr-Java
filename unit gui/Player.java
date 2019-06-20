import java.awt.Rectangle;

public class Player {
    
    double x;
    double y;
    
    double xV;
    double yV;
    
    Rectangle boundingBox;
    
    public Player(double x, double y) {
        this.x = x;
        this.y = y;
        this.xV = 0;
        this.yV = 0;
        this.boundingBox = new Rectangle((int) x, (int) y, 25, 25);
    }
    
    public void setXVelocity(double x) {
        xV = x;
    }
    
    public void setYVelocity(double y) {
        yV = y;
    }
    
    public void move(double time) {
        this.x += xV * time;
        this.y += yV * time;
        this.boundingBox.setLocation((int) x, (int) y);
    }
    
    public boolean hasCollided(Rectangle r) {
        return boundingBox.intersects(r);
    }
}