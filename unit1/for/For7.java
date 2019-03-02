/**
 * For7.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 19, 2019
 * the seventh for loop excercise
 */

import java.util.Scanner;

public class For7 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    int current = 0;
    int sum = 0;
    int highest = 0;
    
    for (int i = 0; i < 10; ++i) {
      current = input.nextInt();
      sum += current;
      if (current > highest) {
        highest = current;
      }
    }
    
    System.out.println(sum);
    System.out.println(sum / 10.0);
    System.out.println(highest);
  }
}