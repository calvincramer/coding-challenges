/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem100;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import mathtools.MF;
import static problem100.Problem100.two;

/**
 *
 * @author Calvin
 */
public class Try2 {
    
    public static void main(String[] args) {
        
        final int max = 100000;
        List<BigInteger> squares = new ArrayList<>();
        for (int n = 0; n < max; n++) {
            squares.add(new BigInteger(""+n).pow(2));
        }
        
        List<BigInteger> quals = new ArrayList<>();
        for (int i = 1; i < max; i++) {
            BigInteger test = squares.get(i).add(squares.get(i-1));
            if (squares.contains(test)) {
                BigInteger b = BigInteger.ONE.add(MF.bigIntSqRootFloor(test)).divide(two);
                BigInteger t = MF.bigIntSqRootFloor(squares.get(i));
                System.out.println("t= " + t + "\tb= " + b);
                
            }
        }
        

    }
    
    public static BigInteger getB(BigInteger t) {
        /*
        long sqr = 1 - (2 * t) + (2 * t * t);
        if (MF.isPerfectSquare(sqr)) {
            sqr = (int) Math.sqrt(sqr);
            long b = (1 + sqr) / 2;
            System.out.println("total = " + t + "\t\tnum blue = " + b);

            boolean check = b * (b-1) == (t * (t-1)) / 2;
            System.out.println((check) ? "good" : "bad");
        }
        */
        BigInteger sqr = BigInteger.ONE.subtract(two.multiply(t)).add(two.multiply(t.pow(2)));
        if (MF.isPerfectSquare(sqr)) {
            sqr = MF.bigIntSqRootFloor(sqr);
            BigInteger b = (BigInteger.ONE.add(sqr)).divide(two);
            return b;
        }
        return null;
    }
}
