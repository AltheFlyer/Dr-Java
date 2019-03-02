/**
 * For6.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 19, 2019
 * the sixth for loop excercise
 */

import java.util.Scanner;

public class For6 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    int start = input.nextInt();
    int end = input.nextInt();
    
    for (int i = start; i <= end; ++i) {
      System.out.println(i);
    }
  }
}