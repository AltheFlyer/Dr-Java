/**
 * While3.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 13, 2019
 * The third while excercise
 */

import java.util.Scanner;

public class While3 {
  
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int sum = 0;
    int nums = 0;
    int lastNum = 0;

    while ((lastNum = input.nextInt()) != 0) {
      sum += lastNum;
      nums++;
    }
    System.out.println("The average is " + (sum / nums * 1.0));
  }
}