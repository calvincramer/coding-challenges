package problem104;

import java.math.BigInteger;
import mathtools.MF;

public class Problem104 {

    /*
    The Fibonacci sequence is defined by the recurrence relation:

    Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
    It turns out that F541, which contains 113 digits, is the first Fibonacci number for which the last nine digits are 1-9 pandigital 
    (contain all the digits 1 to 9, but not necessarily in order). 
    And F2749, which contains 575 digits, is the first Fibonacci number for which the first nine digits are 1-9 pandigital.

    Given that Fk is the first Fibonacci number for which the first nine digits AND the last nine digits are 1-9 pandigital, find k.
    */
    public static void main(String[] args) {
        BigInteger f1 = BigInteger.ONE;
        BigInteger f2 = BigInteger.ONE;
        long k = 2;     //we'll be checking the second fib number
        
        while (f2.toString().length() < 9) {
            BigInteger next = f1.add(f2);
            f1 = f2;
            f2 = next;
            k++;
        }
        
        while (true) {
            
            String f2str = f2.toString();
            String first9 = f2str.substring(f2str.length()-9);
            String last9 =  f2str.substring(0, 9);
            
            //System.out.println(f2str);
            //System.out.println("\t"+first9);
            //System.out.println("\t"+last9+"\n");
            //System.out.println("k="+k);
            
            /*
            if (MF.isPandigital(first9, 1, 9) ) {
                System.out.println("First 9 pan: k = " + k + "\tn = " + f2str);
            }
            if (MF.isPandigital(last9, 1, 9) ) {
                System.out.println("LAST 9 pan: k = " + k + "\tn = " + f2str);
            }
                    */
            
            if (MF.isPandigital(first9, 1, 9) && MF.isPandigital(last9, 1, 9) ) {
                System.out.println("k = " + k + "\tn = " + f2str);
            }
            
            
            BigInteger next = f1.add(f2);
            f1 = f2;
            f2 = next;
            k++;
        }
        
    }

}
//answer: k = 329468
//n = 2456817391860757127354572679274542709785266080676614244523600713114919163118190671495487521958704894682300302893133026962964433860475949604201037424609641542955017984891488105091798013589803910244370238103314544676175955029517758089291370909313680330066070397194601462013248785721482861598021144302249392746085712601062819972781796...............................................................................................................................................................................11238957188311253823164491323794154665840524765670066293341180755764071714813646638474768455401483878038252864172036509444278440246304524662211745206610744866604837986737063797169658636537516684927220566219553269499890534182485542292992286355356740802936117382294309374705781324723489926488460261700907285569276598392830505511790352786941

//run time 1/2 and hour