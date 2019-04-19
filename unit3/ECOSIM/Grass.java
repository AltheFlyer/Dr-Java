public class Grass extends Entity {
    
    public Grass(int x, int y, World w) {
        super(x, y, w);
    }
    
    public int move() {
        //Grass can't really move here
        return 0;
    }
    
    public boolean interact(Entity e) {
        //The grass doesn't really care here
        return false;
    }
    
    public String getEntityType() {
        return "Grass";
    }
}