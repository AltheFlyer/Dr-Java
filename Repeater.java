/**
 * Repeater.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 5, 2019
 * Repeats the user's console input
 */

import java.util.Scanner;

public class Repeater {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    //Prints out the user input using the .nextLine() method
    System.out.println(input.nextLine());
  }
}