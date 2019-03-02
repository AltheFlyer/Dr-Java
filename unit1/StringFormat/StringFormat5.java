/**
 * StringFormat5.java
 * @version 1.1
 * @author Allen Liu
 * @since Feb 25, 2019
 * The final printf exercise
 */

import java.util.Scanner;

public class StringFormat5 {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Please enter your name:");
        String name = input.nextLine();
        
        System.out.println("Please enter your period 1 subject name:");
        String subject1 = input.nextLine();
        System.out.println("Please enter your period 1 mark:");
        double mark1 = input.nextDouble();
        input.nextLine();
        
        System.out.println("Please enter your period 2 subject name:");
        String subject2 = input.nextLine();
        System.out.println("Please enter your period 2 mark:");
        double mark2 = input.nextDouble();
        input.nextLine();
        
        System.out.println("Please enter your period 3 subject name:");
        String subject3 = input.nextLine();
        System.out.println("Please enter your period 3 mark:");
        double mark3 = input.nextDouble();
        input.nextLine();
        
        System.out.println("Please enter your period 4 subject name:");
        String subject4 = input.nextLine();
        System.out.println("Please enter your period 4 mark:");
        double mark4 = input.nextDouble();
        input.nextLine();
        
        System.out.printf("%-25s%25s\n", "Student Profile:", name);
        System.out.println();
        System.out.printf("%-25s%25.2f\n", subject1, mark1);
        System.out.printf("%-25s%25.2f\n", subject2, mark2);
        System.out.printf("%-25s%25.2f\n", subject3, mark3);
        System.out.printf("%-25s%25.2f\n", subject4, mark4);
        System.out.println();
        double sum = mark1 + mark2 + mark3 + mark4;
        System.out.printf("%-25s%25.2f\n", "Total:", sum);
        System.out.printf("%-25s%25.2f\n", "Average:", sum / 4.0);
    }
}