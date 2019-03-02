/**
 * Selection4.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The fourth selection activity
 */

import java.util.Scanner;

public class Selection4 {
 
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a number from 1-10: >>>");
    int number = input.nextInt();
    System.out.println("Guess the number: >>>");
    if (input.nextInt() == number) {
      System.out.println("you win!");
    } else {
      System.out.println("game over");
    }
  }
}