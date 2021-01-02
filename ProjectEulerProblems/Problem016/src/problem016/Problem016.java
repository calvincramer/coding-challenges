package problem016;

import java.math.BigInteger;

public class Problem016 {

    /*
    215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
    What is the sum of the digits of the number 2^1000?
    */
    public static void main(String[] args) {
        BigInteger two = new BigInteger("2");
        BigInteger result = two.pow(1000);
        System.out.println("2^1000 = " + result);
        String bigNumber = result.toString();
        long sumOfDigits = 0;
        for (int i = 0; i < bigNumber.length(); i++)
            sumOfDigits += Character.getNumericValue(bigNumber.charAt(i));
        
        System.out.println("Sum of digits: " + sumOfDigits);
    }
    //answer : 1366
    //2^1000 =
    //10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376
}
