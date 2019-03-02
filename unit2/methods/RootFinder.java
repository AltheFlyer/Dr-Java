import java.util.Scanner;

/**
 * RootFinder.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 1, 2019
 * Does 90% of gr9 math homework
 */
public class RootFinder {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter a");
        double a = input.nextDouble();
        System.out.println("Enter b");
        double b = input.nextDouble();
        System.out.println("Enter c");
        double c = input.nextDouble();
        
        System.out.printf("The quadratic has %d roots.", numRoots(a, b, c));
        input.close();
    }
    
    /**
     * numRoots
     * Finds the number of distinct real roots to a parabola
     * @param a the value of a, or the coefficient of the leading term
     * @param b the coefficient of the second term
     * @param c the constant
     * @return int, the number of distinct real roots
     */
    public static int numRoots(double a, double b, double c) {
        double det = (b * b) - (4 * a * c);
        if (det == 0) {
            return 1;
        } else if (det > 0) {
            return 2;
        }
        return 0;
    }
}