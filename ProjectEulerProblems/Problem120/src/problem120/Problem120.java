package problem120;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
Let r be the remainder when (a−1)^n + (a+1)^n is divided by a^2.

For example, if a = 7 and n = 3, then r = 42: 6^3 + 8^3 = 728 ≡ 42 mod 49. 
And as n varies, so too will r, but for a = 7 it turns out that rmax = 42.

For 3 ≤ a ≤ 1000, find ∑ rmax.
*/
public class Problem120 {

    public static void main(String[] args) {
        long sum = 0;
        for (int a = 3; a <= 1000; a++) {
            int temp = maxr(a);
            sum += temp;
            System.out.println("a="+a + "\trmax = " + temp);
        }
        
        System.out.println(sum);
    }
    
    public static int maxr(int aa) {
        List<Integer> remainders = new ArrayList<>();
        int d = 1;
        
        BigInteger a = new BigInteger(""+aa);
        BigInteger asqr = a.pow(2);
        
        //System.out.println("a="+a);
        //System.out.println("");
        
        for (int n = 0;; n++) {
            BigInteger r = a.subtract(BigInteger.ONE).pow(n).add(a.add(BigInteger.ONE).pow(n)).mod(asqr);
            remainders.add(r.intValue());
            //System.out.println("\tn="+n + "\tr="+r);
            //check for cyles
            if (d*2 <= remainders.size()) {
                boolean isCycle = true;
                for (int di = 0; di < d; di++) {
                    if (di+d >= remainders.size()) {
                        isCycle = false;
                        break;
                    }
                    if (!remainders.get(di).equals(remainders.get(di+d))) {
                        isCycle = false;
                        break;
                    }
                }
                if (isCycle) {
                    break;
                } else {
                    d++;
                }
            }
        }
        
        int max = remainders.get(0);
        for (int i = 1; i < remainders.size(); i++) {
            if (remainders.get(i) > max)
                max = remainders.get(i);
        }
        return max;
    }
    
}
//answer: 333082500
