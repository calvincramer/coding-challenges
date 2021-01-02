package problem317;

public class Problem317 {

    /*
    A firecracker explodes at a height of 100 m above level ground. It breaks into a large number of very small fragments, 
    which move in every direction; all of them have the same initial velocity of 20 m/s.

    We assume that the fragments move without air resistance, in a uniform gravitational field with g=9.81 m/s2.

    Find the volume (in m^3) of the region through which the fragments move before reaching the ground. Give your answer rounded to four decimal places.
    */
    
    public static final double ag = -9.81;     //acceleration of gravity
    public static final double longestAngle = 0.39039709;
    public static final double largestDistance = distance(longestAngle);
    public static final double heighestDistance = maxHeight(Math.PI / 2);
    
    public static void main(String[] args) {
        double maxDist = 0;
        double maxAngle = 0.0;  //radians
        
        double step = 0.001;
        double angle = 0;
        while (step >= Double.MIN_VALUE) {
            
            while (distance(angle) < distance(angle+step)) {
                double tempDistance = distance(angle);
                if (tempDistance > maxDist) {
                    maxDist = tempDistance;
                    maxAngle = angle;
                }
                angle += step;
            }
            
            while (distance(angle) < distance(angle-step)) {
                double tempDistance = distance(angle);
                if (tempDistance > maxDist) {
                    maxDist = tempDistance;
                    maxAngle = angle;
                }
                angle -= step;
                }
            
            step *= 0.1;
        }
        System.out.println("max angle:\t" + maxAngle + "\t\tdeg: " + radToDegree(maxAngle));
        System.out.println("max dist :\t" + maxDist);
        System.out.println("\t\t"+distance(0.39039709));
        System.out.println("max height (90 degrees up) : " + maxHeight(Math.PI / 2));
        System.out.println("volume: " + getVolume());
    }
    
    public static double timeInAir(double angle) {
        double ans = Math.pow(Math.sin(angle), 2.0) - (ag/2);
        ans = Math.sqrt(ans) * 20.0;
        ans = (-20.0 * Math.sin(angle)) - ans;
        return ans / ag;
    }

    public static double distance(double angle) {
        return 20.0 * Math.cos(angle) * timeInAir(angle);
    }
    
    public static double radToDegree(double angRad) {
        return angRad * 180.0 / Math.PI;
    }
    
    public static double maxHeight(double angle) {
        double ans = 2.0/ag;
        ans *= Math.pow(Math.sin(angle), 2.0);
        ans = 1 - ans;
        return 100 * ans;
    }
    
    public static double getVolume() {
        return largestDistance * largestDistance * Math.PI * heighestDistance / 2.0;
    }
}
//close but not best angle in degrees: 23.94963
//actual best angle is about 22.36810559118898

//answer: 1856532.8455 m^3
//did most by hand