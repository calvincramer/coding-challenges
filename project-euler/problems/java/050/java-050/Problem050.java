package problem050;

import java.util.ArrayList;
import java.util.List;

public class Problem050 {

    /*
    The prime 41, can be written as the sum of six consecutive primes:

    41 = 2 + 3 + 5 + 7 + 11 + 13
    This is the longest sum of consecutive primes that adds to a prime below one-hundred.

    The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.

    Which prime, below one-million, can be written as the sum of the most consecutive primes?
    */
    public static void main(String[] args) {
        List<Integer> primes = getPrimesUnder(1000000);

        int greatestChain = 0;
        int primeChain = 0;

        for (int prime : primes) {

            int start = 0;
            while (primes.get(start) < prime) {
                int sum = 0;
                int index = start;
                while (sum < prime) {
                    sum += primes.get(index);
                    index++;
                }

                if (sum == prime) {
                    if (index - start > greatestChain) {
                        greatestChain = index - start;
                        primeChain = prime;
                    }
                }

                start++;
            }

        }

        System.out.println("Chained: " + greatestChain);
        System.out.println(primeChain);
    }
    /*
    answer:
    Chained: 543
    997651
    */

    public static List<Integer> getPrimesUnder(int under) {
        boolean[] marks = new boolean[under];
        for (int i = 0; i < marks.length; i++)
            marks[i] = true;
        marks[0] = false;   //indecies of marks are the numbers, so 0 and 1 are not prime
        marks[1] = false;

        for (int i = 2; i < under; i++) {
            for (int k = i + i; k < under; k += i)
                marks[k] = false;
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 0; i < marks.length; i++)
            if (marks[i] == true)
                primes.add(i);

        return primes;
    }

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
