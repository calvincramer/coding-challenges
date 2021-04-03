package problem012;

import java.util.ArrayList;
import java.util.List;

public class Problem012 {

    /*
    The sequence of triangle numbers is generated by adding the natural numbers. 
    So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28. The first ten terms would be:

    1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...

    Let us list the factors of the first seven triangle numbers:

    1:  1
    3:  1,3
    6:  1,2,3,6
    10: 1,2,5,10
    15: 1,3,5,15
    21: 1,3,7,21
    28: 1,2,4,7,14,28
    We can see that 28 is the first triangle number to have over five divisors.

    What is the value of the first triangle number to have over five hundred divisors?
    */
    public static void main(String[] args) {
        /*
        for (int i = 1; i <= 30; i++) {
            List<Integer> fs = getFactorsOf(i);
            System.out.print(i + ":\t");
            for (int n = 0; n < fs.size(); n++)
                System.out.print(fs.get(n) + ", ");
            System.out.println();
        }
        */
        
        
        List<Integer> factors = new ArrayList<>();
        int triNum = 0;
        int n = 1;
        while (factors.size() < 500) {
            triNum += n;
            factors = getFactorsOf(triNum);
            if (n % 1000 == 0)
                System.out.println(triNum + " : factors = " + factors.size());
            
            n++;
        }
        System.out.println(triNum + "  : " + factors.size());
        System.out.print("Factors: ");
        for (int i = 0; i < factors.size(); i++)
            System.out.print(factors.get(i) + ", ");
        System.out.println();
                
    } //answer: 76576500

    private static List<Integer> getFactorsOf(Integer n) {
        List<Integer> factors = new ArrayList<>();
        
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0)
                factors.add(i);
        }
        factors.add(n); //every number is a factor of itself
        
        return factors;
    }
}