import java.util.Scanner;

/**
 * ACM.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 1, 2019
 * 
 */
public class ACM {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int a = input.nextInt();
        int b = input.nextInt();
        System.out.println(reverseInt(reverseInt(a) + reverseInt(b)));
    }
    
    public static int reverseInt(int x) {
        int out = 0;
        while (x > 0) {
            out *= 10;
            out += x % 10;
            x /= 10;
        }
        return out;
    }
}