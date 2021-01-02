package problem048;

import java.math.BigInteger;

public class Problem048 {

    public static void main(String[] args) {
        
        BigInteger sum = BigInteger.ZERO;
        BigInteger thou = new BigInteger(1000 + "");
        
        for (BigInteger i = BigInteger.ONE; i.compareTo(thou) <= 0; i = i.add(BigInteger.ONE)) {
            BigInteger num = new BigInteger(i + "");
            sum = sum.add(num.pow(i.intValue()));
        }
        
        System.out.println(sum);
        System.out.println(sum.toString().substring(sum.toString().length() - 10));
    }

    /*
    Answer: 9110846700
    */
}
