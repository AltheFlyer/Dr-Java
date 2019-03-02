/**
 * While2.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 13, 2019
 * The second while excercise
 */

import java.util.Scanner;

public class While2 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int random = (int) (Math.random() * 100);
    
    while (input.nextInt() != random) {
      System.out.println("Guess again!");
    }
    System.out.println("You got it!");
  }
}