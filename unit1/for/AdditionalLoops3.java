/**
 * AdditionalLoops3.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 14, 2019
 * the third additional excercise
 */

import java.util.Scanner;

public class AdditionalLoops3 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    String word = input.nextLine();
    int number = input.nextInt();
    
    for (int i = 0; i < word.length(); ++i) {
      for (int j = 0; j < number; ++j) {
        System.out.print(word.charAt(i));
      }
      System.out.print(" ");
    }
    
  }
}