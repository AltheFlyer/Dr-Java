abstract public class Entity {
    
    private int health;
    private int maxHealth = 40;
    
    private int age;
    private int maxAge = 20;
    
    private int x, y;
    private boolean canMove;
    
    World world;
    
    public Entity(int x, int y, World w) {
        health = 20;
        world = w;
        this.x = x;
        this.y = y;
        canMove = true;
        age = 0;
    }
    
    public int move() {
        return 0;
    }
    
    public boolean interact(Entity e) {
        return false;
    }
    
    public void tick() {
        age++;
        if (age > maxAge) {
            health = 0;
        }
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
        if (health > maxHealth) {
            health = maxHealth;
        }
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
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}