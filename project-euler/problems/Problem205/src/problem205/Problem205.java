package problem205;

import java.util.Random;

public class Problem205 {
    
    /*
    Peter has nine four-sided (pyramidal) dice, each with faces numbered 1, 2, 3, 4.
    Colin has six six-sided (cubic) dice, each with faces numbered 1, 2, 3, 4, 5, 6.

    Peter and Colin roll their dice and compare totals: the highest total wins. The result is a draw if the totals are equal.

    What is the probability that Pyramidal Pete beats Cubic Colin? Give your answer rounded to seven decimal places in the form 0.abcdefg
    */
    
    public static final Random rng = new Random(System.currentTimeMillis());
    
    public static void main(String[] args) {
        long peterWins = 0;
        long n = 1;
        int count = 0;
        while (true) {
            if (peterGo() > colinGo())
                peterWins++;
            
            if (count > 5000000) {
                System.out.println(peterWins * 1.0 / n);
                count = 0;
            }
            
            count++;
            n++;
        }
        
    }
    
    public static int peterGo() {
        int total = 0;
        for (int i = 1; i <= 9; i++)
            total += roll4();
        return total;
    }
    
    public static int colinGo() {
        int total = 0;
        for (int i = 1; i <= 6; i++)
            total += roll6();
        return total;
    }
    
    public static int roll4() {
        return rng.nextInt(4)+1;
    }
    
    public static int roll6() {
        return rng.nextInt(6)+1;
    }
}