package problem108;

import mathtools.MF;

public class Problem108 {

    /*
    In the following equation x, y, and n are positive integers.

    1/x + 1/y = 1/n
    For n = 4 there are exactly three distinct solutions:

    1/5 + 1/20 = 1/4
    1/6 + 1/12 = 1/4
    1/8 + 1/8  = 1/4
    What is the least value of n for which the number of distinct solutions exceeds one-thousand?
    */
    public static void main(String[] args) {
        
        long maxN = 0;
        long maxSols = 0;
        
        System.out.println();
        for (long n = 4; n < 10000000; n++) { 
            long start = n+1;
            long end = 2*n;
            
            
            //System.out.println("n="+n);
            
            int numSols = 0;
            
            for (long x = start; x <= end; x++) {
                if ( (x*n) % (x-n) == 0) {
                    //long y = x*n / (x-n);
                    //printAnswer(x,y,n);
                    numSols++;
                }
            }
            
            if (numSols > 1000) {
                System.out.println("WINNER WINNER");
                System.out.println("n="+n+"\tsols:"+numSols);
                System.out.println("WINNER WINNER");
                break;
            }
            
            //System.out.println();
            //System.out.println("n="+n+"\tsols:"+numSols);
            if (numSols > maxSols) {
                long guess = (numDivisors(n*n)+1) / 2;
                System.out.println("n="+n+"\tsols:"+numSols +"\t\t"+guess);
                maxN = n;
                maxSols = numSols;
            }
        }
    }
    
    
    public static void printAnswer(long x, long y, long n) {
        System.out.println("1/"+x+" + 1/"+y+" = 1/"+n);
    }

    public static long numDivisors(long n) {
        long divs = 0;
        for (int i = 2; i < (int) Math.sqrt(n); i++) {  //dont double up on perfect squares
            if (n % i == 0)
                divs++;
        }
        divs *= 2;
        
        if (MF.isPerfectSquare(n))
            divs++;
        
        divs += 2; //one and itself
        
        
        return divs;
    }
    
}
/*
n=4	sols:3
n=6	sols:5
n=12	sols:8
n=24	sols:11
n=30	sols:14
n=60	sols:23
n=120	sols:32
n=180	sols:38
n=210	sols:41
n=360	sols:53
n=420	sols:68
n=840	sols:95
n=1260	sols:113
n=1680	sols:122
n=2520	sols:158
n=4620	sols:203
n=7560	sols:221
n=9240	sols:284
n=13860	sols:338
n=18480	sols:365
n=27720	sols:473
n=55440	sols:608
n=83160	sols:662
n=110880	sols:743
n=120120	sols:851
WINNER WINNER
n=180180	sols:1013
WINNER WINNER
*/