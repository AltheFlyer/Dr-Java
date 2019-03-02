/**
 * AdditionalLoops2.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 14, 2019
 * the second additional excercise
 */

import java.util.Scanner;

public class AdditionalLoops2 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    int num = input.nextInt();
    
    for (int i = 1; i <= num; ++i) {
      if (num % i == 0) {
        System.out.print(i + " ");
      }
    }
  }
}