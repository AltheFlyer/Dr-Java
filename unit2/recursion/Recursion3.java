/**
 * Recursion3.java
 * @version 1.0
 * @author Allen Liu
 * @since Mar 19, 2019
 * 
 */

public class Recursion3 {
    
    public static void main(String[] args) {
        char[] array = {'*', '*', '*', '0', '0'};    
        System.out.println(astrixCount(array));
    }
    
    public static int astrixCount(char[] data) {
        if (data[0] == '*') {
            char[] ret = new char[data.length - 1];
            for (int i = 1; i < data.length; ++i) {
                ret[i - 1] = data[i];
            }
            return 1 + astrixCount(ret);
        }
        return 0;
    }
    
    public static int astrixCount2(char[] data) {
       
        if (data[0] == '*') {
            char[] ret = new char[data.length - 1];
            for (int i = 1; i < data.length; ++i) {
                ret[i - 1] = data[i];
            }
            return 1 + astrixCount(ret);
        }
        return 0;
    }
}