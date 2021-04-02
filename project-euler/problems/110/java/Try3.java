package problem110;

import java.util.ArrayList;
import mathtools.MF;

public class Try3 {

    private static final long start = 0;
    private static final long stop = 1261;
    
    public static void main(String[] args) {
        
        ArrayList<ArrayList<Pair>> allSolutions = new ArrayList<ArrayList<Pair>>();
        for (long n = start; n <= stop; n++)
            allSolutions.add(new ArrayList<>());
        
        for (long n = 2; n <= stop; n++) {
            long numSolutions = 0;
            
            long x = n+1;     //if x is less than n, dividing by (x-n) will result in a negative number which we don't care about
            long xStop = 2*n;
            for (; x <= xStop; x++) {
                if ( (n*x) % (x - n) == 0) {
                    numSolutions++;
                    allSolutions.get((int)n).add(new Pair(x, (n*x) / (x-n)));
                } 
            }
            //symmetric
            for (int i = allSolutions.get((int)n).size() - 1; i >= 0; i--) {
                Pair temp = allSolutions.get((int)n).get(i);
                if (temp.x != temp.y)                           //dont duplicate (12,12)
                    allSolutions.get((int)n).add(new Pair(temp.y,temp.x));
            }
            
            System.out.println("n=" + n + "\tnum solutions: " + numSolutions);
            MF.printList(allSolutions.get((int)n));
            
        }
        /*
        for (long n = start; n <= stop; n++) {
            for (Pair p : allSolutions.get((int)n)) {
                MF.LFraction frac = new MF.LFraction(MF.max(p.x, p.y), n);
                frac.reduceFraction();
                if (frac.denominator != 1)
                    System.out.println(p.toString() + "\tn=" + n + "\t" + MF.max(p.x, p.y) + "/n= " + frac.toString());
            }
        }
    */
        
        
        
        
    }
}
