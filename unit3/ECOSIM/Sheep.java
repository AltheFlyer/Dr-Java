import java.util.ArrayList;

import java.awt.Color;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

/**
 * [Sheep.java]
 * @version 3.0
 * @author Allen Liu
 * @since May 1, 2019
 * A class for sheep entities, who will try to group with other sheep and seek out grass,
 * while avoiding wolves
 */
public class Sheep extends Entity {
    
    private Color woolColor;
    private int minBreedingAge = 5;
    
    //Graphics
    private static BufferedImage whiteSheepSprite;
    private static BufferedImage blackSheepSprite;
    
    /**
     * Creates a sheep with a set position and gender, but random genetics
     * @param x the x position of the sheep
     * @param y the y position of the sheep
     * @param w the this.getWorld() that the sheep is in
     * @param gender the gender of the sheep as a dna string
     */
    public Sheep(int x, int y, World w, String gender) {
        this(x, y, w, gender + Genetics.generateRandomDNA(10), gender + Genetics.generateRandomDNA(10));
        this.decompilePhenotype();
    }
    
    /**
     * Creates a sheep with a set position and genome
     * @param x the x position of the sheep
     * @param y the y position of the sheep
     * @param w the this.getWorld() that the sheep is in
     * @param pheno the phenotype of the sheep
     * @param geno the genotype of the sheep
     */
    public Sheep(int x, int y, World w, String pheno, String geno) {
        super(x, y, w, pheno, geno);
        this.decompilePhenotype();
    }
    
    /**
     * Creates a sheep with a set genome but unknown position
     * @param w the this.getWorld() that the sheep is in
     * @param pheno the phenotype of the sheep
     * @param geno the genotype of the sheep
     */
    public Sheep(World w, String pheno, String geno) {
        super(0, 0, w, pheno, geno);
        this.decompilePhenotype();
    }
    
    /** 
     * [decompilePhenotype]
     * converts the phenotype of the entity into entity attributes.<br>
     * Phenotype encoding as follows:<br>
     * Base 0-5 (6) for gender<br>
     * Base 6 for vision addition (+ 4)<br>
     * Base 7-9 for max age addition (+ 50)<br>
     * Base 10-12 for max health addition (+ 50)<br>
     * Base 13-14 for intelligence (/ 2, + 3)<br>
     * Base 15 for wool colour (A/C is black, T/G is white)
     */
    private void decompilePhenotype() {
        //Same encoding for first 15 characters
        super.decompilePhenotype(2, 50, 30, 7);
        
        //Additional wool color
        int color = Genetics.basePairToInt(this.getPhenotype().substring(15));
        if (color <= 1) {
            this.woolColor = Color.BLACK;
        } else {
            this.woolColor = Color.WHITE;
        }
    }
    
    /**
     * [move]
     * declares what movement will be made by the entity with an int, random movement is used at
     * a rate based on intelligence (less common with higher intelligence) and heuristic movement
     * is used otherwise
     * @return int, a movement integer:<br>
     * 0 is no move<br>
     * 1 is up<br>
     * 2 is right<br>
     * 3 is down<br>
     * 4 is left
     */
    public int move() {
        //Random movement
        if (Math.random() < (1.0 / this.getIntelligence())) {
            return randint(0, 5);
        }
       
        //Heuristic movement otherwise
        return this.getHeuristicTile();
    }
    
    /**
     * [interact]
     * Checks to see if this entity can interact with another entity, and sees if
     * movement can be made after the interaction.
     * Interactions are as follows:<br>
     * Sheep will mate with sheep of the opposite gender if health values allow for it<br>
     * Sheep will consume grass whenever possible<br>
     * Sheep will not move onto wolves otherwise.
     * @param e the entity to make an interaction with
     * @return boolean whether movement is made after the interaction
     */
    public boolean interact(Entity e) {
        if (e instanceof Sheep) {
            //Mating, check for opposite gender, and health threshold
            if ((this.isMale() != e.isMale()) && 
                (this.getHealth() > 20) && 
                (e.getHealth() > 20) &&
                (this.getAge() > this.minBreedingAge) &&
                (e.getAge() > this.minBreedingAge)) {
                
                //Generate a random gender for the new sheep
                String genderMod = Genetics.getRandomGender();
                
                //Create the new sheep's DNA
                String[] newDNA = Genetics.generateOrganism(this.getPhenotype().substring(6),
                                                            this.getGenotype().substring(6),
                                                            e.getPhenotype().substring(6),
                                                            e.getGenotype().substring(6));
                
                //Add gender to the phenotype and genotypes
                newDNA[0] = genderMod + newDNA[0];
                newDNA[1] = genderMod + newDNA[1];
                
                //Add the entity to the this.getWorld()
                this.getWorld().addEntity(new Sheep(this.getWorld(), newDNA[0], newDNA[1]),
                                     this.getX(), this.getY(), 1);
                
                //Reduce the health of both entities
                this.modHealth(-10);
                e.modHealth(-10); 
            }
            
            //No movement occurs regardless
            return false;
        } else if (e instanceof Grass) {
            //Increase sheep's health by grass's health
            this.modHealth(e.getHealth());

            //The sheep's movement will remove the grass tile anyways, but in case of shenanigans...
            e.modHealth(-e.getHealth());
            return true;
        }
        //If theres a wolf or something else, we block movement
        return false;
    }
    
    /**
     * [tick]
     * performs end/start of turn actions, should run once between each turn,
     * removing 1 health from the sheep and restoring the sheep's turn
     */
    public void tick() {
        this.modHealth(-1);
        this.refreshMove();
        //Ages the entity and checks for death
        super.tick();
    }
    
    /**
     * [getEntityType]
     * gets an identifier of the entity as a capitalized string
     * @return String "Sheep"
     */
    public String getEntityType() {
        return "Sheep";
    }
    
    /** 
     * [getHeuristicTile]
     * Finds the best tile to move to based on positions of other entities within this entity's vision range.
     * Heuristic is based on moving to the nearest sheep or grass and/or away from the nearest wolf
     * <br><br>
     * Process description:
     * Start by doing bfs with a range based on the entity's range, save distance in an array. 
     * Save positions of all other entities within that range. 
     * Run bfs based on each entity type, generating values for distance from the given entity type. 
     * The secondary bfs stays within the range as the original distance array determines which tiles are valid. 
     * Once the distances have been found use a heuristic.
     * to determine the best tile to move to.
     * @return int, a movement integer:<br>
     * 0 is no move<br>
     * 1 is up<br>
     * 2 is right<br>
     * 3 is down<br>
     * 4 is left
     */
    public int getHeuristicTile() {
        //Distance arrays:
        //Used to get all tiles in vision range
        int[][] distance = new int[this.getWorld().getWidth()][this.getWorld().getHeight()];
        
        //Used to get the distance to the closest wolf for all tiles in range
        int[][] wolfDistance = new int[this.getWorld().getWidth()][this.getWorld().getHeight()];
        
        //Used to get the distance to the closest sheep for all tiles in range
        int[][] sheepDistance = new int[this.getWorld().getWidth()][this.getWorld().getHeight()];
        
        //Used to get the distance to the closest grass for all tiles in range
        int[][] grassDistance = new int[this.getWorld().getWidth()][this.getWorld().getHeight()];
        
        //Get position for simplicity
        int currentX = this.getX();
        int currentY = this.getY();
        
        //Prefill arrays with -1 (unexplored)
        for (int i = 0; i < this.getWorld().getWidth(); ++i) {
            for (int j = 0; j < this.getWorld().getHeight(); ++j) {
                distance[i][j] = -1;
                wolfDistance[i][j] = -1;
                sheepDistance[i][j] = -1;
                grassDistance[i][j] = -1;
            }
        }
        
        
        //Location arrays
        ArrayList<Integer> wolfPositions = new ArrayList<Integer>();
        ArrayList<Integer> sheepPositions = new ArrayList<Integer>();
        ArrayList<Integer> grassPositions = new ArrayList<Integer>();

        //Scout to find all entities in range
        ArrayList<Integer> tileQueue = new ArrayList<Integer>(); 
        tileQueue.add(this.getWorld().coordToInt(currentX, currentY));
        distance[currentX][currentY] = 0;
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = this.getWorld().intToX(tile);
            int y = this.getWorld().intToY(tile);
            
            if (this.getWorld().getEntityAt(x, y) instanceof Wolf) {
                wolfPositions.add(tile);
            } else if (this.getWorld().getEntityAt(x, y) instanceof Sheep) {
                sheepPositions.add(tile);
            } else if (this.getWorld().getEntityAt(x, y) instanceof Grass) {
                grassPositions.add(tile);
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

        
        for (int grass: grassPositions) {
            tileQueue.add(grass);
            int x = this.getWorld().intToX(grass);
            int y = this.getWorld().intToY(grass);
            grassDistance[x][y] = 0;
        }
        
        while (!tileQueue.isEmpty()) {
            int tile = tileQueue.remove(0);
            int x = this.getWorld().intToX(tile);
            int y = this.getWorld().intToY(tile);
            
            if (distance[x][y] != -1) {
                for (int i = 0; i < 4; ++i) {
                    if ((this.getWorld().tileExists(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i])) && 
                        (grassDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] == -1)) {
                        tileQueue.add(this.getWorld().coordToInt(x + Entity.X_MOVES[i], y + Entity.Y_MOVES[i]));
                        grassDistance[x + Entity.X_MOVES[i]][y + Entity.Y_MOVES[i]] = grassDistance[x][y] + 1;
                    }
                }
            }
        }

        /* Heuristic: Go to either sheep or grass (whatever's closest), but run away from wolves.
         * Numeric values are used, where lowest value is preferred
         */
        int closestBenefit = Math.min(sheepDistance[currentX][currentY] + 1, grassDistance[currentX][currentY]);
        int closestDanger = (Math.max(0, wolfDistance[currentX][currentY]));
        int minValue =  closestBenefit - closestDanger;
        
        //The optimal direction to move in
        int direction = 0;
        //Check heuristic value for 4 adjacent tiles
        for (int i = 0; i < 4; ++i) {
            if (this.getWorld().tileExists(this.getX() + Entity.X_MOVES[i], this.getY() + Entity.Y_MOVES[i])) {
                
                int x = this.getX() + Entity.X_MOVES[i];
                int y = this.getY() + Entity.Y_MOVES[i];
                //Create heuristic value for a tile
                closestBenefit = Math.min(sheepDistance[x][y] + 1, grassDistance[x][y]);
                closestDanger = Math.max(0, wolfDistance[x][y]);
                int heuristic = closestBenefit - closestDanger;  
                
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
     * [getWoolColor]
     * gets the sheep's wool color
     * @return Color, the color of the sheep's wool
     */
    public Color getWoolColor() {
        return this.woolColor;
    }
    
    /**
     * [setSprite]
     * Initializes the static images to be used for black and white sheep.
     * Should be called before any graphics are used.
     */
    public static void setSprite() {
        try {
            whiteSheepSprite = ImageIO.read(new File("sheep.png"));
            blackSheepSprite = ImageIO.read(new File("black_sheep.png"));
        } catch (IOException e) {
            System.out.println("Image loading error, please re-download");
        }   
    }
    
    /**
     * [getSprite]
     * gets the sprite of the sheep to use for GUIs, the sheep will
     * either have white or black wool
     * @return BufferedImage, and image of a sheep with the appropriate wool colour
     */
    public BufferedImage getSprite() {
        if (this.woolColor == Color.WHITE) {
            return Sheep.whiteSheepSprite;
        } else {
            return Sheep.blackSheepSprite;
        }
    }
}
