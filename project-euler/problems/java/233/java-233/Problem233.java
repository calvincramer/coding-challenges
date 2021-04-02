package problem233;
//import mathtools.MF;
import mathtools.Fraction;

/**
 * <a href="https://projecteuler.net/problem=233">https://projecteuler.net/problem=233</a>
 * @author CalvinLaptop
 */
public class Problem233 {

    public static void main(String[] args) {
        System.out.println(bruteForceCirclePoints(10000));
    }

    public static long bruteForceCirclePoints(long N) {
        //distance between the side of the square incribed by the circle, and the maximum distance to the circle
        long circleSquareMaxDiff = (long) Math.ceil(N * (Math.sqrt(2) - 1) / 2.0);
        long min = 0 - circleSquareMaxDiff;
        long max = N + circleSquareMaxDiff;
        
        //check if all lattice points are on the cirlce
        long numOnCircle = 0;
        
        for (long x = min; x <= max; x++) {
        for (long y = min; y <= max; y++) {
            if (pointOnCircle(x, y, N) == true)
                numOnCircle++;
        }}
        
        return numOnCircle;
    }
    
    /**
     * Return true iff (x-(N/2))^2 + (y-(N/2))^2 = (N^2)/2
     * @param x
     * @param y
     * @param N
     * @return 
     */
    public static boolean pointOnCircle(long x, long y, long N) {
        Fraction actualRadius = new Fraction(N*N, 2); 
        
        Fraction _x = new Fraction(x, 1);
        Fraction n2 = new Fraction(N, 2);
        Fraction pointRadius = _x.subtract(n2);
        pointRadius = pointRadius.multiply(pointRadius);
        Fraction _y = new Fraction(y, 1);
        Fraction temp = _y.sub(n2);
        temp = temp.mult(temp);
        pointRadius = pointRadius.add(temp);
        pointRadius.reduceFraction();
        
        return actualRadius.equals(pointRadius);
    }
}
