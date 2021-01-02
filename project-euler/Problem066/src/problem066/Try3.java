package problem066;

import java.math.BigInteger;
import mathtools.MF;
import static problem066.Try2.fixedWidthString;

public class Try3 {
    
    /*
    Consider quadratic Diophantine equations of the form:
    x^2 – Dy^2 = 1
    For example, when D=13, the minimal solution in x is 6492 – 13×1802 = 1.
    It can be assumed that there are no solutions in positive integers when D is square.
    By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:

    3^2 – 2×2^2 = 1
    2^2 – 3×1^2 = 1
    9^2 – 5×4^2 = 1
    5^2 – 6×2^2 = 1
    8^2 – 7×3^2 = 1

    Hence, by considering minimal solutions in x for D ≤ 7, the largest x is obtained when D=5.
    Find the value of D ≤ 1000 in minimal solutions of x for which the largest value of x is obtained.
    */
    public static void main(String[] args) {
        
        MF.startTimer();
        
        //System.out.println(getM(7,1,-4,53));
        
        /*
        for (long n = 2; n <= 1000; n++) {
            
            if (MF.isPerfectSquare(n))
                continue;
            
            long a = (int) Math.sqrt(n); //x
            long b = 1;                  //y
            long k = (a*a) - (n*b*b);
            do {
                
                long m = getM(a,b,k,n); //////
                
                long oldA = a;
                a = Math.abs( (a*m + n*b) / k);
                b = Math.abs( (oldA + b*m) / k) ;
                k = (a*a) - (n*b*b);
                
            } while (k != 1);
            
            System.out.println("d: " + fixedWidthString(n+"", 5) + 
                        "\tx: " + fixedWidthString(a+"", 12) +
                        "\ty: " + fixedWidthString(b+"", 12));
        }
                */
        
        BigInteger maxN = new BigInteger("2");
        BigInteger maxX = BigInteger.ZERO;
        BigInteger maxY = BigInteger.ZERO;
        
                
        BigInteger thou = new BigInteger("1000");
        for (BigInteger n = new BigInteger("2"); n.compareTo(thou) <= 0; n = n.add(BigInteger.ONE)) {
            
            if (MF.isPerfectSquare(n))
                continue;
            
            BigInteger a = MF.sqrt(n);      //x
            BigInteger b = BigInteger.ONE;  //y
            BigInteger k = a.pow(2).subtract(n.multiply(b).multiply(b));
            
            do {
                
                BigInteger m = getM(a,b,k,n); //////
                
                BigInteger oldA = a;
                a = a.multiply(m).add( n.multiply(b) ).divide(k).abs();
                b = oldA.add( b.multiply(m) ).divide(k).abs();
                k = a.pow(2).subtract(n.multiply(b).multiply(b));
                
            } while ( !k.equals(BigInteger.ONE));
            
            if (a.compareTo(maxX) > 0) {
                maxX = a;
                maxN = n;
                maxY = b;
                System.out.println("d: " + fixedWidthString(n+"", 5) + 
                        "\tx: " + fixedWidthString(a+"", 12) +
                        "\ty: " + fixedWidthString(b+"", 12));
            }
            
            
            
        }
        
        System.out.println("Time: " + MF.getElapsedSeconds());
        
    }
    
    //doesn't work perfectly, but works eventually :3
    public static long getM(long a, long b, long k, long n) {
        //(a+bm)/k divides evenly and |m^2-N| is minimum
        //abs k
        long minM = 0;
        for (long m  = 1; m <= (int) Math.sqrt(n); m += 1) {
            if ( (a+b*m) % k != 0)
                continue;
            if (Math.abs(m*m - n) < Math.abs(minM*minM - n))
                minM = m;
        }
        
        if (minM == 0)
            System.out.println("Couldn't find m " + a + " " + b + " " + k + " " + n);
        return minM;
    }
    
    public static BigInteger getM(BigInteger a, BigInteger b, BigInteger k, BigInteger n) {
        BigInteger minM = BigInteger.ZERO;
        BigInteger positiveK = k.abs();
        for  (BigInteger m = BigInteger.ONE; m.compareTo(MF.sqrt(n)) <= 0; m = m.add(BigInteger.ONE)) {
            if ( a.add( b.multiply(m) ).mod(positiveK).compareTo(BigInteger.ZERO) != 0 )
                continue;
            if (m.pow(2).subtract(n).abs() .compareTo( minM.pow(2).subtract(n).abs()) < 0)
                minM = m;
        }
        
        if (minM.compareTo(BigInteger.ZERO) == 0)
            System.out.println("Couldn't FIND m " + a + " " + b + " " + k + " " + n);
        return minM;
    }
}
/*
d: 661  	x: 16421658242965910275055840472270471049	y: 638728478116949861246791167518480580
*/
