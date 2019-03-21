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
        
        int posX = 0;
        int posY = 0;
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
        
        int cX = map[0].length / 2;
        int cY = map.length / 2;
        map[cY][cX] = 'f';
        
        System.out.printf("%d, %d\n", posX, posY);
        System.out.println(numLoot(map, 0, posX, posY));
    }
    
    public static int numLoot(char[][] map, int turns, int x, int y) {
        if (turns > map.length / 2) {
            if (map[y][x] == 'f') {
                return 0;
            }
            //Invalid ending
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

        //Check if out of bounds:
        if (y > map.length - 1 - turns || y < turns || x > map.length - 1 - turns || x < turns) {
            return -9999;
        }
        
        if (map[y][x] == '1') {
            points = 1;
        }
        
        //copy[y][x] = 'v';
        
        //4-way branch:
        int a = -1, b = -1, c = -1, d = -1;
        //System.out.println("Possible valid: " + x + ", " + y);
        if (x > 0) {
            a = points + numLoot(copy, turns+1, x-1, y);
        }
        if (y > 0) {
            b = points + numLoot(copy, turns+1, x, y-1);
        }
        if (x < map[0].length - 1){
            c = points + numLoot(copy, turns+1, x+1, y); 
        }
        if (y < map.length - 1) {
            d = points + numLoot(copy, turns+1, x, y+1);
        }
        
        if (copy[y][x] == 'f') {
            return Math.max(0, Math.max(Math.max(a, b), Math.max(c, d)));
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