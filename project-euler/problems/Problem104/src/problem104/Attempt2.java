package problem104;

import java.math.BigInteger;
import mathtools.MF;


public class Attempt2 {
    
    public static void main(String[] args) {
        
        final int size = 329468;
        
        //fast
        MF.startTimer();
        String[] first9Guess = new String[size];
        first9Guess[0] = "0";
        first9Guess[1] = "1";
        
        int n1 = 1;
        int n2 = 1;
        
        for (int i = 2; i < first9Guess.length; i++) { //index 40 = first fib number with 9 digits
            String n2str = n2+"";
            if (n2str.length() < 9) {
                while (n2str.length() < 9)
                    n2str = "0"+n2str;
                first9Guess[i] = n2str;
            }
            else 
                first9Guess[i] = n2str;
            
            int next = n1+n2;
            n1 = n2;
            n2 = next;
            
            n2str = n2+"";
            if (n2str.length() > 9)
                n2 = Integer.parseInt(n2str.substring(n2str.length()-9));
        }
        System.out.println("Fast: " + MF.getElapsedSeconds());
        System.out.println(n2);
        
        //slow
        MF.startTimer();
        BigInteger f1 = BigInteger.ONE;
        BigInteger f2 = BigInteger.ONE;
        int k = 2;     //we'll be checking the second fib number
        
        BigInteger[] fibNumbers = new BigInteger[size];
        fibNumbers[0] = BigInteger.ZERO;
        fibNumbers[1] = BigInteger.ONE;
        
        for (; k < fibNumbers.length; k++) {
            fibNumbers[k] = f2;
            BigInteger next = f1.add(f2);
            f1 = f2;
            f2 = next;
        }
        
        String[] first9Actual = new String[size];
        for (int i = 0; i < first9Actual.length; i++) {
            String num = fibNumbers[i].toString();
            if (num.length() >= 10)
                first9Actual[i] = num.substring(num.length()-9);
            else
                first9Actual[i] = num;
            //System.out.println(first9Actual[i]);
        }
        System.out.println("slow: " + MF.getElapsedSeconds());
        

        //test
        for (int i = 40; i < size; i++) {
            if (!first9Actual[i].equals(first9Guess[i])) {
                System.out.println("actual: " + first9Actual[i]);
                System.out.println("guess : " + first9Guess[i]);
                System.out.println();
            }
                
        }
                
        
        
    }
}
