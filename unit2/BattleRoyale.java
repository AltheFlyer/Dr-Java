import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * BattleRoyale.java
 * @version 1.0 
 * @author Allen Liu
 * @since March 20, 2019
 */

public class BattleRoyale {
    
    public static void main(String[] args) throws IOException {
        //readFromFile("map.txt");
        char[][] map = readFromFile("map.txt");
        /*{
            {'p', '.', '.'},
            {'.', '.', '1'},
            {'.', '.', '.'}
        };
        */

        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
        
        int cX = map[0].length / 2;
        int cY = map.length / 2;
        System.out.printf("%d, %d\n", cX, cY);
        System.out.println(numLoot(map, 4, cX, cY));
    }
    
    public static int numLoot(char[][] map, int turnsLeft, int x, int y) {
        if (turnsLeft == 0) {
            if (map[y][x] == 'p') {
                /*
                for (int y1 = 0; y1 < 3; ++y1) {
                    for (int x1 = 0; x1 < 3; ++x1) {
                        System.out.print(map[y1][x1]);
                    }
                    System.out.println();
                }
                System.out.println();
                */
                return 0;
                
            }
            return -99999;
        }

        int points = 0;
        
        //Generate map copy
        //System.out.println(turnsLeft);
        
        char[][] copy = new char[map.length][map[0].length];
        for (int y1 = 0; y1 < map.length; ++y1) {
            for (int x1 = 0; x1 < map[0].length; ++x1) {
                copy[y1][x1] = map[y1][x1];
                //System.out.print(copy[y1][x1]);
            }
            //System.out.println();
        }
        
        //System.out.println();

        if (map[y][x] == '1') {
            points = 1;
        }
        
        copy[y][x] = 'v';
        
        //4-way branch:
        int a = -1, b = -1, c = -1, d = -1;
        if (x > 0) {
            a = points + numLoot(copy, turnsLeft-1, x-1, y);
        }
        if (y > 0) {
            b = points + numLoot(copy, turnsLeft-1, x, y-1);
        }
        if (x < map[0].length - 1){
            c = points + numLoot(copy, turnsLeft-1, x+1, y); 
        }
        if (y < map.length - 1) {
            d = points + numLoot(copy, turnsLeft-1, x, y+1);
        }
        
        if (turnsLeft == 5) {
            for (int y1 = 0; y1 < map.length; ++y1) {
                for (int x1 = 0; x1 < map[0].length; ++x1) {
                    System.out.print(copy[y1][x1]);
                }
            System.out.println();
        }
        }
        return Math.max(Math.max(a, b), Math.max(c, d));
    }
   
        
        
    public static char[][] readFromFile(String filename) throws IOException {
        File mapText = new File(filename);
        Scanner in = new Scanner(mapText);
        
        //Find map bounds
        int height = 0;
        int width = 0;
        
        //Scout file
        while (in.hasNextLine()) {
            if (height == 0) {
                String s = in.nextLine();
                while (s.indexOf(' ') > -1) {
                    s = s.substring(s.indexOf(' ') + 1);
                    width++;
                }
                width++;
            } else {
                in.nextLine();   
            }
            height++;
        }
        
        in.close();
        in = new Scanner(mapText);
        
        char[][] outMap = new char[height][width];
        
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                outMap[y][x] = in.next().charAt(0);
            }
        }
        
        in.close();
        return outMap;
    }
    
}