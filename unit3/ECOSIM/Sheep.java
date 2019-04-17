public class Sheep extends Entity {
    
    public Sheep(int x, int y, Map w) {
        super(x, y, w);
    }
    
    public int move() {
        int r = randint(0, 5);
        //None, Up, Right, Down, Left = {0, 1, 2, 3, 4}
        return r;
    }
    
    //Returns whether movement is made
    public boolean interact(Entity e) {
        if (e instanceof Sheep) {
            //Mating
            if (getHealth() > 20 && e.getHealth() > 20) {
                world.addSheepTick();
                this.modHealth(-10);
                e.modHealth(-10);
                System.out.println("Mating");
            }
            //No movement occurs regardless
            return false;
        } else if (e instanceof Grass) {
            modHealth(e.getHealth());
            System.out.println("Grass Eaten");
            //We don't touch the grass entity, since the sheep's movement will delete it anyways
            return true;
        }
        //If theres a wolf or something, we block movement
        return false;
    }
    
    public void tick() {
        modHealth(-1);
        refreshMove();
    }
    
    public String getEntityType() {
        return "2";
    }
}
