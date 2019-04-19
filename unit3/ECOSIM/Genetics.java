public class Genetics {
    
    private static char[] bases = {'A', 'C', 'T', 'G'};
    
    
    
    public static String intToBasePair(int value) {
        String out = "";
        
        int power = 0;
        for (int i = 1; i <= value; i *= 4) {
            power += 1;
        }
        
        for (int i = 0; i < power; ++i) {
            out = bases[value % 4] + out;
            value /= 4;
        }

        return out;
    }
    
    public static String intToBasePair(int value, int length) {
        String s = intToBasePair(value);
        for (int i = s.length(); i < length; ++i) {
            s = "A" + s;
        }
        
        return s;
    }
}