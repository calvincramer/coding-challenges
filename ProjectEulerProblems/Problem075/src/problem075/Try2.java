package problem075;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;


public class Try2 {

    public static void main(String[] args) {
        
        //for every (a,b) less than 1500000
        //see if right triangle
        
        MF.startTimer();
        
        final int MAX = 1500001;
        int count = 0;
        ArrayList<Triple>[] rightTriangles = new ArrayList[MAX];    //0 to 15 mil indexed by perimeter
        for (int i = 0; i < rightTriangles.length; i++)
            rightTriangles[i] = new ArrayList<Triple>();
        
        for (long a = 1; a < MAX; a++) {
            count++;
            if (count > 1000) {
                System.out.println(a);
                count = 0;
            }
            for (long b = a+1; b < MAX; b++) {
                long c = (long) Math.sqrt(a*a + b*b);
                if (a*a + b*b == c*c) {
                    //System.out.println(a+ ", " + b + ", " + c);
                    int l = (int) (a+b+c);
                    if (l < MAX)
                        rightTriangles[l].add(new Triple(a,b,c));
                }
            }
        }
        
        int total = 0;
        /*
        for (int i = 0; i < rightTriangles.length; i++) {
            if (rightTriangles[i].size() == 0)
                continue;
            System.out.println("l=" + i);
            for (Triple rt : rightTriangles[i]) {
                System.out.println("\t"+rt.a+", " + rt.b + ", " + rt.c);
            }
        }
                */
        for (int i = 0; i < rightTriangles.length; i++)
            if (rightTriangles[i].size() == 1)
                total++;
        
        System.out.println("total: " + total);
        System.out.println(MF.getElapsedSeconds());
        
    }
    
    private static class Triple {
        public long a;
        public long b;
        public long c;
        
        public Triple(long a, long b, long c) {
            this.a = a;
            this.b = b; 
            this.c = c;
        }
    }
}//answer: 161667
