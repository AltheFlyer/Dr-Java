public class Wolf extends Entity implements Comparable<Wolf> {
    
    public Wolf(int x, int y, Map w) {
        super(x, y, w);
    }
    
    public int move() {
        int r = randint(0, 5);
        //None, Up, Right, Down, Left = {0, 1, 2, 3, 4}
        return r;
    }
    
    public boolean interact(Entity e) {
        if (e instanceof Sheep) {
            //CONSUME
            System.out.println("CONSUME");
            modHealth(e.getHealth());
            //Movement will naturally destroy the sheep
            return true;
        } else if (e instanceof Wolf) {
            //Survival of the fittest
            System.out.println("SURVIVAL OF THE FITTEST");
            if (compareTo((Wolf)(e)) < 0) {
                e.modHealth(-10);
            } else if (compareTo((Wolf)(e)) > 0) {
                this.modHealth(-10);
            }
            return false;
        } else if (e instanceof Grass) {
            System.out.println("Rip grass");
            //Tramples grass, ruining economy
            return true;
        }
       
        //Something else?
        return false;
    }
    
    public void tick() {
        modHealth(-1);
        refreshMove();
    }
    
    public String getEntityType() {
        return "1";
    }
    
    public int compareTo(Wolf w) {
        return w.getHealth() - this.getHealth();
    }
}