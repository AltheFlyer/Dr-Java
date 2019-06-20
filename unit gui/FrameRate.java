import java.awt.*;

//Better to abstract the FrameRate stuff
class FrameRate { 
    
    String frameRate;
    
    double elapsedInspection;
    int frames;
    
    public FrameRate() {
        this.frameRate = "0";
        this.elapsedInspection = 0;
    }
    
    public void update(double deltaTime) {
        this.elapsedInspection += deltaTime;
        this.frames++;
        if (this.elapsedInspection > 1.0) {
            this.elapsedInspection -= 1.0;
            this.frameRate = frames + " FPS";
            this.frames = 0;
        }
    }
    
    public void draw(Graphics g, int x, int y) {
        g.drawString(this.frameRate, x, y);
    }
    
}