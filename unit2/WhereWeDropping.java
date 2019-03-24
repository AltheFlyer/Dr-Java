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

public class WhereWeDropping {
    
    public static char[][] mainMap;
    
    public static void main(String[] args) throws IOException {
        //readFromFile("map.txt");
        char[][] map = readFromFile("map.txt");
        /*
            {'p', '.', '.'},
            {'.', '.', '1'},
            {'.', '.', '.'}
        };
        */
        char[][] originalMap = new char[map.length][map[0].length];
        int[][] opt = new int[map.length][map[0].length];

        int posX = 0, posY = 0;
      
        
        int cX = map[0].length / 2;
        int cY = map.length / 2;
        map[cY][cX] = 'f';
        
        regenArray(originalMap, map);
        
        int optX = 0;
        int optY = 0;
        int maxLoot = -1;
        
        //System.out.println("123456789".indexOf('.'));
        //Where we droppin:
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                if ("123456789".indexOf(map[y][x]) == -1) {
                    opt[y][x] = numLoot(map, 0, x, y);
                    regenArray(map, originalMap);
                    if (maxLoot < opt[y][x]) {
                        maxLoot = opt[y][x];
                        optX = x;
                        optY = y;
                    }
                } else {
                    //System.out.println(map[y][x]);
                    opt[y][x] = -9999;
                }
            }
            //System.out.println();
        }
        
        
        //System.out.printf("%d, %d\n", posX, posY);
        //int loot = numLoot(map, 0, posX, posY);
        map[optY][optX] = 'p';
        int loot = numLoot(map, 0, optX, optY);
        
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
        System.out.printf("%d item(s) looted.\n", loot);
    }
    
    public static int numLoot(char[][] map, int turns, int x, int y) {
        //The concede code:
        //Check if out of bounds
        if (y > map.length - 1 - turns || y < turns || x > map.length - 1 - turns || x < turns) {
            return -9999;
        }
        //Check if impossible to return
        int cX = map.length / 2;
        if (Math.abs(x - cX) + Math.abs(y - cX) > map.length / 2 - turns) {
            return -9999;
        }
        
        if (turns > map.length / 2) {
            if (map[y][x] == 'f') {
                return 0;
            }
            return -99999;
        }

        int points = 0;
        int tMod = 0;
        
        //System.out.println(turnsLeft);
        
        

        //IN PROGRESS count scores
        if ("123456789".indexOf(map[y][x]) > -1) {
            points = map[y][x] - 48;
            tMod = points - 1;
            if (turns + tMod + 1 > map.length / 2) {
                return -9999;
            }
        }
        /*
        if (map[y][x] == '1') {
            points = 1;
        }
        */
        
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
            a = points + numLoot(aArr, turns+1+tMod, x-1, y);
        }
        //Up
        if (y > 0) {
            b = points + numLoot(bArr, turns+1+tMod, x, y-1);
        }
        //Right
        if (x < map[0].length - 1) {
            c = points + numLoot(cArr, turns+1+tMod, x+1, y); 
        }
        //Down
        if (y < map.length - 1) {
            d = points + numLoot(dArr, turns+1+tMod, x, y+1);
        }
  
        //System.out.printf("Turn %d: [%d, %d]\n", turns, x, y);
        //System.out.printf("%d %d %d %d \n", a, b, c, d);
        
        int max = -1;
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