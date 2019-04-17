abstract public class Entity {
    
    private int health;
    private int x, y;
    private boolean canMove;
    Map world;
    
    public Entity(int x, int y, Map w) {
        health = 40;
        world = w;
        this.x = x;
        this.y = y;
        canMove = true;
    }
    
    public int move() {
        return 0;
    }
    
    public boolean interact(Entity e) {
        return false;
    }
    
    public void tick() {
        
    }
    
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    abstract public String getEntityType();
    
    /**
     * 
     * Bottom Inclusive
     */
    public static int randint(int low, int high) {
        return (int) (low + Math.random() * (high - low));
    }
    
    public int getHealth() {
        return health;
    }
    
    public void modHealth(int modifier) {
        health += modifier;
    }
    
    public boolean hasMoved() {
        return !canMove;
    }
    
    public void refreshMove() {
        canMove = true;
    }
    public void exhaustMove() {
        canMove = false;
    }
}