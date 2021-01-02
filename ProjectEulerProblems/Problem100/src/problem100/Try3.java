/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem100;

import java.math.BigInteger;
import mathtools.MF;
import static problem100.Problem100.two;
/**
 *
 * @author Calvin
 */
public class Try3 {
    
    public static void main(String[] args) {
        System.out.println("3");
        BigInteger head = new BigInteger("1000000000000");
        BigInteger headSqr = head.pow(2);
        BigInteger tail = head.subtract(BigInteger.ONE);
        BigInteger tailSqr = tail.pow(2);
        
        while (true) {
            BigInteger sum = headSqr.add(tailSqr);
            if (MF.isPerfectSquare2(sum)) {
                BigInteger b = BigInteger.ONE.add(MF.bigIntSqRootFloor(sum)).divide(two);
                System.out.println("t= " + head + "\t\tb= " + b);
            }
            
            head = head.add(BigInteger.ONE);
            headSqr = head.pow(2);
            tail = tail.add(BigInteger.ONE);
            tailSqr = tail.pow(2);
        }
        
        
        
    }
}
