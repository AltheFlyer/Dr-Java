/**
 * [Genetics.java]
 * @version 1.8
 * @author Allen Liu
 * @since April 27, 2019
 * Static class for genetic opperations, where DNA is primarily used as a base-4 system
 * All methods are public in case advanced DNA maniputlation (with partial genomes) is needed
 * i.e a genetics research lab opens up in the ecosim.<br>
 * Words used to refer to genetics include:<br>
 * base pair string, base pair chain, dna string, dna chain, chromosome
 * for any String that represents dna<br>
 * base pair, base character, base for any Character that represents a single base (ACTG) in DNA
 */
abstract public class Genetics {
    
    /* The array of dna bases
     * in order of dominance (greatest to least),
     * in order of value (least (0) to greatest (3))
     * base pairs can be used as a base-4 number system
     */
    private static final char[] bases = {'A', 'C', 'T', 'G'};
    
    //The string representation of a male chromosome
    public static final String maleChromosome = "AACTGA";
    //The string representation of a female chromosome
    public static final String femaleChromosome = "AACAAC";
        
    /**
     * [intToBasePair]
     * converts and integer into a base pair string,
     * with bases as digits in a base-4 number system
     * @param value the integer value to convert into base pairs
     * @return String, the base pair representation of the integer
     */
    public static String intToBasePair(int value) {
        String out = "";
        
        //Find the number of digits in base 4
        int power = 0;
        for (int i = 1; i <= value; i *= 4) {
            power += 1;
        }
        
        //For each base 4 digit, find the appropriate value and
        //Use the appropriate base pair to represend it
        for (int i = 0; i < power; ++i) {
            out = bases[value % 4] + out;
            value /= 4;
        }

        return out;
    }
    
    /**
     * [intToBasePair]
     * converts and integer into a base pair string,
     * with bases as digits in a base-4 number system, 
     * with an additional argument that allows for padding with 0s (represented by A)
     * @param value the integer value to convert into base pairs
     * @param length the length of the resulting base pair string
     * @return String, the base pair representation of the integer, padded to the specified length
     */
    public static String intToBasePair(int value, int length) {
        String s = intToBasePair(value);
        for (int i = s.length(); i < length; ++i) {
            s = "A" + s;
        }
        return s;
    }
    
    /**
     * [basePairToInt]
     * converts a base pair string into an integer by
     * interpreting the chain as a base 4 number
     * @param dna the String of base pairs to interpret
     * @return int, the numeric value of the dna
     */
    public static int basePairToInt(String dna) {
        int out = 0;
        int power = 1;
        //Go from right to left, getting digitsof lowest value first
        for (int i = dna.length() - 1; i >= 0; i--) {
            char base = dna.charAt(i);
            int bit = "ACTG".indexOf(base);    
            out += power * bit;
            power *= 4;
        }
        return out;
    }
    
    /**
     * [getPairPriority]
     * gets the priority value of the base,
     * higher integers are returned for dominant bases, 
     * lower integers for recessive
     * @param base the character of the base to get the priority of
     * @return int, the priority of the base (0-3 for normal bases, -1 for invalid)
     */
    public static int getPairPriority(char base) {
        return "GTCA".indexOf(base);
    }
    
    /**
     * [compareBase]
     * compares two base characters to find the different in priority
     * @param a the first base to check
     * @param b the second base to check
     * @return int, the difference in priority:<br>
     * 0 if equal<br>
     * negative if b is the higher priority<br>
     * positive if a is the higher priority<br>
     */
    public static int compareBase(char a, char b) {
        return getPairPriority(a) - getPairPriority(b);
    }
    
    /**
     * [generateOrganism]
     * Creates an organism (by dna) through the phenotypes and genotypes of two parents.
     * Of the active phenotypes and inactive genotypes, one is picked from random from each
     * parent, and the DNA is combined into a new active phenotype and genotype based on 
     * dominant and recessive traits.
     * @param phenoA the phenotype of the first parent
     * @param genoA the inactive portion of the genotype of the first parent
     * @param phenoB the phenotype of the second parent
     * @param genoB the inactive portion of the genotype of the second parent
     * @return String[], an array of length 2 containing the phenotype and genotype of the new organism. 
     * The phenotype is in the first index, and the genotype is in the second index of the array.
     * An array of length 1 with the String "DNA ERROR" is returned if incompatiable phenotypes and 
     * genotypes are provided.
     */
    public static String[] generateOrganism(String phenoA, String genoA, String phenoB, String genoB) {
        double r = Math.random();
        if (r < 0.25) {
            return generateCombinedPairs(phenoA, phenoB);
        } else if (r < 0.5) {
            return generateCombinedPairs(genoA, phenoB);
        } else if (r < 0.75) {
            return generateCombinedPairs(phenoA, genoB);
        } else {
            return generateCombinedPairs(genoA, genoB);
        }
    }
    
    /**
     * [generateCombinedPairs]
     * Creates an active phenotype and inactive genotype from two dna strings with equal length. 
     * Phenotype and genotype are determined by the dominance of each base pair, 
     * mimicking dominant and recessive traits in real life.
     * @param dna0 the first dna string to use in dna generation
     * @param dna1 the second dna string to use in dna generation
     * @return String[], an array of length 2 containing the phenotype and genotype of the new organism. 
     * The phenotype is in the first index, and the genotype is in the second index of the array.
     * An array of length 1 with the String "DNA ERROR" is returned if the two dna strings are
     * different lengths.
     */
    public static String[] generateCombinedPairs(String dna0, String dna1) {
        //Check for incompatiable dna strings
        if (dna0.length() != dna1.length()) {
            return new String[] {"DNA ERROR"};
        }
        
        //the output values
        String phenotype = "";
        String genotype = "";
        
        //Determine which character becomes active, based on base pair priority:
        // A > C > T > G
        for (int i = 0; i < dna0.length(); ++i) {
            //Check for the dominant base
            int comparison = compareBase(dna0.charAt(i), dna1.charAt(i));
            if (comparison < 0) {
                phenotype = phenotype + dna1.charAt(i);
                genotype = genotype + dna0.charAt(i);
            } else {
                phenotype = phenotype + dna0.charAt(i);
                genotype = genotype + dna1.charAt(i);
            }
        }
        
        return new String[] {phenotype, genotype};
    }
    
    /**
     * [genderToBase]
     * converts a gender as a boolean into the appropriate genetic representation
     * @param isMale whether the intended base pair string should be for a male or not
     * @return String, maleChromosome for a male gender, and femaleChromosome for a female gender
     */
    public static String genderToBase(boolean isMale) {
        if (isMale) {
            return maleChromosome;
        } else {
            return femaleChromosome;
        }
    }
    
    /**
     * [baseToGender]
     * Converts a dna string to a boolean gender
     * @param chromosome the string that should represent a gender
     * @return boolean, whether the provided string represents a male gender or not
     */
    public static boolean baseToGender(String chromosome) {
        if (chromosome == maleChromosome) {
            return true;
        } else if (chromosome == femaleChromosome) {
            return false;
        }
        //If something goes wrong, try to get a valid gender anyways
        if (Math.random() < 0.5) {
            return true;
        }
        return false;
    }
    
    /**
     * [generateRandomDNA]
     * generates random dna from the 4 base pairs
     * @param length the length of the dna string that should be generated
     * @return String, a completely random dna string with the specified length
     */
    public static String generateRandomDNA(int length) {
        String dna = "";
        for (int i = 0; i < length; ++i) {
            double r = Math.random();
            if (r < 0.25) {
                dna += "A";
            } else if (r < 0.5) {
                dna += "C";
            } else if (r < 0.75) {
                dna += "T";
            } else {
                dna += "G";
            }
        }
        return dna;
    }
    
    /**
     * [getRandomGender]
     * gets a random dna representation of a gender (male or female)
     * @return String, a random gender dna string, either maleChromosome or femaleChromosome
     */
    public static String getRandomGender() {
        if (Math.random() < 0.5) {
            return maleChromosome;
        } else {
            return femaleChromosome;
        }
    }
}