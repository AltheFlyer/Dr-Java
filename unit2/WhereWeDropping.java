import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * WhereWeDropping.java
 * @version 4.1 
 * @author Allen Liu
 * @since March 20, 2019
 * Beats the battle royal by finding optimal landing spots and paths (Level 4++)
 */

public class WhereWeDropping {
    
    public static char[][] mainMap;
    
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the filename");
        
        String filename = input.nextLine();
        //filename = "map.txt";
        
        //readFromFile("map.txt");
        char[][] map = readMapFromFile(filename);
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
        //map[cY][cX] = 'f';
        
        copyArray(originalMap, map);
        
        int optX = 0;
        int optY = 0;
        int maxLoot = -1;
        
        //System.out.println("123456789".indexOf('.'));
        //Where we droppin:
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                //Don't run process on impossible maps.
                if (Math.abs(x - cX) + Math.abs(y - cX) > map.length / 2) {
                    opt[y][x] = -9999;
                } else if ("123456789".indexOf(map[y][x]) == -1) {
                    //Find optimal loot for the tile
                    opt[y][x] = numLoot(map, 0, x, y);
                    //Regenerate original map
                    copyArray(map, originalMap);
                    //Set value for maximum loot, save best player positions
                    if (maxLoot < opt[y][x]) {
                        maxLoot = opt[y][x];
                        optX = x;
                        optY = y;
                    }
                } else {
                    //Misc impossible cases
                    opt[y][x] = -9999;
                }
            }
        }
        
        //Set the optimal starting position to the letter 'p'
        map[optY][optX] = 'p';
        
        //Find optimal number for loot, generate map
        int loot = numLoot(map, 0, optX, optY);
        
        //Set center of map to the letter 'f'
        map[cY][cX] = 'f';
        
        //Check if there are any possible solutions (no negative answers)
        if (loot >= 0) {
            printArray2D(map);
            System.out.printf("%d item(s) looted.\n", loot);
        } else {
            System.out.println("No real solutions.");
        }
    }
    
    /**
     * numLoot
     * A recursive method that determines the maximum amount of loot that can be gained,
     * given a map, the number of turns currently passed, and the player position
     * @param map the 2D char array that stores the map with loot locations
     * @param turns the number of turns already taken upon function call
     * @param x the x position of the player (0 &lt;= x &lt; width of the map)
     * @param y the y position of the player (0 &lt;= y &lt; height of the map)
     * @return int - the maximum number of loot that can be gained from the starting position,
     * returns -9999 if it is impossible to return from center
     */
    public static int numLoot(char[][] map, int turns, int x, int y) {
        //The concede code:
        //Check if out of bounds
        if ((y > map.length - 1 - turns) || (y < turns) || (x > map.length - 1 - turns) || (x < turns)) {
            return -9999;
        }
        
        //Check if impossible to return (distance > turns for 'storm' to close in
        int cX = map.length / 2;
        if (Math.abs(x - cX) + Math.abs(y - cX) > (map.length / 2 - turns)) {
            return -9999;
        }
        
        //Check if the number of turns has exceeded the maximum expected number (before storm closes in)
        if (turns > map.length / 2) {
            //If ending point is the center, solution is valid
            if (y == map.length / 2 && x == map.length / 2) {
                if ("123456789".indexOf(map[y][x]) > -1) {
                    return map[y][x] - '0';
                } else {
                    return 0;
                }
            }
            //If ending point is not in center, solution is invalid
            return -99999;
        }

        //Value for points earned by the tile
        int points = 0;
        //Value for the time taken by the tile (always >= 1)
        int tMod = 1;

        //Get loot if tile has a numeric value
        if ("123456789".indexOf(map[y][x]) > -1) {
            //Set the value of current points earned, and turns taken
            points = map[y][x] - '0';
            tMod = points;
            //If collecting loot would get you out of bounds, kills path
            if (turns + tMod > (map.length / 2)) {
                return -9999;
            }
        }
        
        //Set path marker ('v'), if tile is not the start or end
        if ((map[y][x] != 'p') && (map[y][x] != 'f')) {
            map[y][x] = 'v';
        }

        //Arrays for each direction
        char[][] left = new char[map.length][map.length];
        char[][] up = new char[map.length][map.length];
        char[][] right = new char[map.length][map.length];
        char[][] down = new char[map.length][map.length];
        
        //Duplicate the map, one for each path
        copyArray(left, map);
        copyArray(up, map);
        copyArray(right, map);
        copyArray(down, map);
        
        //4-way branch:
        int a = -1, b = -1, c = -1, d = -1;
        //Left
        if (x > 0) {
            a = points + numLoot(left, turns+tMod, x-1, y);
        }
        //Up
        if (y > 0) {
            b = points + numLoot(up, turns+tMod, x, y-1);
        }
        //Right
        if (x < map[0].length - 1) {
            c = points + numLoot(right, turns+tMod, x+1, y); 
        }
        //Down
        if (y < map.length - 1) {
            d = points + numLoot(down, turns+tMod, x, y+1);
        }
  
        /*
         * Determine which direction will get the most loot
         * The map will be set to the map/path of the optimal path
         * The second last step will find the optimal last step,
         * The third lest step will find the best second last step, and so on...
         */
        int max = -1;
        if (max < a) {
            copyArray(map, left);
            max = a;
        }
        if (max < b) {
            copyArray(map, up);
            max = b;
        }
        if (max < c) {
            copyArray(map, right);
            max = c;
        }
        if (max < d) {
            copyArray(map, down);
            max = d;
        }
        
        if (map[y][x] != 'p' && map[y][x] != 'f') {
            map[y][x] = 'v';
        }
        
        //If center of map, we can stop moving, and see if it we can end, or keep moving
        if ((y == map.length / 2) && (x == map[0].length / 2)) {
            //Find if more loot/points can be earned
            return Math.max(points, Math.max(Math.max(a, b), Math.max(c, d)));
        }
        
        return Math.max(Math.max(a, b), Math.max(c, d));
    }
   
    /**
     * [readMapFromFile]
     * Reads from a file with a valid map, and returns the map found
     * @param filename the string for the name of the file to read from
     * @return char[][] - a 2D array representing the map
     * @throws IOException
     */
    public static char[][] readMapFromFile(String filename) throws IOException {
        File mapText = new File(filename);
        Scanner in = new Scanner(mapText);
        
        //Find map bounds
        int size = 0;
        
        String first = "";
        String s = "";
        
        //Scout file, since maps are sqaure.
        if (in.hasNextLine()) {
            s = in.nextLine();
            first = s;
            while (s.indexOf(' ') > -1) {
                s = s.substring(s.indexOf(' ') + 1);
                size++;
            }
            size++;
        }
        
        //in.close();
        //in = new Scanner(mapText);
        
        char[][] outMap = new char[size][size];
        
        //Add first row of map
        for (int i = 0; i < size; ++i) {
            outMap[0][i] = first.charAt(i * 2);
        }
        
        //Add the rest of the map
        for (int y = 1; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                outMap[y][x] = in.next().charAt(0);
            }
        }
        
        in.close();
        return outMap;
    }
    
    /**
     * [copyArray]
     * Copies the values from one array to another without creating references
     * @param a the 2D character array to be copied to
     * @param b the 2D character array to copy values from
     */
    public static void copyArray(char[][] a, char[][] b) {
        for (int y = 0; y < a.length; ++y) {
            for (int x = 0; x < a[0].length; ++x) {
                a[y][x] = b[y][x];
            }
        }
    }
    
    /**
     * [printArray2D]
     * Prints out a 2D array (map) to console.
     * @param arr the 2D array to print to console
     */
    public static void printArray2D(char[][] arr) {
        for (int y = 0; y < arr.length; ++y) {
            for (int x = 0; x < arr[0].length; ++x) {
                System.out.print(arr[y][x]);
            }
            System.out.println();
        }
    }
}