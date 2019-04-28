/**
 * [MapThread]
 * @version 1.0
 * @author Allen Liu
 * @since April 27, 2019
 * Manages internal map ticks and updates for a World
 */
public class MapThread extends Thread {
    
    private World world;
    private long tickDelay;
    private WindowThread window;
    
    public MapThread(World w, long timeDelay) {
        this.world = w;
        this.tickDelay = timeDelay;
        this.window = null;
    }
    
    public MapThread(World w, long timeDelay, WindowThread window) {
        this.world = w;
        this.tickDelay = timeDelay;
        this.window = window;
    }
    
    public void attachWindowThread(WindowThread window) {
        this.window = window;
    }
    
    public void run() {
        long lastFrame = System.currentTimeMillis();
        long processTime = lastFrame;
        while ((world.getNumSheep() > 0) && (world.getNumWolves() > 0)) {
            processTime = System.currentTimeMillis() - lastFrame;
            try {
                this.sleep(tickDelay - processTime);
            } catch (java.lang.InterruptedException e) {
                System.out.println("threading error");
            }
            world.tick();
            if (window != null) {
                window.incrementTurn();
            }
            lastFrame = System.currentTimeMillis();
        }
        
        if (window != null) {
            if (world.getNumSheep() <= 0) {
                window.pushAlert("Sheep Extinction");
            }
            if (world.getNumWolves() <= 0) {
                window.pushAlert("Wolf Extinction");
            }
        } else {
            if (world.getNumSheep() <= 0) {
                System.out.println("Sheep Extinction");
                return;
            }
            if (world.getNumWolves() <= 0) {
                System.out.println("Wolf Extinction");
                return;
            }
        }
        
    }
}