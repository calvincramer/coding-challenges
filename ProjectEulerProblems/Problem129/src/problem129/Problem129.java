package problem129;

import java.math.BigInteger;
import mathtools.MF;

public class Problem129 {

    /*
    A number consisting entirely of ones is called a repunit. We shall define R(k) to be a repunit of length k; for example, R(6) = 111111.

    Given that n is a positive integer and GCD(n, 10) = 1, it can be shown that there always exists a value, k, for which R(k) is divisible by n, and let A(n) be the least such value of k; for example, A(7) = 6 and A(41) = 5.

    The least value of n for which A(n) first exceeds ten is 17.

    Find the least value of n for which A(n) first exceeds one-million.
    */
    
    public static BigInteger[] repunits = new BigInteger[10000];
    public static boolean goBigger = true;
    
    public static void main(String[] args) {
        //build repunits
        for (int size = 10; size <= 10000000; size *= 10) {
            
            System.out.println("size: " + size);
            /*
            repunits = new BigInteger[size];
            System.out.print("1: ");
            MF.startTimer();
            builtRepunits1();
            System.out.println(MF.getElapsedSeconds());
            */
            /*
            repunits = new BigInteger[size];
            System.out.print("2: ");
            MF.startTimer();
            builtRepunits2();
            System.out.println(MF.getElapsedSeconds());
            */
            repunits = new BigInteger[size];
            System.out.print("3: ");
            MF.startTimer();
            builtRepunits3();
            System.out.println(MF.getElapsedSeconds());
        }
        
        System.out.println("\n\n---------------------\n\n");
        
        
        MF.startTimer();
        
        long n = 1;
        long max = 10;
        long maxmax = 10000000;
        while (true) {
            if (n % 5 == 0)
                n += 2;
        
            long an = a(n);
            
            //System.out.println("a(" + n + ")" + " : " + an);
            if (an > max) {
                System.out.println("exceeded: " + max + "\ta(" + n + ") = " + an);
                max *= 10;
                n = max -1;
                
                if (max > maxmax)
                    break;
            }

            //System.out.println("\ntime: " + MF.getElapsedSeconds());
            
            n += 2;
        }
        System.out.println(MF.getElapsedSeconds());
    }
    
    
    public static long a(long n) {
        int k = 1;
        BigInteger rk = BigInteger.ONE;
        BigInteger num = new BigInteger(""+n);
        
        while (!rk.mod(num).equals(BigInteger.ZERO)) {
            
            if (k < repunits.length)
                rk = repunits[k];
            else {
                rk = new BigInteger(rk.toString()+"1"); //append a 1
                if (goBigger) {
                    System.out.println("go bigger");
                    goBigger = false;
                }
            }
            k++;
        }
        
        
        return k;
    }
    
    
    /**
     * slowest
     */
    public static void builtRepunits1() {
        //MF.startTimer();
        repunits[0] = BigInteger.ONE;
        for (int i = 1; i < repunits.length; i++) {
            repunits[i] = new BigInteger(repunits[i-1].toString()+"1");
        }
        //System.out.println("built in " + MF.getElapsedSeconds());
    }
    
    /**
     * a little faster
     */
    public static void builtRepunits2() {
        //MF.startTimer();
        String s = "1";
        for (int i = 0; i < repunits.length; i++) {
            repunits[i] = new BigInteger(s);
            s += "1";
        }
        //System.out.println("built in " + MF.getElapsedSeconds());
    }
    
    /**
     * supes fast
     */
    public static void builtRepunits3() {
        //MF.startTimer();
        repunits[0] = BigInteger.ONE;
        for (int i = 1; i < repunits.length; i++) {
                repunits[i] = repunits[i-1].multiply(BigInteger.TEN).add(BigInteger.ONE);
        }
        //System.out.println("built in " + MF.getElapsedSeconds());
    }
    
    
    
    
}
/*
exceeded: 10	a(17) = 16
exceeded: 100	a(109) = 108
exceeded: 1000	a(1017) = 1008

*/
/*
built in 8.980976428
exceeded: 10	a(17) = 16
exceeded: 100	a(109) = 108
exceeded: 1000	a(1017) = 1008
exceeded: 10000	a(10007) = 10006
0.079429424
*/