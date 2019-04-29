import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;

/**
 * [Grass.java]
 * @version 1.2
 * @author Allen Liu
 * @since April 26, 2019
 * The class for a tall grass tile, which is edible and interactable by other entities
 */
public class Grass extends Entity {
    
    /**
     * Creates grass at a set position in the world
     * @param x the x position of the grass
     * @param y the y position of the grass
     * @param w the world that the grass is in
     */
    public Grass(int x, int y, World w) {
        super(x, y, w, Genetics.generateRandomDNA(15), Genetics.generateRandomDNA(15));
        this.decompilePhenotype();
        this.modHealth(this.getMaxHealth());
    }
    
    /**
     * [decompilePhenotype]
     * converts the phenotype of the entity into entity attributes.<br>
     * Phenotype encoding as follows:<br>
     * Base 0-5 (6) for padding (junk data)<br>
     * Base 6 (1) for padding (junk data)<br>
     * Base 7-9 (3) for max age addition (+ 50)<br>
     * Base 10-12 (3) for max health addition (+ 50)<br>
     * Base 13-14 (2) for padding (junk data)<br>
     * Let's assume that grass doesn't need pathfinding or intelligence
     * and photosynthesis will do all of the work
     */
    private void decompilePhenotype() {
        super.decompilePhenotype(0, 20, 1, 0);
    }
    
    /**
     * [move]
     * @return int, what movement option should be made, always 0 
     * since grass shouldn't be walking anywhere
     */
    public int move() {
        //Grass can't really move here
        return 0;
    }
    
    /**
     * [interact]
     * @param e the entity to interact with
     * @return boolean, always false since the grass <em>really</em> shouldn't 
     * be moving or interacting anyways
     */
    public boolean interact(Entity e) {
        //The grass doesn't really care about what happens here
        return false;
    }
    
    /**
     * [getEntityType]
     * gets an identifier of the entity as a capitalized string
     * @return String "Grass"
     */
    public String getEntityType() {
        return "Grass";
    }
    
        /**
     * 
     * 
     */
    public BufferedImage getSprite() {
        return null;
    }
}