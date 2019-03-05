import java.util.Scanner;

/**
 * Array2.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 5, 2019
 * The second array exercise
 */

public class Array2 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter 3 words:");
        String[] words = new String[3];
        boolean[] guessed = new boolean[3];
        
        for (int i = 0; i < 3; ++i) {
            words[i] = input.next();
            guessed[i] = false;
        }
        
        System.out.println("You should now guess the words");
        
        while (hasFalse(guessed)) {
            String guess = input.next();
            for (int i = 0; i < words.length; ++i) {
                if (guess.equals(words[i])) {
                    guessed[i] = true;
                    System.out.println("You got a word!");
                }
            }
        }
        System.out.println("Good job!");
    }
    
    public static boolean hasFalse(boolean[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            if (!arr[i]) {
                return true;
            }
        }
        return false;
    }
}