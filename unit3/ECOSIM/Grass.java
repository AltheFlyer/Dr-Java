public class Grass extends Entity {
    
    public Grass(int x, int y, Map w) {
        super(x, y, w);
    }
    
    public int move() {
        return 0;
    }
    
    public boolean interact(Entity e) {
        //The grass doesn't really care here
        return false;
    }
    
    public String getEntityType() {
        return "3";
    }
}