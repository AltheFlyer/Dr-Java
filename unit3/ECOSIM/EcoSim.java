public class EcoSim {
    
 
    public static void main(String[] args) {
        World map = new World();
        String[][] stringMap = map.getStringArray();
       
        //Set up Grid Panel
        DisplayGrid grid = new DisplayGrid(stringMap);
        
        while(true) {
            map.tick();
            stringMap = map.getStringArray();
            
            /*
            for (int y = 0; y < stringMap.length; ++y) {
                for (int x = 0; x < stringMap[0].length; ++x) {
                    if (map.hasEntity(x, y)) {
                        System.out.print(map.map[y][x].getHealth());
                    } else {
                        System.out.print("N");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            */
            
            
            //Display the grid on a Panel
            grid.refresh(stringMap);            
            
            //Small delay
            try { 
                Thread.sleep(1000);
            } catch(Exception e) {
                
            };
            
        }
    }
}