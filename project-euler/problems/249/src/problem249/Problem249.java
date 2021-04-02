package problem249;

import java.util.List;
import mathtools.MF;

public class Problem249 {

    public static void main(String[] args) {
        List<Long> primes = MF.getPrimesUnder(5000);
        System.out.println(primes);
        System.out.println("");
        System.out.println("length = " + primes.size());
        long sum = primes.stream().mapToLong(Long::longValue).sum();
        System.out.println("sum = " + sum);
        
    }

}
