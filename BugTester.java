public class BugTester {
    
    public static void indexOfTests() {
        System.out.println("Starting .indexOf() tests...");
        System.out.println("Batch 1:");
        String[] testStrings = {
            //Single character, in string
            "a", "b", "c", "d", "e", 
            //'Single' character, -1 expected
            "f", " ", "\n", "\t", "%s",
            //Multi-characters, in string
            "abc", "bcd", "cde", "ab", "de",
            //Multi-characters, not in string
            " ab", "a c", "ee", "e ", "\ne\n",
            //The zero-length string says screw you
            "",
        };
        int[] expected1 = {
            0, 1, 2, 3, 4, 
            -1, -1, -1, -1, -1,
            0, 1, 2, 0, 3,
            -1, -1, -1, -1, -1,
            //I hate the zero length string so much
            -1,
        };
        String analyzer = "abcde";
        int count = 0;
        for (int i = 0; i < testStrings.length; ++i) {
            if (analyzer.indexOf(testStrings[i]) == expected1[i]) {
                count++;
            } else {
                System.out.printf("(Test %d) %s.indexOf(%s) fails:\nExpected output: %d\nActual output: %d\n", i+1, analyzer, testStrings[i], expected1[i], analyzer.indexOf(testStrings[i]));
            }
        }
        System.out.printf("%d/%d Passed.\n", count, testStrings.length);
        
        System.out.println("Batch 2: ");
        char[] testChars = {
            //Single chars
            'a', 'b', 'c', 'd', 'e',
            //Numeric equivalents
            97, 98, 99, 100, 101,
            //F me
            255, 'f', ' ', 'A', 'E',
        };
        int[] expected2 = {
            0, 1, 2, 3, 4, 
            0, 1, 2, 3, 4,
            -1, -1, -1, -1, -1,
        };
        count = 0;
        for (int i = 0; i < testChars.length; ++i) {
            if (analyzer.indexOf(testChars[i]) == expected2[i]) {
                count++;
            } else {
                System.out.printf("(Test %d) %s.indexOf(%s) fails:\nExpected output: %d\nActual output: %d\n", i+1, analyzer, testChars[i], expected2[i], analyzer.indexOf(testChars[i]));
            }
        }
        System.out.printf("%d/%d Passed.\n", count, testChars.length);
    }
 
    public static void main(String[] args) {
        indexOfTests();
    }
}