package problem097;

import java.math.BigInteger;

public class Problem097 {

    /*
    The first known prime found to exceed one million digits was discovered in 1999, and is a Mersenne prime of the form 26972593−1; it contains exactly 2,098,960 digits. Subsequently other Mersenne primes, of the form 2p−1, have been found which contain more digits.

    However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits: 28433×2^7830457+1.

    Find the last ten digits of this prime number.
    */
    public static void main(String[] args) {
        BigInteger n28433 = new BigInteger("" + 28433);
        BigInteger two = new BigInteger("2");
        //BigInteger n7830457 = new BigInteger(""+ 7830457);
        
        BigInteger num = two.pow(7830457);
        num = num.multiply(n28433);
        num = num.add(BigInteger.ONE);
        
        System.out.println(num.toString());
        System.out.println();
        System.out.println(num.toString().substring(num.toString().length()-20, num.toString().length()));
    }

}
//8739992577