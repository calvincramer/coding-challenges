package problem094;

import java.math.BigInteger;
import mathtools.MF;

public class Problem094 {

    /*
    It is easily proved that no equilateral triangle exists with integral length sides and integral area. However, the almost equilateral triangle 5-5-6 has an area of 12 square units.

    We shall define an almost equilateral triangle to be a triangle for which two sides are equal and the third differs by no more than one unit.

    Find the sum of the perimeters of all almost equilateral triangles with integral side lengths and area and whose perimeters do not exceed one billion (1,000,000,000).
    */
    
    public static final BigInteger four = new BigInteger("4");
    public static final BigInteger sixteen = new BigInteger("16");
    
    public static void main(String[] args) {
        
        long sumPerimeters = 0;

        long billion = 1000000000;
        
        for (long leg = 2; leg <= 340000000; leg++) {
            
            long smallSide = leg-1;
            if (leg*2 + smallSide <= billion) {
                if (areaIsIntegerBig(leg, smallSide)) {
                    sumPerimeters += leg + leg + smallSide;
                    System.out.println(leg + "\t" + leg + "\t" + smallSide + "\ta: " + areaBig(leg, smallSide));
                }
            }
            
            long largeSide = leg+1;
            if (leg*2 + largeSide <= billion) {
                if (areaIsIntegerBig(leg, largeSide)) {
                    sumPerimeters += leg + leg + largeSide;
                    System.out.println(leg + "\t" + leg + "\t" + largeSide + "\ta: " + areaBig(leg, largeSide));
                }
            }
            
        }
        System.out.println("totals of perimeters: " + sumPerimeters);
    }
    
    /**
     * A^2 = ((4a^2-c^2)*c^2)/16
     * @param leg12
     * @param leg3
     * @return 
     */
    public static boolean areaIsInteger(long leg12, long leg3) {
        
        long asqr = (4*leg12*leg12) - (leg3*leg3);
        asqr *= leg3 * leg3;
        
        if (asqr % 16 != 0)
            return false;
        
        asqr /= 16;
        
        return MF.isPerfectSquare(asqr);      
    }
    
    public static boolean areaIsIntegerBig(long a, long c) {
        BigInteger legA = new BigInteger(""+a);
        BigInteger legC = new BigInteger(""+c);
        
        BigInteger asqr = BigInteger.ONE.multiply(four).multiply(legA).multiply(legA);      //asqr = 4*a*a
        asqr = asqr.subtract(legC.pow(2));                                                  //asqr -= c*c
        asqr = asqr.multiply(legC.pow(2));                                                  //asqr *= c*c
        
        if ( ! asqr.mod(sixteen).equals(BigInteger.ZERO))           //if asqr%16 != 0
            return false;
        
        asqr = asqr.divide(sixteen);
        return MF.isPerfectSquare2(asqr);
        
    }
    
    public static BigInteger areaBig(long a, long c) {
        BigInteger legA = new BigInteger(""+a);
        BigInteger legC = new BigInteger(""+c);
        
        BigInteger asqr = BigInteger.ONE.multiply(four).multiply(legA).multiply(legA);      //asqr = 4*a*a
        asqr = asqr.subtract(legC.pow(2));                                                  //asqr -= c*c
        asqr = asqr.multiply(legC.pow(2));                                                  //asqr *= c*c
        asqr = asqr.divide(sixteen);
        
        return MF.bigIntSqRootFloor(asqr);
    }
    
    public static long area(long a, long c) {
        long asqr = (4*a*a) - (c*c);
        asqr *= c * c;
        
        asqr /= 16;
        return (long) Math.sqrt(asqr);
    }

}
//312532314457237943
//163767371462

/*
5	5	6	a: 12
17	17	16	a: 120
65	65	66	a: 1848
241	241	240	a: 25080
901	901	902	a: 351780
3361	3361	3360	a: 4890480
12545	12545	12546	a: 68149872
46817	46817	46816	a: 949077360
174725	174725	174726	a: 13219419708
652081	652081	652080	a: 184120982760
2433601	2433601	2433602	a: 2564481115560
9082321	9082321	9082320	a: 35718589344360
33895685	33895685	33895686	a: 497495864091732
126500417	126500417	126500416	a: 6929223155685600
totals of perimeters: 518408346

*/