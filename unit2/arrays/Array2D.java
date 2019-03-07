import java.util.Scanner;

/**
 * Array2D.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 5, 2019
 * The 2D array exercise
 */

public class Array2D {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        char[][] grid = new char[3][3];
        
        String newGame = "";
        
        do {
            for (int x = 0; x < 3; ++x) {
                for (int y = 0; y < 3; ++y) {
                    grid[x][y] = '-';
                }
            }
            
            boolean xTurn = true;
            
            System.out.println("Welome to TICTACTOE");
            System.out.println("Select your move based on the numbers below.");
            System.out.println("1\t2\t3");
            System.out.println("4\t5\t6");
            System.out.println("7\t8\t9");
            
            while (hasLine(grid) == '0' && unoccupied(grid)) {
                if (xTurn) {
                    System.out.print("Player X's turn: ");
                } else {
                    System.out.print("Player O's turn: ");
                }
                
                int tile = input.nextInt();
                tile -= 1;
                if (grid[tile /3][tile %3] == '-') {
                    if (xTurn) {
                        grid[tile /3][tile %3] = 'X';
                    } else {
                        grid[tile /3][tile %3] = 'O';
                    }
                    xTurn = !xTurn;
                } else {
                    System.out.println("That tile is already filled!");
                }
                
                for (int x = 0; x < 3; ++x) {
                    for (int y = 0; y < 3; ++y) {
                        System.out.print(grid[x][y] + "\t");
                    }
                    System.out.println();
                }
            }
            
            if (hasLine(grid) != '0') {
                System.out.printf("Player %s wins!!!\n", hasLine(grid));
            } else {
                System.out.println("Draw, nobody wins!");
            }
            System.out.println("Do you want to play again?");
            newGame = input.next();
        } while (newGame.equals("Y"));
    }
    
    /**
     * hasLine
     * @param arr The tic tac toe grid
     * @return char, whether a line is made with that character or not
     */
    public static char hasLine(char[][] arr) {
        //Go through rows and columns
        int len = arr.length;
        for (int i = 0; i < arr.length; ++i) {
            //Row
            if (gridVal(arr, 1 + i * len) == gridVal(arr, 1 + i * len + 1) && 
                gridVal(arr, 1 + i * len) == gridVal(arr, 1 + i * len + 2) && 
                gridVal(arr, 3 + i * len) != '-') {
                return gridVal(arr, 1 + i * 3);
            }
            //Column
            if (gridVal(arr, 1 + i) == gridVal(arr, 1 + i + len) && 
                gridVal(arr, 1 + i + len) == gridVal(arr, 1 + i + len * 2) && 
                gridVal(arr, 1 + i + len * 2) != '-') {
                return gridVal(arr, 1 + i);
            }
        }
        if (arr[0][0] == arr[1][1] && arr[0][0] == arr[2][2] && arr[0][0] != '-') {
            return arr[0][0];
        }
        if (arr[2][0] == arr[1][1] && arr[2][0] == arr[0][2] && arr[2][0] != '-') {
            return arr[2][0];
        }
        return '0';
    }
    
    /**
     * unoccupied
     * @param arr the tic tac toe grid
     * @return Boolean, whether the grid is has an empty space or not
     */
    public static boolean unoccupied(char[][] arr) {
        for (int x = 0; x < arr.length; ++x) {
            for (int y = 0; y < arr.length; ++y) {
                if (arr[x][y] == '-') {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static char gridVal(char[][] arr, int pos) {
        pos--;
        return arr[pos /3][pos %3];
    }
}