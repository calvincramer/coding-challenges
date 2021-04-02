package problem060;

import java.util.List;
import mathtools.MF;

public class Problem060 {

    /*
    The primes 3, 7, 109, and 673, are quite remarkable. 
    By taking any two primes and concatenating them in any order the result will always be prime. 
    For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.

    Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
    */
    public static void main(String[] args) {
        
        List<Long> primes = MF.getPrimesUnder(10000);
        long numberOfSets = 0;
        
        for (int p1 = 0; p1 < primes.size(); p1++) {
            System.out.println("p1:" + primes.get(p1));
        for (int p2 = 0; p2 < primes.size(); p2++) {
            if (p2 == p1) continue;
            
            if (!testNumbers(primes.get(p1), primes.get(p2)))
                continue;
            
        for (int p3 = 0; p3 < primes.size(); p3++) {
            if (p3 == p2 || p3 == p1) continue;
            
            if (!testNumbers(primes.get(p1), primes.get(p3)))
                continue;
            if (!testNumbers(primes.get(p2), primes.get(p3)))
                continue;
            
        for (int p4 = 0; p4 < primes.size(); p4++) {
            if (p4 == p3 || p4 == p2 || p4 == p1) continue;
            
            if (!testNumbers(primes.get(p1), primes.get(p4)))
                continue;
            if (!testNumbers(primes.get(p2), primes.get(p4)))
                continue;
            if (!testNumbers(primes.get(p3), primes.get(p4)))
                continue;
            
        for (int p5 = 0; p5 < primes.size(); p5++) {
            if (p5 == p4 || p5 == p3 || p5 == p2 || p5 == p1) continue;
            
            Long[] set = new Long[5];
            set[0] = primes.get(p1);
            set[1] = primes.get(p2);
            set[2] = primes.get(p3);
            set[3] = primes.get(p4);
            set[4] = primes.get(p5);
            //test every pair in set
            boolean passed = true;
            for (int i1 = 0; i1 < set.length; i1++) {
                for (int i2 = 0; i2 < set.length; i2++) {
                    if (i2 == i1) continue;
                    
                    long cat1 = concatenateNumbers(set[i1], set[i2]);
                    long cat2 = concatenateNumbers(set[i2], set[i1]);
                    if ( !MF.isPrimeFast(cat1) || !MF.isPrimeFast(cat2) ) {
                        passed = false;
                        break;
                    }
                        
                }
                if (!passed)
                    break;
            }
            if (passed) {
                for (long prime : set)
                    System.out.println(prime);
                System.out.println();
                numberOfSets++;
            }
        
        }}}}}
        
    }
    /*
    13
    5197
    5701
    6733
    8389
    sum: 26033
    */
    
    public static long concatenateNumbers(long num1, long num2) {
        String cat = "" + num1 + num2;
        return Long.parseLong(cat);
    }
    
    public static boolean testNumbers(long prime1, long prime2) {
        if (!MF.isPrimeFast(concatenateNumbers(prime1, prime2)))
            return false;
        if (!MF.isPrimeFast(concatenateNumbers(prime2, prime1)))
            return false;
        
        return true;
    }

}
