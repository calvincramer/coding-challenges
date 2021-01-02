package problem021;

import java.util.ArrayList;
import java.util.List;

public class Problem021 {

    /*
    Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
    If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

    For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. 
    The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.

    Evaluate the sum of all the amicable numbers under 10000.
    */
    public static void main(String[] args) {
        List<NumberPair> numbers = new ArrayList<>();
        
        for (int n = 1; n < 10000; n++) {
            numbers.add( new NumberPair(n, sumOfProperDivisors(n)) );
        }
        
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (i==j) continue;
                
                if (numbers.get(i).sumOfDivs == numbers.get(j).number
                        && numbers.get(j).sumOfDivs == numbers.get(i).number) {
                    //found amicable pair
                    numbers.get(i).isAmicable = true;
                    numbers.get(j).isAmicable = true;
                }
            }
        }
        
        long totalOfAms = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).isAmicable) {
                totalOfAms += numbers.get(i).number;
                System.out.println(numbers.get(i).number + " amicable");
            }
        }
        System.out.println("Total: " + totalOfAms);
    }
    //answer (total): 31626
    
    /*
    220 amicable
    284 amicable
    1184 amicable
    1210 amicable
    2620 amicable
    2924 amicable
    5020 amicable
    5564 amicable
    6232 amicable
    6368 amicable
    */

    public static List<Integer> properDivisors(Integer n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i < (n/2)+1; i++) {
            if (n % i == 0)
                divisors.add(i);
        }
        return divisors;
    }
    
    public static int sumOfProperDivisors(Integer n) {
        List<Integer> divs = properDivisors(n);
        int sum = 0;
        for (int i = 0; i < divs.size(); i++)
            sum += divs.get(i);
        return sum;
    }
    
    public static class NumberPair {
        public int number;
        public int sumOfDivs;
        public NumberPair(int num, int sum) {
            this.number = num;
            this.sumOfDivs = sum;
        }
        public boolean isAmicable = false;
    }
    
}
