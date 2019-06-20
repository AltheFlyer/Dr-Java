import java.util.Scanner;

/**
 * [Ecosim.java]
 * @version 3.0
 * @author Allen Liu
 * @since May 1, 2019
 * An ecosystem simulator with sheep, grass, and wolves<br>
 * Additional Features/Modifications:<br>
 * - Sheep and Wolves can mate, and produce offspring in a nearby tile (if possible)<br>
 * - Wolves no longer guaranteed to fight each other, as some can mate<br>
 * - Genetics are implemented for grass, sheep and wolves<br>
 * - Attributes such as gender, vision/entity detection range, intelligence (chance to use inoptimal movement)<br>
 * wool color, maximum health, and maximum age are controlled through simplified genes<br>
 * - The User can click inside during the ecosim to track an entity, up until death<br>
 */
public class EcoSim {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String command;
        int gridWidth, gridHeight;
        double plantSpawn, sheepSpawn, wolfSpawn;
        double plantRate;
        long turnDelay;
            
        System.out.println("Would you like the default configuration? (Y/N)");
        
        command = input.nextLine();
        
        if (command.toLowerCase().equals("y")) {
            gridWidth = 50;
            gridHeight = 50;
            plantSpawn = 0.5;
            sheepSpawn = 0.3;
            wolfSpawn = 0.05;
            plantRate = 0.05;
            turnDelay = 1000;
        } else {
            System.out.println("Enter the world width");
            gridWidth = input.nextInt();
            System.out.println("Enter the world height");
            gridHeight = input.nextInt();
            
            System.out.println("Enter the initial plant spawn chance (decimal)");
            plantSpawn = input.nextDouble();
            System.out.println("Enter the initial sheep spawn chance (decimal)");
            sheepSpawn = input.nextDouble();
            System.out.println("Enter the intial wolf spawn chance (decimal)");
            wolfSpawn = input.nextDouble();
            
            System.out.println("Enter the plant regrowth rate (chance per tile)");
            plantRate = input.nextDouble();
            
            System.out.println("Enter the delay between each turn");
            turnDelay = input.nextLong();
        }
        
        //initialize sprites
        Sheep.setSprite();
        Wolf.setSprite();
        Grass.setSprite();
        
        World map = new World(gridWidth, gridHeight, plantSpawn, sheepSpawn, wolfSpawn, plantRate);
        
        //Set up Grid Panel
        DisplayGrid grid = new DisplayGrid(map);
        
        long lastTurn = System.currentTimeMillis();
        
        //Use a loop without thread.sleep() to allow for responsive window and timed map
        while ((map.getNumSheep() > 0) && (map.getNumWolves() > 0)) {
            //Only update map after the specified delay
            if (System.currentTimeMillis() - lastTurn > turnDelay) {
                map.tick();
                lastTurn = System.currentTimeMillis();
                grid.incrementTurn();
            }
            
            grid.refresh();
        }
    
        //Extinction messages
        if (map.getNumSheep() <= 0) {
            System.out.println("Sheep Extinction");
            grid.endSimulation("Sheep Extinction");
        } else if (map.getNumWolves() <= 0) {
            System.out.println("Wolf Extinction");
            grid.endSimulation("Wolf Extinction");
        }
    }
}