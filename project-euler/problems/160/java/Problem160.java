package problem160;

import java.math.BigInteger;
import mathtools.MF;

public class Problem160 {
    
    /*
    For any N, let f(N) be the last five digits before the trailing zeroes in N!.
    For example,

    9! = 362880 so f(9)=36288
    10! = 3628800 so f(10)=36288
    20! = 2432902008176640000 so f(20)=17664

    Find f(1,000,000,000,000)
    */
    static final int size = 100000;
    static int[] sol1 = new int[size+1];
    static int[] sol2 = new int[size+1];
    
    public static void main(String[] args) {
        System.out.println("f(9) = " + f(9));
        System.out.println("f(10) = " + f(10));
        System.out.println("f(20) = " + f(20));
        System.out.println("");
        
        for (int i = 0; i < 30; i++)
            System.out.println(i + "!: " + MF.factorial(i));
        System.out.println("");
        
        faster1();
        System.out.println("");
        faster2();
        System.out.println("");
        
        int numErrors = 0;
        for (int i = 0; i < size; i++) {
            if (sol1[i] != sol2[i]) {
                System.out.println(i + ":\t" + sol1[i] + "\t" + sol2[i]);
                numErrors++;
            }
        }
        System.out.println("done compare : " + numErrors + " errors");
    }
    
    public static void faster1() {
        System.out.println("faster1");
        BigInteger fac = BigInteger.ONE;
        BigInteger n = BigInteger.ZERO;
        BigInteger stop = new BigInteger(size+"");
        BigInteger printAt = BigInteger.TEN;
        while (n.compareTo(stop) < 0) {
            n = n.add(BigInteger.ONE);
            fac = fac.multiply(n);
            

            sol1[n.intValueExact()] = format(fac);
            /*
            if (n.equals(printAt)) {
                //printAt = printAt.multiply(BigInteger.TEN);
                printAt = printAt.add(BigInteger.ONE);
                System.out.println(n + "! : f(n) = \t" + format(fac) + "\t" + fac);
            }
            */
            
        }
        System.out.println("done");
    }
    
    //only keep the 5 digits from the fatorial that we need, every other digit is useless
    //apparantly some digits to the left of the  digits change the answer
    //probably because the 5 digits are "moving over" to the left when multiplied by 10 or greater
    public static void faster2() {
        System.out.println("faster2");
        BigInteger fac = BigInteger.ONE;
        BigInteger n = BigInteger.ZERO;
        BigInteger stop = new BigInteger(size+"");
        BigInteger printAt = BigInteger.TEN;
        while (n.compareTo(stop) < 0) {
            n = n.add(BigInteger.ONE);
            fac = fac.multiply(n);
            //strip off right zeroes from fac
            String facStr = fac.toString();
            int i = facStr.length() - 1;
            while (facStr.charAt(i) == '0' && i > 0)
                i--;
            facStr = facStr.substring(0, i+1);
            
            //strip off most of the left digits
            //.....
            int numDigs = 7 + (int)Math.log10(n.intValueExact());
            if (facStr.length() > numDigs)
                facStr = facStr.substring(facStr.length() - numDigs);
            
            fac = new BigInteger(facStr);
            
            sol2[n.intValueExact()] = format(fac);
            
            /*
            if (n.equals(printAt)) {
                //printAt = printAt.multiply(BigInteger.TEN);
                printAt = printAt.add(BigInteger.ONE);
                System.out.println(n + "! : f(n) = \t" + fac);
            }
            */
        }
        System.out.println("done");
    }
    
    public static int format(BigInteger fac) {
        String num = fac.toString();
        //trim rightmost zeroes
        int i = num.length() - 1;
        while (num.charAt(i) == '0' && i > 0)
            i--;
        num = num.substring(0, i+1);
        
        //five digits
        if (num.length() > 5)
            num = num.substring(num.length()-5);
        return Integer.parseInt(num);
    }
    
    public static int f(int n) {
        BigInteger nfac = MF.factorial(n);
        return format(nfac);
    }
}
/* correct answers
10! : f(n) = 	36288
100! : f(n) = 	16864
1000! : f(n) = 	53472
10000! : f(n) = 	79008
100000! : f(n) = 	62496
*/
/* almost correct
10! : f(n) = 	36288
100! : f(n) = 	16864
1000! : f(n) = 	53472
10000! : f(n) = 	79008
100000! : f(n) = 	2496   <--- wrong
1000000! : f(n) = 	4544
10000000! : f(n) = 	51552
100000000! : f(n) = 	52032
*/