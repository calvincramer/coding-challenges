package problem091;

import mathtools.MF;

public class Problem091 {

    /*
    The points P (x1, y1) and Q (x2, y2) are plotted at integer co-ordinates and are joined to the origin, O(0,0), to form ΔOPQ.

    There are exactly fourteen triangles containing a right angle that can be formed when each co-ordinate lies between 0 and 2 inclusive; that is,
    0 ≤ x1, y1, x2, y2 ≤ 2.

    Given that 0 ≤ x1, y1, x2, y2 ≤ 50, how many right triangles can be formed?
    */
    public static void main(String[] args) {
        
        for (int size = 1; size <= 50; size++) 
            System.out.println("size " + size + ": " + (numTriangles(size)/2));
    }

    public static long numTriangles(int size) {
        
        long numTris = 0;
        
        for (int p1y = 0; p1y <= size; p1y++) {
        for (int p1x = 0; p1x <= size; p1x++) {
            
            if (p1y == 0 && p1x == 0)
                continue;
            
            for (int p2y = 0; p2y <= size; p2y++) {
            for (int p2x = 0; p2x <= size; p2x++) {
                
                if (p2y == 0 && p2x == 0)
                    continue;
                if (p2y == p1y && p2x == p1x)
                    continue;
                
                //see if right triangle
                int l1sq = p1y*p1y + p1x*p1x;       //leg1 squared
                int l2sq = p2y*p2y + p2x*p2x;       //leg2 squared
                int dsq = (p2y-p1y)*(p2y-p1y)+(p2x-p1x)*(p2x-p1x);  //d squared
                
                int max = MF.max(l1sq, l2sq, dsq);
                
                if (l1sq + l2sq + dsq - max == max)
                    numTris++;
            }
            }
        }
        }
        
        return numTris;
    }
}
//size 50: 14234