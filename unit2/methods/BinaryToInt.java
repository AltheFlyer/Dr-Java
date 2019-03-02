import java.util.Scanner;

/**
 * BinaryToInt.java
 * @version 1.0
 * @author Allen Liu
 * @since March 1
 * Converts a binary string to an int
 */
public class BinaryToInt {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String binStr = input.nextLine();
        
        System.out.printf("%s is %d", binStr, toBinaryVal(binStr));
        input.close();
    }
    
    /**
     * toBinaryVal
     * Converts a binary string into its integer equivalent
     * @param s The binary string (should only contain 0s and 1s)
     * @return int The binary equivalent of s
     */
    public static int toBinaryVal(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(s.length() - 1 - i) == '1') {
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }
}