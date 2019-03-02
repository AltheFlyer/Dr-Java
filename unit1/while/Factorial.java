/**
 * Factorial.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 13, 2019
 * The first challenge
 */

import java.util.Scanner;

public class Factorial {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    int base = input.nextInt();
    int counter = 1;
    long factorial = 1;
    while (counter <= base) {
      factorial *= counter;
      counter++;
    }
    System.out.println(factorial);
  }
}