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
    
    public static char[][] mainMap;
    
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
<<<<<<< HEAD
        map[cY][cX] = 'f';
        
        System.out.printf("%d, %d\n", posX, posY);
        System.out.println(numLoot(map, 0, posX, posY));
        
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                System.out.print(map[y][x]);
                if (map[y][x] == 'p') {
                    posX = x;
                    posY = y;
                }
            }
            System.out.println();
        }
=======
        System.out.printf("%d, %d\n", cX, cY);
        System.out.println(numLoot(map, 4, cX, cY));
>>>>>>> parent of a2820a3... kill me now
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
        
<<<<<<< HEAD
        if (map[y][x] != 'p') {
            map[y][x] = 'v';
            copy[y][x] = 'v';
        }
=======
        copy[y][x] = 'v';
>>>>>>> parent of a2820a3... kill me now
        
        //4-way branch:
        int a = -1, b = -1, c = -1, d = -1;
        if (x > 0) {
            a = points + numLoot(copy, turnsLeft-1, x-1, y);
        }
        if (y > 0) {
            b = points + numLoot(copy, turnsLeft-1, x, y-1);
        }
<<<<<<< HEAD
        if (x < map[0].length - 1) {
            c = points + numLoot(copy, turns+1, x+1, y); 
=======
        if (x < map[0].length - 1){
            c = points + numLoot(copy, turnsLeft-1, x+1, y); 
>>>>>>> parent of a2820a3... kill me now
        }
        if (y < map.length - 1) {
            d = points + numLoot(copy, turnsLeft-1, x, y+1);
        }
        
<<<<<<< HEAD
        //If center of map
        if (y == map.length / 2 && x == map[0].length / 2) {
            //Find if more loot/points can be earned
            return Math.max(0, Math.max(Math.max(a, b), Math.max(c, d)));
=======
        if (turnsLeft == 5) {
            for (int y1 = 0; y1 < map.length; ++y1) {
                for (int x1 = 0; x1 < map[0].length; ++x1) {
                    System.out.print(copy[y1][x1]);
                }
            System.out.println();
        }
>>>>>>> parent of a2820a3... kill me now
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