import java.util.Scanner;

/**
 * FactorialFinder.java
 * @version 1.0
 * @author Allen Liu
 * @since March 1 2019
 * Find the factorial of a number
 */
public class FactorialFinder {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter a number");
        int n = input.nextInt();
        System.out.printf("The factorial of %d is %d.", n, factorial(n));
        input.close();
    }
    
    /**
     * factorial
     * @param n the base of the factorial
     * @return the value of the factorial, of form n!
     */
    public static int factorial(int n) {
        int val = 1;
        for (int i = 1; i <= n; ++i) {
            val *= i;
        }
        return val;
    }
}