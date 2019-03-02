/**
 * Pyramid.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 13, 2019
 * The second challenge
 */

import java.util.Scanner;

public class Pyramid {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int size = input.nextInt();
    int counter1 = 1;
    int counter2 = 0;
    while (counter1 <= size) {
      counter2 = 1;
      while (counter2 <= counter1) {
        System.out.print(counter1 + " ");
        counter2++;
      }
      System.out.println();
      counter1++;
    }
  }
}