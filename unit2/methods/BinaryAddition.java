import java.util.Scanner;

/**
 * BinaryAddition.java
 * Adds two binary strings
 * @version 1.0
 * @author Allen Liu
 * @since March 1
 */
public class BinaryAddition {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter two binary numbers as strings");
        String first = input.nextLine();
        String second = input.nextLine();
        
        System.out.printf("%s + %s = %s", first, second, addBinary(first, second));
    }
    
    /**
     * addBinary
     * 
     * 
     * @param x The first binary string to add
     * @param y The second binary string to add
     * @return String The sum of the two arguments as a binary string
     */
    public static String addBinary(String x, String y) {
        String out = "";
        boolean carry = false;
        if (x.length() > y.length()) {
            for (int i = y.length(); i < x.length(); ++i) {
                y = "0" + y;
            }
        } else {
            for (int i = x.length(); i < y.length(); ++i) {
                x = "0" + x;
            }
        }

        System.out.println(x);
        System.out.println(y);
        for (int i = x.length() - 1; i >= 0; --i) {
            System.out.println(out + " " + x.charAt(i) + " " + y.charAt(i) + " " + carry);
            if (carry) {
                if (x.charAt(i) == y.charAt(i)) {
                    out = "1" + out;
                    if (x.charAt(i) == '1') {
                        carry = true;
                    } else {
                        carry = false;
                    }
                } else {
                    out = "0" + out;
                }
            } else {
                if (x.charAt(i) == y.charAt(i)) {
                    out = "0" + out;
                    if (x.charAt(i) == '1') {
                        carry = true;
                    }
                } else {
                   out = "1" + out; 
                   carry = false;
                }
            }
        }
        if (carry) {
            out = "1" + out;
        }

        return out;
    }
}