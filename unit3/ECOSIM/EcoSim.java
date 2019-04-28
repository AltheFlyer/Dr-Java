import java.util.Scanner;

/**
 * [Ecosim.java]
 * @version 1.4
 * @author Allen Liu
 * @since April 26, 2019
 * An ecosystem simulator with sheep, grass, and wolves including
 * genetics and pathfinding
 */
public class EcoSim {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String command;
        int gridWidth, gridHeight;
        double plantSpawn, sheepSpawn, wolfSpawn;
        double plantRate;
        double framesPerSecond;
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
            framesPerSecond = 30;
            turnDelay = 1000;
        } else {
            System.out.println("Enter the world width");
            gridWidth = input.nextInt();
            System.out.println("Enter the world height");
            gridHeight = input.nextInt();
            
            System.out.println("Enter the plant spawn chance (decimal)");
            plantSpawn = input.nextDouble();
            System.out.println("Enter the sheep spawn chance (decimal)");
            sheepSpawn = input.nextDouble();
            System.out.println("Enter the wolf spawn chance (decimal)");
            wolfSpawn = input.nextDouble();
            
            System.out.println("Enter the plant growth rate");
            plantRate = input.nextDouble();
            
            System.out.println("Enter your preferred frame rate (frames/second)");
            framesPerSecond = input.nextDouble();
            
            System.out.println("Enter your time between turns (milliseconds)");
            turnDelay = input.nextLong();
        }
        
        World map = new World(gridWidth, gridHeight, plantSpawn, sheepSpawn, wolfSpawn, plantRate);
        
        //0.5, 0.3, 0.04, 0.05
        //World map = new World();
        //System.out.println(((Sheep)(map.getEntityAt(0, 0))).getHeuristicTile());
                           
        //World map = new World(50, 50, pSpawn, sSpawn, wSpawn, plantRate);
        
        //String[][] stringMap = map.getStringArray();
       
        //Set up Grid Panel
        //DisplayGrid grid = new DisplayGrid(map);
        WindowThread windows = new WindowThread(map, framesPerSecond);
        
        MapThread thread = new MapThread(map, turnDelay, windows);
        
        thread.start();
        windows.start();
    
    }
}