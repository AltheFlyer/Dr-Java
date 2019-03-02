/**
 * Selection8.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 11, 2019
 * The eigth selection activity
 */

import java.util.Scanner;

public class Selection8 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("Enter a number from 1-3");
    int hellos = input.nextInt();
    if (hellos >= 3) {
      System.out.println("hello");
    }
    if (hellos >= 2) {
      System.out.println("hello");
    }
    if (hellos >= 1) {
      System.out.println("hello");
    }
  }
}