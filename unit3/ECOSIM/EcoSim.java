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
        
        System.out.println("Enter the plant spawn chance (decimal)");
        double pSpawn = input.nextDouble();
        System.out.println("Enter the sheep spawn chance (decimal)");
        double sSpawn = input.nextDouble();
        System.out.println("Enter the wolf spawn chance (decimal)");
        double wSpawn = input.nextDouble();
        
        System.out.println("Enter the plant growth rate");
        double plantRate = input.nextDouble();
        
        World map = new World(50, 50, pSpawn, sSpawn, wSpawn, plantRate);
        
        //0.5, 0.3, 0.04, 0.05
        //World map = new World();
        //System.out.println(((Sheep)(map.getEntityAt(0, 0))).getHeuristicTile());
                           
        //World map = new World(50, 50, pSpawn, sSpawn, wSpawn, plantRate);
        
        //String[][] stringMap = map.getStringArray();
       
        //Set up Grid Panel
        //DisplayGrid grid = new DisplayGrid(map);
        WindowThread windows = new WindowThread(map, 30);
        
        MapThread thread = new MapThread(map, 1000, windows);
        
        thread.start();
        windows.start();
        
    
    }
}