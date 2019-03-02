import java.util.Scanner;

/**
 * GreatestCommon.java
 * @version 1.0
 * @author Allen Liu
 * @since March 1 2019
 * Find the gcf of two numbers
 */
public class GreatestCommon {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int first, second;
        System.out.println("Enter two numbers:");
        first = input.nextInt();
        second = input.nextInt();
        System.out.printf("The gcf of %d and %d is %d.", first, second, gcf(first, second));
        input.close();
    }
    
    /**
     * gcf
     * Finds the greatest common factor of two integers
     * @param m One of the integers
     * @param n One of the integers
     * @return int The gcf of the two integers
     */
    public static int gcf(int m, int n) {
        int greatest = 1;
        for (int i = Math.max(m,n); i > 1; --i) {
            if (m % i == 0 && n % i == 0) {
                return i;
            }
        }
        return greatest;
    }
}