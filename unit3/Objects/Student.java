/**
 * Student.java
 * @version 1.0
 * @author Allen Liu
 * @since April 3, 2019
 * Represents a students with a given school and marks
 */
public class Student {
 
    String name;
    double[] marks;
    
    static final String SCHOOL_NAME = "Richmond Hill High School";
    
    Student(String name) {
        this.name = name;
        marks = new double[4];
        for (int i = 0; i < 4; ++i) {
            marks[i] = -1;
        }
    }
    
    Student(String name, double mark1, double mark2, double mark3, double mark4) {
        this.name = name;
        marks = new double[4];
        marks[0] = mark1;
        marks[1] = mark2;
        marks[2] = mark3;
        marks[3] = mark4;
    }
    
    /**
     * getName
     * Gets the student's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * setName
     * Sets a student's name
     * @param name the name of the student
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * getSchool
     * Gets the student's school
     */
    public String getSchool() {
        return SCHOOL_NAME;
    }
    
    /**
     * getMark
     * Gets the student's mark for a given period
     * @param period the period to get the mark from
     */
    public double getMark(int period) {
        return marks[period - 1];
    }
    
    /**
     * setMark
     * sets the student's mark for a given period
     * @param period the period to modify the mark in
     * @mark the value of the student's mark
     */
    public void setMark(int period, double mark) {
        marks[period - 1] = mark;
    }
    
    /**
     * getAverage()
     * @return the average of the student's marks across 4 courses
     */
    public double getAverage() {
        double sum = 0;
        for (int i = 0; i < 4; ++i) {
            sum += marks[i];
        }
        return sum / 4;
    }
}