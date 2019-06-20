abstract class Clock {
    
    static long lastTime = System.nanoTime();
    
    static void setTimeMark() {
        lastTime = System.nanoTime();
    }
    
    static double getDeltaTime() {
        long nextMark = System.nanoTime() - lastTime;
        lastTime = System.nanoTime();
        return nextMark / 1.0E9;
    }
}