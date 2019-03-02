/**
 * StringFormat3.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 19, 2019
 * The third printf exercise
 */

public class StringFormat3 {
    
    public static void main(String[] args) {
        String select = "one two three";
        System.out.printf("%s\n", select.substring(select.lastIndexOf(" ")));
        select = select.substring(0, select.lastIndexOf(" "));
        System.out.printf("%s\n", select.substring(select.lastIndexOf(" ")));
        select = select.substring(0, select.lastIndexOf(" "));
        System.out.printf("%s\n", select);
    }
}