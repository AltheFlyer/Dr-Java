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
    
    public static int basePairToInt(String dna) {
        int out = 0;
        int power = 1;
        for (int i = dna.length() - 1; i >= 0; i--) {
            char base = dna.charAt(i);
            int bit = "ACTG".indexOf(base);    
            out += power * bit;
            power *= 4;
        }
        return out;
    }
    
    public static int getPairPriority(char base) {
        return "GTCA".indexOf(base);
    }
    
    //Compares a to b
    public static int compareBase(char a, char b) {
        return getPairPriority(a) - getPairPriority(b);
    }
    
    public static String[] generateOrganism(String[] parentA, String[] parentB) {
        double r = Math.random();
        if (r < 0.25) {
            return generateCombinedPairs(parentA[0], parentB[0]);
        } else if (r < 0.5) {
            return generateCombinedPairs(parentA[1], parentB[0]);
        } else if (r < 0.75) {
            return generateCombinedPairs(parentA[0], parentB[1]);
        } else {
            return generateCombinedPairs(parentA[1], parentB[1]);
        }
    }
    
    public static String[] generateCombinedPairs(String a, String b) {
        if (a.length() != b.length()) {
            return new String[] {"DNA ERROR"};
        }
        
        String phenotype = "";
        String genotype = "";
        for (int i = 0; i < a.length(); ++i) {
            int comparison = compareBase(a.charAt(i), b.charAt(i));
            if (comparison < 0) {
                phenotype = phenotype + b.charAt(i);
                genotype = genotype + a.charAt(i);
            } else {
                phenotype = phenotype + a.charAt(i);
                genotype = genotype + b.charAt(i);
            }
        }
        
        return new String[] {phenotype, genotype};
    }
}