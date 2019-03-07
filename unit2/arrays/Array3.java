import java.util.Scanner;

/**
 * Array3.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 5, 2019
 * The third array exercise
 */
public class Array3 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[] nums = new int[0];
        int primes[] = new int[0];
        
        int numberInput = 0;
        
        System.out.println("Enter in numbers. Type a negative number to stop");
        while (numberInput >= 0) {
            numberInput = input.nextInt();
            if (numberInput >= 0) {
                nums = addToArray(nums, numberInput);
            }
        }
        
        //Count the number of primes given
        for (int i = 0; i < nums.length; ++i) {
            if (isPrime(nums[i])) {
                primes = addToArray(primes, nums[i]);
            }
        }
        
        for (int i = 0; i < primes.length; ++i) {
            System.out.println(primes[i]);
        }
    }
    
    /**
     * isPrime
     * @param p The integer to check
     * @return Boolean, whether the integer is prime or not
     */
    public static boolean isPrime(int p) {
        if (p == 0 || p == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(p); ++i) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * addToArray
     * @param arr The array of integers
     * @param val The value to add to the array
     * @return int[], The array of integers, with the value appended to it
     */
    public static int[] addToArray(int[] arr, int val) {
        int[] out = new int[arr.length + 1];
        for (int i = 0; i < arr.length; ++i) {
            out[i] = arr[i];
        }
        out[arr.length] = val;
        return out;
    }
}