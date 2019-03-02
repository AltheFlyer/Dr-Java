import java.util.Scanner;

/**
 * IntToBinary.java
 * @version 1.0
 * @author Allen Liu
 * @since March 1
 * Converts an int into a binary string
 */
public class IntToBinary {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int num = input.nextInt();
        
        System.out.printf("The value of %d in binary is %s.", num, toBinaryStr(num));
        input.close();
    }
    
    /**
     * toBinaryStr
     * Converts an integer to it's binary value as a string
     * @param n the number to convert
     * @return String, the binary version of the number
     */
    public static String toBinaryStr(int n) {
        int maxPow = 1;
        String out = "";
        while (maxPow < n) {
            maxPow *= 2;
        }
        for (int i = maxPow; i > 0; i /= 2) {
            if (i <= n) {
                n -= i;
                out += "1";
            } else if (i != maxPow){
                out += "0";
            }
        }
        return out;
    }
}