import java.util.Scanner;

/**
 * HypotenuseFinder.java
 * @version 1.0
 * @author Allen Liu
 * @since March 1 2019
 * Find the hypotenuse
 */
public class HypotenuseFinder {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the base and height of the triangle");
        double base = input.nextDouble();
        double height = input.nextDouble();
        
        double hypotenuse = getHypotenuse(base, height);
        System.out.println(hypotenuse);
        input.close();
    }
    
    /**
     * getHypotenuse
     * @param a one side of the triangle
     * @param b one side of the triangle
     * @return double, the hypotenuse
     */
    public static double getHypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }
}