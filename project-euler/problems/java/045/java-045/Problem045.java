package problem045;

import java.math.BigInteger;

public class Problem045 {

    /*
    Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:

    Triangle	 	Tn=n(n+1)/2	 	1, 3, 6, 10, 15, ...
    Pentagonal	 	Pn=n(3n−1)/2	 	1, 5, 12, 22, 35, ...
    Hexagonal	 	Hn=n(2n−1)	 	1, 6, 15, 28, 45, ...
    It can be verified that T285 = P165 = H143 = 40755.

    Find the next triangle number that is also pentagonal and hexagonal.
    */
    public static void main(String[] args) {
        
        BigInteger curTri = BigInteger.ONE;
        BigInteger curPent = BigInteger.ONE;
        BigInteger curHex = BigInteger.ONE;
        BigInteger t = BigInteger.ONE;  //indexes
        BigInteger p = BigInteger.ONE;
        BigInteger h = BigInteger.ONE;
        
        BigInteger two = new BigInteger(2 + "");
        BigInteger three = new BigInteger(3 + "");

        int numFound = 0;
        
        while (numFound < 3) {
            
            //System.out.println(curTri + "\t" + curPent + "\t" + curHex);
            if (curTri.compareTo(curPent) == 0 && curTri.compareTo(curHex) == 0) {
                numFound++;
                System.out.println(curTri + "\t" + curPent + "\t" + curHex);
            }
            
            t = t.add(BigInteger.ONE);                    //next tri
            //curTri = t*(t+1)/2;
            curTri = t.multiply(t.add(BigInteger.ONE)).divide(two);
            
            while (curPent.compareTo(curTri) < 0) {
                p = p.add(BigInteger.ONE);
                //curPent = p*(3*p-1)/2;
                curPent = p.multiply(three.multiply(p).subtract(BigInteger.ONE)).divide(two);
                
            }
            while (curHex.compareTo(curTri) < 0) {
                h = h.add(BigInteger.ONE);
                //curHex = h*(2*h-1);
                curHex = h.multiply(two.multiply(h).subtract(BigInteger.ONE));
            }
            
        }
 

    }
    //answer: 1533776805

}
