import java.util.Scanner;

/**
 * LineFinder.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 1, 2019
 * Does 90% of gr9 math homework
 */
public class LineFinder {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        double x1, y1, x2, y2;
        System.out.println("Enter two points");
        x1 = input.nextDouble();
        y1 = input.nextDouble();
        x2 = input.nextDouble();
        y2 = input.nextDouble();
        
        System.out.printf("The length of the line is %f, and the slope is %f.", getLength(x1, y1, x2, y2), getSlope(x1, y1, x2, y2));
        input.close();
    }
    
    /**
     * getLength
     * Gets the distance between two points
     * @param x1 the x-coordinate of the first point
     * @param y1 the y-coordinate of the first point
     * @param y1 the x-coordinate of the second point
     * @param y2 the y-coordinate of the second point
     * @return double, the distance between the two points
     */
    public static double getLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
    
    /**
     * getSlope
     * Gets the  slope of a line given two points
     * @param x1 the x-coordinate of the first point
     * @param y1 the y-coordinate of the first point
     * @param y1 the x-coordinate of the second point
     * @param y2 the y-coordinate of the second point
     * @return double, the slope of the line, returns Double.MAX_VALUE if a vertical line is used
     */
    public static double getSlope(double x1, double y1, double x2, double y2) {
        if (x1 == x2) {
            return Double.MAX_VALUE;
        } else {
            return (y2 - y1) / (x2 - x1);
        }
    }
}