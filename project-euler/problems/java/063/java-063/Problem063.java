package problem063;

import java.math.BigInteger;

public class Problem063 {

    /*
    The 5-digit number, 16807=7^5, is also a fifth power. Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
    How many n-digit positive integers exist which are also an nth power?
    */
    public static void main(String[] args) {
        
        faster();
        
        /*
        int maxExp = 10000;
        int numQual = 0;
        
        for (int exp = 1; exp < maxExp; exp++) {
            
            BigInteger n = BigInteger.ONE;
            //for each n while n^exp has more digits than exp
            while(n.pow(exp).toString().length() <= exp) {
                BigInteger res = n.pow(exp);
                if (res.toString().length() == exp) {
                    numQual++;
                    System.out.println(n+"^"+exp+ " = " + res + "\t\tSo far: " + numQual);
                    
                }
                n = n.add(BigInteger.ONE);
            }
            
            
        }
        */
    }
    
    public static void faster() {
        int maxExp = 10000;
        int numQual = 0;
        
        for (int exp = 1; exp < maxExp; exp++) {
            
            BigInteger n = BigInteger.ONE;
            for (; n.compareTo(BigInteger.TEN) < 0; n = n.add(BigInteger.ONE)) {
            //while(n.pow(exp).toString().length() <= exp) {
                BigInteger res = n.pow(exp);
                if (res.toString().length() == exp) {
                    numQual++;
                    System.out.println(n+"^"+exp+ " = " + res + "\t\tSo far: " + numQual);
                    
                }
                //n = n.add(BigInteger.ONE);
            }
            
            
        }
    }
    //answer: 49
    // range of n must be [1,9], because for n >= 10, n^(any number) will always (apparantly) have more digits.
    // after 9^10, further matches get closer to 10*********...

}
