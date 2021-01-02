package problem049;

public class Problem049 {

    /*
    The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: 
        (i) each of the three terms are prime, and, 
        (ii) each of the 4-digit numbers are permutations of one another.

    There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is one other 4-digit increasing sequence.

    What 12-digit number do you form by concatenating the three terms in this sequence?
    */
    public static void main(String[] args) {
        final int inc = 3330;
        
        for (int i = 1000; i <= 9999; i++) {
            int n1 = i;
            int n2 = i + inc;
            int n3 = i + inc + inc;
            if (isPrimeFast(n1) && isPrimeFast(n2) && isPrimeFast(n3)) {
                //make sure permutation
                char[] n1Chars = (n1+"").toCharArray();
                char[] n2Chars = (n2+"").toCharArray();
                char[] n3Chars = (n3+"").toCharArray();
                
                boolean isPermutation = true;
                
                for (char c1 : n1Chars) {
                    //find in n2
                    boolean inN2 = false;
                    for (char c2 : n2Chars) {
                        if (c1 == c2)
                            inN2 = true;
                    }
                    
                    //find in n3
                    boolean inN3 = false;
                    for (char c3 : n3Chars) {
                        if (c1 == c3)
                            inN3 = true;
                    }
                    
                    if (!inN2)
                        isPermutation = false;
                    if (!inN3)
                        isPermutation = false;
                }
                
                if (isPermutation) {
                    System.out.println(n1);
                    System.out.println(n2);
                    System.out.println(n3);
                    System.out.println("" + n1 + n2 + n3);
                    System.out.println();
                }
                
            }
        }
    }
    /* answer:
    2969
    6299
    9629
    296962999629
    */
    
    public static boolean isPrimeFast(long num) {
        if (num < 2) {
            return false;
        }
        for (int n = 2; n <= (int) Math.sqrt(num); n++) {
            if (num % n == 0) {
                return false;
            }
        }

        return true;
    }

}
