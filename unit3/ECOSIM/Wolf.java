import java.util.ArrayList;

/**
 * [Wolf.java]
 * @version 1.7
 * @author Allen Liu
 * @since April 26, 2019
 * A class for wolf entities, who will try to group with other wolves and seek out sheep, 
 * they can also trample grass
 */
public class Wolf extends Entity implements Comparable<Wolf> {
    
    private int hungerThreshold = 30;
    
    /**
     * Creates a wolf in a world with randomized attributes
     * @param w the world that the wolf is on
     */
    public Wolf(World w) {
        this(0, 0, w, Genetics.getRandomGender());
    }
    
    /**
     * Creates a wolf with a set position and gender, but random attributes
     * @param x the x position in the world that the wolf is on
     * @param y the y position in the world that the wolf is on
     * @param w the world that the wolf is in
     * @param gender the gender of the wolf as a dna string
     */
    public Wolf(int x, int y, World w, String gender) {
        super(x, y, w, gender);
        this.decompilePhenotype(3, 50, 50, 15);
    }
    
    /**
     * Creates a wolf with a set genome, but unknown position
     * @param pheno the phenotype of the wolf
     * @param geno the genotype of the wolf
     */
    public Wolf(World w, String pheno, String geno) {
        this(0, 0, w, pheno, geno);
    }
    
    /**
     * Creates a wolf with a set genome and position
     * @param x the x position in the world that the wolf is on
     * @param y the y position in the world that the wolf is on
     * @param w the world that the wolf is in
     * @param pheno the phenotype of the wolf
     * @param geno the genotype of the wolf
     */
    public Wolf(int x, int y, World w, String pheno, String geno) {
        super(x, y, w, pheno, geno);
        this.decompilePhenotype(3, 50, 50, 15);
    }
    
    /**
     * [move]
     * declares what movement will be made by the entity with an int
     * @return int, a movement integer:<br>
     * 0 is no move<br>
     * 1 is up<br>
     * 2 is right<br>
     * 3 is down<br>
     * 4 is left
     */
    public int move() {
        //Only check nearby tiles for an instant sheep kill when hungry
        /*
        if (this.getHealth() < this.hungerThreshold) {
            int maxValue = 0;
            int move = 0;
            
            for (int i = 0; i < 4; ++i) {
                if (this.world.tileExists(this.getX() + Entity.X_MOVES[i], this.getY() + Entity.Y_MOVES[i]) && 
                    this.world.getEntityAt(this.getX() + Entity.X_MOVES[i], 
                                           this.getY() + Entity.Y_MOVES[i]) instanceof Sheep) {
                    
                    if (this.world.getEntityAt(this.getX() + Entity.X_MOVES[i], 
                                               this.getY() + Entity.Y_MOVES[i]).getHealth() > maxValue) {
                        maxValue = this.world.getEntityAt(this.getX() + Entity.X_MOVES[i], this.getY() + Entity.Y_MOVES[i]).getHealth();
                        move = i + 1;
                    }
                }
            }
            if (maxValue != 0) {
                return move;
            }
        }
        
        //Default random walk
        int r;
        if (Math.random() > (1.0 / this.getIntelligence())) {
            r = randint(0, 5);
        } else {
        */
        int r = this.getHeuristicTile();
        //}

        return r;
    }
    
    /**
     * [interact]
     * Checks to see if this entity can interact with another entity, and sees if
     * movement can be made after the interaction.
     * Interactions are as follows:<br>
     * Wolf will kill/consume sheep, gaining health<br>
     * Wolf will trample (kill) tall grass<br>
     * Wolf will attack wolves if there is a significant health difference (and same gender), 
     * or will mate with wolves of the opposite gender
     * @param e the entity to make an interaction with
     * @return boolean whether movement is made after the interaction
     */
    public boolean interact(Entity e) {
        if (e instanceof Sheep) {
            //Sheep eating
            this.modHealth(e.getHealth());
            //Movement will naturally destroy the sheep but to prevent shenanigans...
            e.modHealth(-e.getHealth());
            return true;
        } else if (e instanceof Wolf) {
            //Survival of the fittest, wolves will attack if it's advantageous, and if they are the same gender
            if (this.isMale() == e.isMale()) {
                //Difference in wolf health
                int diff = compareTo((Wolf)(e));
                
                if (diff < -20) {
                    this.modHealth(-10);
                } else if (diff > 20) {
                    e.modHealth(-10);
                }
            //Opposite gender, try to make babies
            } else {
                if ((this.getHealth() > 20) && (e.getHealth() > 20)) {
                    //Generate a random gender for the new wolf
                    String genderMod = Genetics.getRandomGender();
                    
                    //Create the new wolf's DNA
                    String[] newDNA = Genetics.generateOrganism(this.getPhenotype().substring(6),
                                                                this.getGenotype().substring(6),
                                                                e.getPhenotype().substring(6),
                                                                e.getGenotype().substring(6));
                    
                    //Add gender to the phenotype and genotypes
                    newDNA[0] = genderMod + newDNA[0];
                    newDNA[1] = genderMod + newDNA[1];
                    
                    world.addEntity(new Wolf(world, newDNA[0], newDNA[1]), getX(), getY(), 1);
                    
                    this.modHealth(-10);
                    e.modHealth(-10);
                    //System.out.println("Mating");
                }
            }
            return false;
        } else if (e instanceof Grass) {
            //Tramples grass, ruining economy
            e.modHealth(-e.getHealth());
            return true;
        }
       
        //Something else?
        return false;
    }
    
    /**
     * [tick]
     * performs end/start of turn actions, should run once between each turn,
     * removing 1 health from the wolf and restoring the wolf's turn
     */
    public void tick() {
        modHealth(-1);
        refreshMove();
        super.tick();
    }
    
    /**
     * [getEntityType]
     * gets an identifier of the entity as a capitalized string
     * @return String "Wolf"
     */
    public String getEntityType() {
        return "Wolf";
    }
    
    /**
     * [compareTo]
     * Compares one wolf to another, returning the difference in health between this wolf, and another
     * @return int, the difference in the wolves' health
     */
    public int compareTo(Wolf w) {
        return this.getHealth() - w.getHealth();
    }
    
    /**
     * [getHeuristicTile]
     * Finds the best tile to move to based on positions of other entities within this entity's vision range.
     * Heuristic is based on moving to the nearest sheep
     * <br><br>
     * Process description:
     * Start by doing bfs with a range based on the entity's range, save distance in an array
     * Save positions of all other entities within that range
     * Run bfs based on each entity type, generating values for distance from the given entity type
     * The secondary bfs stays within the range as the original distance array determines which tiles are valid
     * Once the distances have been found use a heuristic.
     * to determine the best tile to move to
     * @return int, a movement integer:<br>
     * 0 is no move<br>
     * 1 is up<br>
     * 2 is right<br>
     * 3 is down<br>
     * 4 is left
     */
    public int getHeuristicTile() {
        int[][] distance = new int[world.getWidth()][world.getHeight()];
        int[][] wolfDistance = new int[world.getWidth()][world.getHeight()];
        int[][] sheepDistance = new int[world.getWidth()][world.getHeight()];
        int[][] grassDistance = new int[world.getWidth()][world.getHeight()];
        
        for (int i = 0; i < world.getWidth(); ++i) {
            for (int j = 0; j < world.getHeight(); ++j) {
                distance[i][j] = -1;
                wolfDistance[i][j] = -1;
                sheepDistance[i][j] = -1;
                grassDistance[i][j] = -1;
            }
        }
        
        
        //location arrays
        ArrayList<Integer> wolfPositions = new ArrayList<Integer>();
        ArrayList<Integer> sheepPositions = new ArrayList<Integer>();
        ArrayList<Integer> grassPositions = new ArrayList<Integer>();
        
        int topTile = 0;
        int rightTile = 0;
        int bottomTile = 0;
        int leftTile = 0;
        //Scout to find all entities in range
        ArrayList<Integer> tileQueue = new ArrayList<Integer>(); 
        tileQueue.add(world.coordToInt(getX(), getY()));
        distance[getX()][getY()] = 0;
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = world.intToX(tile);
            int y = world.intToY(tile);
            
            if ((world.getEntityAt(x, y) instanceof Wolf)) {
                wolfPositions.add(tile);
            } else if (world.getEntityAt(x, y) instanceof Sheep) {
                sheepPositions.add(tile);
            } else if (world.getEntityAt(x, y) instanceof Grass) {
                grassPositions.add(tile);
            }
            
            if (distance[x][y] < getRange()) {
                for (int i = 0; i < 4; ++i) {
                    if ((world.tileExists(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i])) && 
                        (distance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] == -1)) {
                        tileQueue.add(world.coordToInt(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i]));
                        distance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] = distance[x][y] + 1;
                    }
                }
            }
        }
       
        
        //Wolf bfs
        for (int wolf: wolfPositions) {
            tileQueue.add(wolf);
            int x = world.intToX(wolf);
            int y = world.intToY(wolf);
            wolfDistance[x][y] = 0;
        }
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = world.intToX(tile);
            int y = world.intToY(tile);
            
            if (distance[x][y] != -1) {
                for (int i = 0; i < 4; ++i) {
                    if ((world.tileExists(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i])) && 
                        (wolfDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] == -1)) {
                        tileQueue.add(world.coordToInt(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i]));
                        wolfDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] = wolfDistance[x][y] + 1;
                    }
                }
            }
        }

        for (int sheep: sheepPositions) {
            tileQueue.add(sheep);
            int x = world.intToX(sheep);
            int y = world.intToY(sheep);
            sheepDistance[x][y] = 0;
        }
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = world.intToX(tile);
            int y = world.intToY(tile);
            
            if (distance[x][y] != -1) {
                for (int i = 0; i < 4; ++i) {
                    if ((world.tileExists(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i])) && 
                        (sheepDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] == -1)) {
                        tileQueue.add(world.coordToInt(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i]));
                        sheepDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] = sheepDistance[x][y] + 1;
                    }
                }
            }
        }

        
        for (int grass: grassPositions) {
            tileQueue.add(grass);
            int x = world.intToX(grass);
            int y = world.intToY(grass);
            grassDistance[x][y] = 0;
        }
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = world.intToX(tile);
            int y = world.intToY(tile);
            
            if (distance[x][y] != -1) {
                for (int i = 0; i < 4; ++i) {
                    if ((world.tileExists(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i])) && 
                        (grassDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] == -1)) {
                        tileQueue.add(world.coordToInt(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i]));
                        grassDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] = grassDistance[x][y] + 1;
                    }
                }
            }
        }

        int minValue = sheepDistance[getX()][getY()];
        int direction = 0;
        for (int i = 0; i < 4; ++i) {
            if (world.tileExists(getX() + Entity.X_MOVES[i], getY() + Entity.Y_MOVES[i])) {
                //Heuristic: Math.min(s(), w())
                int x = getX() + Entity.X_MOVES[i];
                int y = getY() + Entity.Y_MOVES[i];
                //Heuristic based on hunger check
                int h = 0;
                if (this.getHealth() > this.hungerThreshold) {
                    h = sheepDistance[x][y];
                } else {
                    h = Math.min(wolfDistance[x][y], sheepDistance[x][y]);
                }
                
                if (h == minValue) {
                    if (Math.random() < 0.5) {
                        direction = i + 1;
                    }
                } else if (h < minValue) {
                    minValue = h;
                    direction = i + 1;
                }
            }
        }
        
        return direction;
    }
}