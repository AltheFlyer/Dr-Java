import java.util.Scanner;

/**
 * Array1.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 5, 2019
 * The first array exercise
 */

public class Array1 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        double[] nums = new double[10];
        
        System.out.println("Enter 10 numbers:");
        for (int i = 0; i < 10; ++i) {
            System.out.println("Enter a number.");
            nums[i] = input.nextDouble();
        }
        
        double average = 0;
        double smallest = Double.MAX_VALUE;
        
        //Since we HAVE to loop through the array
        for (int i = 9; i >= 0; --i) {
            System.out.println(nums[i]);
        }
        for (int i = 0; i < 10; ++i) {
            if (nums[i] < smallest) {
                smallest = nums[i];
            }
            average += nums[i];
        }
        average /= 10;
        System.out.printf("The average is %f, and the smallest number is %f", average, smallest);
    }
}