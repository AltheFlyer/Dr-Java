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

public class BattleRoyaleKillMeCopy {
    
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

        int posX = 0, posY = 0;
        
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
    }
    
    public static int numLoot(char[][] map, int turns, int x, int y) {
        if (turns > map.length / 2) {
            if (map[y][x] == 'f') {
                return 0;
                
            }
            return -99999;
        }

        int points = 0;
        
        //System.out.println(turnsLeft);
        
        //Check if out of bounds
        if (y > map.length - 1 - turns || y < turns || x > map.length - 1 - turns || x < turns) {
            return -9999;
        }

        if (map[y][x] == '1') {
            points = 1;
        }
        
        if (map[y][x] != 'p' && map[y][x] != 'f') {
            map[y][x] = 'v';
        }

        char[][] aArr = copyArray(map);
        char[][] bArr = copyArray(map);
        char[][] cArr = copyArray(map);
        char[][] dArr = copyArray(map);
        
        //4-way branch:
        int a = -1, b = -1, c = -1, d = -1;
        //Left
        if (x > 0) {
            a = points + numLoot(aArr, turns+1, x-1, y);
        }
        //Up
        if (y > 0) {
            b = points + numLoot(bArr, turns+1, x, y-1);
        }
        //Right
        if (x < map[0].length - 1) {
            c = points + numLoot(cArr, turns+1, x+1, y); 
        }
        //Down
        if (y < map.length - 1) {
            d = points + numLoot(dArr, turns+1, x, y+1);
        }
  
        //System.out.printf("Turn %d: [%d, %d]\n", turns, x, y);
        //System.out.printf("%d %d %d %d \n", a, b, c, d);
        
        int max = 0;
        if (max < a) {
            regenArray(map, aArr);
            max = a;
        }
        if (max < b) {
            regenArray(map, bArr);
            max = b;
        }
        if (max < c) {
            regenArray(map, cArr);
            max = c;
        }
        if (max < d) {
            regenArray(map, dArr);
            max = d;
        }
        
        if (map[y][x] != 'p' && map[y][x] != 'f') {
            map[y][x] = 'v';
        }
        
        //if (turns == 0) {
         //printArray2D(map);   
        //}
        //System.out.println(map);
        //If center of map
        if (y == map.length / 2 && x == map[0].length / 2) {
            //Find if more loot/points can be earned
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
    
    public static char[][] copyArray(char[][] arr) {
        char[][] ret = new char[arr.length][arr[0].length];
        for (int y = 0; y < arr.length; ++y) {
            for (int x = 0; x < arr[0].length; ++x) {
                ret[y][x] = arr[y][x];
            }
        }
        return ret;
    }
    
    public static void regenArray(char[][] a, char[][] b) {
        for (int y = 0; y < a.length; ++y) {
            for (int x = 0; x < a[0].length; ++x) {
                a[y][x] = b[y][x];
            }
        }
    }
    
    public static void printArray2D(char[][] arr) {
        for (int y = 0; y < arr.length; ++y) {
            for (int x = 0; x < arr[0].length; ++x) {
                System.out.print(arr[y][x]);
            }
            System.out.println();
        }
    }
}