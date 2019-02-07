/**
 * VariableExcercises.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 6, 2019
 * Simple excercises with variables
 */

public class VariableExcercises {
  
  public static void main(String[] args) {
    //Activity 1
    int num = 5;
    System.out.println(num);
    
    //Activity 2
    int num1 = 10;
    int num2 = 20;
    System.out.println(num1 * num2);
    
    //Activity 3
    int firstInput = 13;
    int secondInput = 2;
    int sum = firstInput + secondInput;
    int difference = firstInput - secondInput;
    int product = firstInput * secondInput;
    int quotient = firstInput / secondInput;
    int remainder = firstInput % secondInput;
    System.out.println(firstInput + " + " + secondInput + " = " + sum);
    System.out.println(firstInput + " - " + secondInput + " = " + difference);
    System.out.println(firstInput + " x " + secondInput + " = " + product);
    System.out.println(firstInput + " / " + secondInput + " = " + quotient);
    System.out.println(firstInput + " % " + secondInput + " = " + remainder);
    
    //Activity 6
    double length = 3.24;
    double width = 4.20;
    double area = width * length;
    System.out.println("The area of a rectangle with a width of " + width + 
                       " and a length of " + length + " is " + area);
    //Activity 7
    double bill = 30;
    double percent = 12;
    final double TAX = 0.13;
    double rawTotal = bill + bill * TAX;
    System.out.println("Raw cost is " + rawTotal);
    System.out.println("The tip is " + (rawTotal * (percent / 100)));
    System.out.println("The total cost is " + (rawTotal + rawTotal * (percent / 100)));
    
    //Activity 8
    float principal = 1000;
    float rate = 15;
    float time = 3;
    //I = P * R * T
    float interest = principal * (rate / 100) * time;
    System.out.println("The interest is " + interest);
    System.out.println("You will earn " + (interest + principal));
    
    //Activity 9
    int firstValue = 4;
    int secondValue = 5;
    //Double cast using double multiplication
    System.out.println(firstValue / (secondValue * 1.0));
    
    //Activity 10
    double a = 2.71;
    double b = 3.14;
    double c = 5;
    //D = b^2 - 4ac
    System.out.println((b * b) - 4 * a * c);
    
  }
}