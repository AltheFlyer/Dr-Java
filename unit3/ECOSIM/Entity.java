import java.awt.image.BufferedImage;

/**
 * [Entity.java]
 * @version 3.0
 * @author Allen Liu
 * @since April 29, 2019
 * The abstract class for all entities (Sheep, Wolves, Grass), in the ecosim
 */
abstract public class Entity {
    
    //Static movement values
    //Up, right, down, left (NESW)
    public static final int[] X_MOVES = {0, 1, 0, -1};
    public static final int[] Y_MOVES = {-1, 0, 1, 0};
    
    private final String IMAGE_PATH = "";
    
    //Stats
    private int health;
    private int age;
    
    //When an animal is considered an adult
    private int adultThreshold;
    
    //Genetics
    //This value is active, and determines other attributes
    private String phenotype;
    //this value is 'hidden', and is only used during breeding (where parts of it may be used)
    private String genotype;
    
    //These values are dependant on genetics
    //A boolean for whether an entity is male or female
    private boolean isMale;
    //The entity's detection range of other entities
    private int visionRange;
    //The entity's maximum age (it instantly dies if it goes past this)
    private int maxAge;
    //The entity's maximum health (this prevents the 1000 health wolf from existing)
    private int maxHealth;
    //This controls the entity's chance of not using heuristic based movement, 
    //the chance is a reciprocal of intelligence
    private int intelligence;
    
    //World interaction values
    //Positions
    private int x, y;
    //Whether the entity can move or not - should only be false after moving in a single turn
    private boolean canMove;
    
    //The world that the entity is in
    private World world;
    
    public Entity(int x, int y, World w, String pheno, String geno) {
        world = w;
        this.x = x;
        this.y = y;
        canMove = true;
        age = 0;
        health = 29;

        //DNA fixing 
        String[] DNA = Genetics.generateCombinedPairs(pheno, geno);
        phenotype = DNA[0];
        genotype = DNA[1];
    }
    
    public Entity(int x, int y, World w, String gender) {
        this(x, y, w, gender + Genetics.generateRandomDNA(9), gender + Genetics.generateRandomDNA(9));
    }
    
    /**
     * [decompilePhenotype]
     * converts the phenotype of the entity into entity attributes.<br>
     * Default phenotype encoding as follows:<br>
     * Base 0-5 (6) for gender<br>
     * Base 6 (1) for vision addition (+ 4)<br>
     * Base 7-9 (3) for max age addition (+ 50)<br>
     * Base 10-12 (3) for max health addition (+ 50)<br>
     * Base 13-14 (2) for intelligence (/ 2, + 3)
     * @param minRange the minimum sight range for the entity
     * @param minAge the minimum age limit of the entity
     * @param minHealth the minimum health limit of the entity
     * @param minIntelligence the minimum intelligence value of the entity
     */
    public void decompilePhenotype(int minRange, int minAge, int minHealth, int minIntelligence) {
        isMale = Genetics.baseToGender(phenotype.substring(0, 6));
        visionRange = minRange + Genetics.basePairToInt(phenotype.substring(6, 7));
        maxAge = minAge + Genetics.basePairToInt(phenotype.substring(7, 10));
        
        maxHealth = minHealth + Genetics.basePairToInt(phenotype.substring(10, 13));
        
        intelligence = minIntelligence + (Genetics.basePairToInt(phenotype.substring(13, 15)) / 2);
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
        return 0;
    }
    
    /**
     * [interact]
     * controls this entity's interaction with another entity (should there be a movement conflict)
     * @return boolean, whether this entity gets to move to the other entity's postion
     */
    public boolean interact(Entity e) {
        return false;
    }
    
    /**
     * [getHeuristicTile]
     * gets the optimal tile for an entity's, returns 0 by default for the sake of immobile entities
     * @return int the movement to make:<br>
     * 0 is no move<br>
     * 1 is up<br>
     * 2 is right<br>
     * 3 is down<br>
     * 4 is left
     */
    private int getHeuristicTile() {
        return 0;
    }
    
    /**
     * [tick]
     * runs for time based actions or status, such as aging, hunger (taking passive damage)
     */
    public void tick() {
        age++;
        if (age > maxAge) {
            health = 0;
        }
    }
    
    /**
     * [setPos]
     * sets the position of the entity
     * @param x the new x position of the entity
     * @param y the new y
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * getEntityType
     * @return String, the name of the entity as a String (Capitalized first letter)
     */
    abstract public String getEntityType();
    
    /**
     * [randint]
     * Generates a random integer, inclusive of the lower bound, 
     * but exclusive of the higher bound
     * @param low the lower bound of the generator
     * @param the higher bound of the generator, not included in generation
     * @return int, the randomly generated integer
     */
    public static int randint(int low, int high) {
        return (int) (low + Math.random() * (high - low));
    }
    
    /**
     * [getHealth]
     * gets the entity's current health
     * @return int health, the entity's current health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * [modHealth]
     * modifies the health of the given entity without going above the maximum health limit
     * @param modifier the integer amount of health to modify,
     * positive values increase health, 
     * negative values decrease it
     */
    public void modHealth(int modifier) {
        health += modifier;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }
    
    /**
     * [hasMoved]
     * gets whether the entity has made a movei n the current turn
     * @return boolean, whether the entity has moved this turn or not
     */
    public boolean hasMoved() {
        return !canMove;
    }
    
    /**
     * [refreshMove]
     * allows the enemy to move again, intended for use between turns
     */
    public void refreshMove() {
        canMove = true;
    }
    
    /**
     * [exhaustMove]
     * prevents the enemy from moving for the rest of the turn
     */
    public void exhaustMove() {
        canMove = false;
    }
    
    /**
     * [getX]
     * gets the x position of the entity in a world
     * @return int x, the x position of the entity
     */
    public int getX() {
        return x;
    }
    
    /**
     * [getY]
     * gets the y position of the entity in a world
     * @return int y, the y position of the entity
     */
    public int getY() {
        return y;
    }
    
    /**
     * [toString]
     * gets a string representation of the entity's basic stats
     * @return String, a string with health and age of the entity
     */
    public String toString() {
        return String.format("%d/%d health, %d/%d age", health, maxHealth, age, maxAge);
    }
    
    /**
     * [getRange]
     * gets the vision range of the entity (or how far it can detect other entities from)
     * @return int visionRange, the detection range of this entity
     */
    public int getRange() {
        return visionRange;
    }
    
    /**
     * [getIntelligence]
     * gets the entity's intelligence, or the inverse of the chance that the entity will move randomly
     * @return int intelligence, the inverse of how often the entity uses random pathfinding
     */
    public int getIntelligence() {
        return intelligence;
    }
    
    /**
     * [isMale]
     * gets whether the entity is male or not
     * @return boolean isMale, whether the entity is male or not
     */
    public boolean isMale() {
        return isMale;
    }
    
    /**
     * [getPhenotype]
     * gets the entity's phenotype, the dna that affects its attributes
     * @return String phenotype, the entity's phenotype
     */
    public String getPhenotype() {
        return phenotype;
    }
    
    /**
     * [getGenotype]
     * gets the inactive half of the entity's genotype, the part that doesn't
     * affect attributes
     * @return String genotype, the entity's genotype
     */
    public String getGenotype() {
        return genotype;
    }
    
    /**
     * [getMaxHealth]
     * gets the maximum health of the current entity
     * @return int maxHealth, the maximum health of the entity
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    
    /**
     * [getAge]
     * gets the age of the current entity
     * @return int age, the age of the entity
     */
    public int getAge() {
        return age;
    }
    
    /**
     * [setSprite]
     * used to set the sprite(s) of the entity
     */
    static public void setSprite() {
        
    };
    
    /**
     * [getSprite]
     * gets the sprite of the entity to display
     * @return BufferedImage, the sprite of the entity
     */
    abstract public BufferedImage getSprite();
    
    /**
     * [getWorld]
     * gets the current world that the entity is in
     * @return World, the world that the entity is in
     */
    public World getWorld() {
        return this.world;
    }
}