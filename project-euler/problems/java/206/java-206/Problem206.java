package problem206;

import java.math.BigInteger;
import mathtools.MF;

public class Problem206 {

    /*
    Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
    where each “_” is a single digit.
    */

    
    public static void main(String[] args) {
        
        //System.out.println(MF.sqrt(new BigInteger("1929374254627488900")));
        
        BigInteger low = new BigInteger("1020304050607080900");
        low = MF.sqrt(low).subtract(BigInteger.TEN);
        
        while (true) {
            BigInteger temp = low.pow(2);
            //System.out.println(temp);
            if (test(temp) == true) {
                System.out.println(low);
                System.out.println(temp);
                System.out.println("sqr = " + temp.pow(2));
                break;
            }
            low = low.add(BigInteger.ONE);
        }
        
    }
    
    public static boolean test(BigInteger num) {
        char[] digs = num.toString().toCharArray();
        
        if (digs.length != 19)
            return false;
        
        if (digs[0] == '1'
                && digs[2] == '2'
                && digs[4] == '3'
                && digs[6] == '4'
                && digs[8] == '5'
                && digs[10] == '6'
                && digs[12] == '7'
                && digs[14] == '8'
                && digs[16] == '9'
                && digs[18] == '0')
            return true;
        return false;
    }
}
/*
sqroot 1389019170
1929374254627488900
sqr = 3722485014419378373055303519623210000
*/