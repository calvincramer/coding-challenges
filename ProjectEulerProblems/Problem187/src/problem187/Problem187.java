package problem187;

import java.util.List;
import mathtools.MF;

public class Problem187 {
    
    /*
    A composite is a number containing at least two prime factors. For example, 15 = 3 × 5; 9 = 3 × 3; 12 = 2 × 2 × 3.

    There are ten composites below thirty containing precisely two, not necessarily distinct, prime factors: 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.

    How many composite integers, n < 10^8, have precisely two, not necessarily distinct, prime factors?
    */
    public static void main(String[] args) {
        //generate primes less than 5*10^7 (50,000,000)
        List<Long> primes = MF.getPrimesUnder(50000000);
        long count = 0;
        for (int p = 0; p < primes.size(); p++) {
            //System.out.println(p);
            for (int q = p; q < primes.size(); q++) {
                if (primes.get(p) * primes.get(q) < 100000000L)
                    count++;
                else 
                    break;
            }
        }
        System.out.println(count);
    }
}
//17427258