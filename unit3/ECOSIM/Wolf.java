public class Wolf extends Entity implements Comparable<Wolf> {
    
    public Wolf(World w) {
        super(0, 0, w);
    }
    
    public Wolf(int x, int y, World w) {
        super(x, y, w);
    }
    
    public int move() {
        //Only check when hungry
        //if (getHealth() < 35) {
        int maxValue = 0;
        int move = 0;
        //Up, Right, Down, Left
        int[] xMoves = {0, 1, 0, -1};
        int[] yMoves = {-1, 0, 1, 0};
        
        for (int i = 0; i < 4; ++i) {
            if (world.tileExists(getX() + xMoves[i], getY() + yMoves[i]) && world.getEntityAt(getX() + xMoves[i], getY() + yMoves[i]) instanceof Sheep) {
                if (world.getEntityAt(getX() + xMoves[i], getY() + yMoves[i]).getHealth() > maxValue) {
                    maxValue = world.getEntityAt(getX() + xMoves[i], getY() + yMoves[i]).getHealth();
                    move = i + 1;
                }
            }
        }
        if (maxValue != 0) {
            return move;
        }
        //}
        
        //Default random walk
        int r = randint(0, 5);
        //None, Up, Right, Down, Left = {0, 1, 2, 3, 4}
        return r;
    }
    
    public boolean interact(Entity e) {
        if (e instanceof Sheep) {
            //CONSUME
            //System.out.println("CONSUME");
            modHealth(e.getHealth());
            //Movement will naturally destroy the sheep
            return true;
        } else if (e instanceof Wolf) {
            int diff = compareTo((Wolf)(e));
            //Survival of the fittest, wolves will attack if it's advantageous
            //System.out.println("SURVIVAL OF THE FITTEST");
            if (diff < -10/*0*/) {
                e.modHealth(-10);
            } else if (diff > 10/*0*/) {
                this.modHealth(-10);
            }
            if (getHealth() > 20 && e.getHealth() > 20) {
                world.addEntity(new Wolf(world), getX(), getY(), 1);
                
                this.modHealth(-10);
                e.modHealth(-10);
                //System.out.println("Mating");
            }
            
            return false;
        } else if (e instanceof Grass) {
            //System.out.println("Rip grass");
            //Tramples grass, ruining economy
            return true;
        }
       
        //Something else?
        return false;
    }
    
    public void tick() {
        modHealth(-1);
        refreshMove();
        super.tick();
    }
    
    public String getEntityType() {
        return "Wolf";
    }
    
    public int compareTo(Wolf w) {
        return w.getHealth() - this.getHealth();
    }
}