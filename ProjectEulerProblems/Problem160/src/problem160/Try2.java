package problem160;

import java.math.BigInteger;
import mathtools.MF;

public class Try2 {

    public static void main(String[] args) {
        System.out.println("f(9) = " + f(9));
        System.out.println("f(10) = " + f(10));
        System.out.println("f(20) = " + f(20));
        System.out.println("f(15) = " + f(15));
        
        //test();
        
        
        for (long n = 10; n <= 1000000000000L; n *= 10) {
            System.out.println("f(" + MF.numberToString(n) + ") = \t" + f(n));
            //System.out.println("fDumb(" + n + ") = \t" + fDumb(n));
        }
        
    }
    
    public static void test() {
        for (long n = 10; n <= 100000; n++) {
            long ansF = f(n);
            long ansDumb = fDumb(n);
            
            if (ansF != ansDumb) {
                System.out.println("error at n = " + n);
                System.out.println("f(n) = " + ansF);
                System.out.println("fDumb(n) = " + ansDumb);
                System.out.println("");
            }
        }
    }
    
    public static long f(long n) {
        long ans = 1;
        for (long r = 2; r <= n; r++) {
            ans *= r;
            //strip 0s
            while (ans % 10 == 0)
                ans /= 10;
            //keep last 6 digits, since the 6th digit can affect the outcome
            ans %= 10000000;
        }
        return ans % 100000;
    }
    
    public static long fBad(long n) {
        long ans = 1;
        for (long r = 2; r <= n; r++) {
            ans *= r;
            //strip 0s
            while (ans % 10 == 0)
                ans /= 10;
            //keep last 5 digits
            ans %= 100000;
        }
        return ans;
    }

    public static long fDumb(long n) {
        BigInteger fac = MF.factorial(n);
        String str = fac.toString();
        while(str.charAt(str.length() - 1) == '0')
            str = str.substring(0, str.length() - 1);
        str = str.substring(str.length() - 5, str.length());
        return Long.parseLong(str);
    }
}
/*
f(10) = 	36288   GOOD
f(100) = 	16864   GOOD
f(1000) = 	53472   GOOD
f(10000) = 	79008   GOOD
f(100000) = 	2496    INCORRECT
f(1000000) = 	4544
f(10000000) = 	51552
f(100000000) = 	52032
f(1000000000) = 	64704
f(10000000000) = 	83232
f(100000000000) = 	87136
f(1000000000000) = 	30976   INCORRECT

fDumb(10) = 	36288
fDumb(100) = 	16864
fDumb(1000) = 	53472
fDumb(10000) = 	79008
fDumb(100000) = 	62496



f(ten) = 	36288
f(one hundred) = 	16864
f(one thousand) = 	53472
f(ten thousand) = 	79008
f(one hundred thousand) = 	62496
f(one million) = 	12544
f(ten million) = 	94688
f(one hundred million) = 	54176
f(one billion) = 	38144
f(ten billion) = 	-77344

好
*/