/**
 * Main.java
 * @version 1.0
 * @author Allen Liu
 * @since April 3, 2019
 * Where objects are made and run
 */

public class Main {
 
    public static void main(String[] args) {
        SportsCar bugatti = new SportsCar("Bugatti", "Veyron", 100, 60, 100000, 15);
        System.out.println(bugatti.range());
        
        //Student testing
        Student[] students = new Student[5];
        students[0] = new Student("A");
        students[1] = new Student("B", 100, 100, 100, 90);
        students[2] = new Student("C");
        students[3] = new Student("D", 50, 90, 70, 80);
        students[4] = new Student("E");
        
        for (int i = 0; i < students.length; ++i) {
            System.out.println(students[i].getName() + ":");
            System.out.println("School: " + students[i].getSchool());
            System.out.println("Average: " + students[i].getAverage());
            for (int k = 1; k <= 4; ++k) {
                System.out.println("Period " + k + ":\t" + students[i].getMark(k));
            }
            System.out.println();
        }
    }
}