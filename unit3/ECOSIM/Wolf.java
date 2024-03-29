import java.util.ArrayList;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

/**
 * [Wolf.java]
 * @version 3.0
 * @author Allen Liu
 * @since May 1, 2019
 * A class for wolf entities, who will try to group with other wolves and seek out sheep, 
 * they can also trample grass
 */
public class Wolf extends Entity implements Comparable<Wolf> {
    
    private int hungerThreshold = 30;
    private int minBreedingAge = 2;
    
    private static BufferedImage wolfSprite;
    
    /**
     * Creates a wolf in a this.getWorld() with randomized attributes
     * @param w the this.getWorld() that the wolf is on
     */
    public Wolf(World w) {
        this(0, 0, w, Genetics.getRandomGender());
    }
    
    /**
     * Creates a wolf with a set position and gender, but random attributes
     * @param x the x position in the this.getWorld() that the wolf is on
     * @param y the y position in the this.getWorld() that the wolf is on
     * @param w the this.getWorld() that the wolf is in
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
     * @param x the x position in the this.getWorld() that the wolf is on
     * @param y the y position in the this.getWorld() that the wolf is on
     * @param w the this.getWorld() that the wolf is in
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
        int r = this.getHeuristicTile();

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
                if ((this.getHealth() > 20) && (e.getHealth() > 20) &&
                    (this.getAge() <= minBreedingAge) && (e.getAge() <= this.minBreedingAge)) {
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
                    
                    this.getWorld().addEntity(new Wolf(this.getWorld(), newDNA[0], newDNA[1]), getX(), getY(), 1);
                    
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
     * Heuristic is based on moving to the nearest sheep or wolf
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
        //Distance from the source wolf
        int[][] distance = new int[this.getWorld().getWidth()][this.getWorld().getHeight()];
        //Distance grid of wolves and sheep, measuring distance from the nearest one 
        int[][] wolfDistance = new int[this.getWorld().getWidth()][this.getWorld().getHeight()];
        int[][] sheepDistance = new int[this.getWorld().getWidth()][this.getWorld().getHeight()];
        
        int currentX = this.getX();
        int currentY = this.getY();
        
        //Initialize with -1 acting as unvisited values
        for (int i = 0; i < this.getWorld().getWidth(); ++i) {
            for (int j = 0; j < this.getWorld().getHeight(); ++j) {
                distance[i][j] = -1;
                wolfDistance[i][j] = -1;
                sheepDistance[i][j] = -1;
            }
        }
        
        
        //location arrays
        ArrayList<Integer> wolfPositions = new ArrayList<Integer>();
        ArrayList<Integer> sheepPositions = new ArrayList<Integer>();

        int topTile = 0;
        int rightTile = 0;
        int bottomTile = 0;
        int leftTile = 0;
        //Scout to find all entities in range
        ArrayList<Integer> tileQueue = new ArrayList<Integer>(); 
        tileQueue.add(this.getWorld().coordToInt(getX(), getY()));
        distance[currentX][currentY] = 0;
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = this.getWorld().intToX(tile);
            int y = this.getWorld().intToY(tile);
            
            if ((this.getWorld().getEntityAt(x, y) instanceof Wolf)) {
                wolfPositions.add(tile);
            } else if (this.getWorld().getEntityAt(x, y) instanceof Sheep) {
                sheepPositions.add(tile);
            }
            
            if (distance[x][y] < getRange()) {
                for (int i = 0; i < 4; ++i) {
                    if ((this.getWorld().tileExists(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i])) && 
                        (distance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] == -1)) {
                        tileQueue.add(this.getWorld().coordToInt(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i]));
                        distance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] = distance[x][y] + 1;
                    }
                }
            }
        }
       
        
        //Wolf bfs
        for (int wolf: wolfPositions) {
            tileQueue.add(wolf);
            int x = this.getWorld().intToX(wolf);
            int y = this.getWorld().intToY(wolf);
            wolfDistance[x][y] = 0;
        }
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = this.getWorld().intToX(tile);
            int y = this.getWorld().intToY(tile);
            
            if (distance[x][y] != -1) {
                for (int i = 0; i < 4; ++i) {
                    if ((this.getWorld().tileExists(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i])) && 
                        (wolfDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] == -1)) {
                        tileQueue.add(this.getWorld().coordToInt(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i]));
                        wolfDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] = wolfDistance[x][y] + 1;
                    }
                }
            }
        }

        for (int sheep: sheepPositions) {
            tileQueue.add(sheep);
            int x = this.getWorld().intToX(sheep);
            int y = this.getWorld().intToY(sheep);
            sheepDistance[x][y] = 0;
        }
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = this.getWorld().intToX(tile);
            int y = this.getWorld().intToY(tile);
            
            if (distance[x][y] != -1) {
                for (int i = 0; i < 4; ++i) {
                    if ((this.getWorld().tileExists(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i])) && 
                        (sheepDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] == -1)) {
                        tileQueue.add(this.getWorld().coordToInt(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i]));
                        sheepDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] = sheepDistance[x][y] + 1;
                    }
                }
            }
        }

        /* Heuristic: Go to the nearest sheep if hungry.
         * While not hungry, seek either the nearest sheep or nearest wolf (whatever's closest).
         * (Guess wolves go crazy when unfed) - this is the most accurate thing I can make Mr. Mangat,
         * I'm coding this to my dog's whining.
         */
        int minValue;
        
        if (this.getHealth() > this.hungerThreshold) {
            minValue = sheepDistance[currentX][currentY];
        } else {
            minValue = Math.min(wolfDistance[currentX][currentY], sheepDistance[currentX][currentY]);
        }
        int direction = 0;
        for (int i = 0; i < 4; ++i) {
            if (this.getWorld().tileExists(currentX + Entity.X_MOVES[i], currentY + Entity.Y_MOVES[i])) {
                int x = currentX + Entity.X_MOVES[i];
                int y = currentY + Entity.Y_MOVES[i];
                //Heuristic based on hunger check
                int heuristic = 0;
                if (this.getHealth() > this.hungerThreshold) {
                    heuristic = sheepDistance[x][y];
                } else {
                    heuristic = Math.min(wolfDistance[x][y], sheepDistance[x][y]);
                }
                
                //Compare to see if a smaller value is found,
                //If a value is matched, choose randomly between directions
                if (heuristic == minValue) {
                    if (Math.random() < 0.5) {
                        direction = i + 1;
                    }
                } else if (heuristic < minValue) {
                    minValue = heuristic;
                    direction = i + 1;
                }
            }
        }
        
        return direction;
    }
    
    /**
     * [setSprite]
     * Initializes the static image for a wolf.
     * Should be called before any graphics are used.
     */
    public static void setSprite() {
        try {
            wolfSprite = ImageIO.read(new File("wolf.png"));
        } catch (IOException e) {
            System.out.println("Image loading error, please re-download");
        }   
    }
    
    /**
     * [getSprite]
     * gets the sprite of the wolf for GUIs
     * @return BufferedImage, and image of a wolf
     */
    public BufferedImage getSprite() {
        return wolfSprite;
    }
}