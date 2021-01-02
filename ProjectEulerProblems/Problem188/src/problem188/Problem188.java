package problem188;

import java.math.BigInteger;

public class Problem188 {

    public static void main(String[] args) {
        
        BigInteger num = new BigInteger("3");
        BigInteger ans = num;
        
        for (int i = 1; i < 4; i++)
            ans = num.pow(ans.intValue());
        
        System.out.println(ans);
        
    }

}
