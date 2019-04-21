import java.util.Scanner;

public class EcoSim {
    
    
    
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
        
        long lastTurn = System.currentTimeMillis();
        
        int i = 0;
        while(true) {
            //Display the grid on a Panel
            grid.refresh();  
            
            if (System.currentTimeMillis() - lastTurn > 1000) {
                
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
                
                map.tick();
                
                lastTurn = System.currentTimeMillis();
            }
            
            //Small delay
            /*
            try { 
                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
            };
            */
            
            
            //stringMap = map.getStringArray();
           
        }
    
    }
}