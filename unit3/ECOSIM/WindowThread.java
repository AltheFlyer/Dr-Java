/**
 * [WindowThread.java]
 * @version 1.0
 * @author Allen Liu
 * @since April 27, 2019
 * Manages a GUI in relation to a simulated world
 */
public class WindowThread extends Thread {
    
    private DisplayGrid display;
    private long frameDelay;
    
    public WindowThread(World w, double fps) {
        display = new DisplayGrid(w);
        this.frameDelay = (long) (1000 / fps);
    }
    
    public void run() {
        while (true) {
            try {
                this.sleep(frameDelay);
            } catch (java.lang.InterruptedException e) {
                e.printStackTrace();
            }
            
            display.refresh();
        }
    }
    
    /**
     * [incrementTurn]
     * increments an internal turn counter on the window GUI
     */
    public void incrementTurn() {
        display.incrementTurn();
    }
    
    public void pushAlert(String alert) {
        display.pushAlert(alert);
    }
}