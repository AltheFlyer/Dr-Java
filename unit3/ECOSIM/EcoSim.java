import java.util.Scanner;

public class EcoSim {
    
    /* MOST STABLE:
     * 280 Turns, 0.4, 0.4, 0.01, 0.01
     * 295 Truns, 0.4, 0.1, 0.01, 0.01
     * 398 Turns, 0.5, 0.1, 0.01, 0.01
     * No Wolf Hunger
     * 0.5, 0.1, 0.01, 0.01
     */
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //for (int i = 0; i < 32; ++i) {
        //System.out.println(Genetics.intToBasePair(i, 4));
        //}
        
        System.out.println("Enter the plant spawn chance (decimal)");
        double pSpawn = input.nextDouble();
        System.out.println("Enter the sheep spawn chance (decimal)");
        double sSpawn = input.nextDouble();
        System.out.println("Enter the wolf spawn chance (decimal)");
        double wSpawn = input.nextDouble();
        
        System.out.println("Enter the plant growth rate");
        double plantRate = input.nextDouble();
        
        //World map = new World(25, 25, pSpawn, sSpawn, wSpawn, plantRate);
        
        World map = new World(25, 25, pSpawn, sSpawn, wSpawn, plantRate);
        
        String[][] stringMap = map.getStringArray();
       
        //Set up Grid Panel
        DisplayGrid grid = new DisplayGrid(map);
        
        int i = 0;
        while(true) {
            //Display the grid on a Panel
            grid.refresh();  
            i++;
            
            //System.out.printf("Turn %d: %d, %d, %d\n", i,  map.getNumGrass(), map.getNumSheep(), map.getNumWolves());
            
            if (map.getNumSheep() <= 0) {
                System.out.printf("Turn %d: %d, %d, %d\n", i,  map.getNumGrass(), map.getNumSheep(), map.getNumWolves());
            
                System.out.println("Sheep Extinction");
                return;
            }
            
            if (map.getNumWolves() <= 0) {
                System.out.printf("Turn %d: %d, %d, %d\n", i,  map.getNumGrass(), map.getNumSheep(), map.getNumWolves());
            
                System.out.println("Wolf Extinction");
                return;
            }
            
            //Small delay
            try { 
                Thread.sleep(100);
            } catch(Exception e) {
                e.printStackTrace();
            };
            
            map.tick();
            //stringMap = map.getStringArray();
           
        }
    
    }
}