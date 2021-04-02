package problem066;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem066 {
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
        
        List<Triple> mins = new ArrayList<>();

        for (long d = 61; d <= 1000; d++) {
            //find minimal solution to x^2 - Dy^2 = 1
            if (MF.isPerfectSquare(d))
                continue;
            
            
            long scale = 10;
            boolean foundMin = false;
            while (!foundMin) {
                for (long y = 1; y <= scale; y++) {
                    for (long x = 1; x <= scale; x++) {
                        if (x*x - d*y*y == 1){
                            mins.add(new Triple(d,x,y));
                            foundMin = true;
                            break;
                        }

                    }
                    if (foundMin)
                            break;
                }
                if (!foundMin)
                    scale *= 10;
            }
            System.out.println(mins.get(mins.size() -1));
        }

        System.out.println();
        for (Triple tr : mins)
            System.out.println(tr);
        
        
        int largestXindex = 0;
        for (int i = 0; i < mins.size(); i++) {
            if (mins.get(i).x > mins.get(largestXindex).x) {
                largestXindex = i;
                System.out.println("largest so far: " + mins.get(i));
            }
        }
        System.out.println("Largest X:");
        System.out.println(mins.get(largestXindex));
    }

    public static class Triple {
        long d;
        long x;
        long y;
        
        public Triple(long d, long x, long y) {
            this.d = d;
            this.x = x;
            this.y = y;
        }
        
        @Override public String toString() {
            return "D: " + d + "\tX: " + x + "\tY: " + y;
        }
    }
}
