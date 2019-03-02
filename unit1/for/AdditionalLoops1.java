/**
 * AdditionalLoops1.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 14, 2019
 * the first additional excercise
 */

import java.util.Scanner;

public class AdditionalLoops1 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    double lastIn;
    double sum = 0;
    double maximum = 0;
    int numStudents = 0;
    String bestName = "";
    
    do {
      ++numStudents;
      System.out.println("Enter your name: ");
      String name = input.nextLine();
      
      System.out.println("Enter your marks");
      for (int i = 0; i < 4; ++i) {
        do {
          lastIn = input.nextDouble();
        } while (lastIn < 0 || lastIn > 100);
        sum += lastIn;
        if (lastIn > maximum) {
          maximum = lastIn;
          bestName = name;
        }
      }
      input.nextLine();
      System.out.println("Enter another student? (y/n)");
    } while (input.nextLine().equals("y"));
    
    
    System.out.println(bestName + " " + maximum);
    System.out.println(sum / (numStudents * 4.0));
    
  }
}