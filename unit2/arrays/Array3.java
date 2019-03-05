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
        
        System.out.println("Enter the number of ints:");
        int n = input.nextInt();
        int p = 0;
        
        int numPrimes = 0;
        
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = input.nextInt();
            if (isPrime(nums[i])) {
                numPrimes++;
            }
        }
        int primes[] = new int[numPrimes];
        int primeIndex = 0;
        
        for (int i = 0; i < n; ++i) {
            if (isPrime(nums[i])) {
                primes[primeIndex] = nums[i];
                primeIndex++;
            }
        }
        
        for (int i = 0; i < numPrimes; ++i) {
            System.out.println(primes[i]);
        }
    }
    
    
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
}