package problem351;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;


public class Attempt7 {
    public static void main(String[] args) {
        
        Pair f1 = new Pair(0,1);
        Pair f2 = new Pair(0,1);
        System.out.println(f1.equals(f2));
        
        List<Pair> reducedFracs = new ArrayList<>();
        final int size = 1000;
        
        for  (int level = size; level >= 1; level--) {
            int addedPerLevel = 0;
            System.out.print("level: " + level);
            for (int x = 0; x <= level; x++) {
                Pair frac = new Pair(x, level);
                int gcf = MF.gcf(x, level);
                frac.numer /= gcf;
                frac.denom /= gcf;
                if (!reducedFracs.contains(frac)) {
                    //System.out.println("adding: "+ frac.toString());
                    reducedFracs.add(frac);
                    addedPerLevel++;
                }
            }
            System.out.println("\tnew reduced fracs: " + addedPerLevel);
        }
        
        
        System.out.println(reducedFracs.size());
    }
    
    
    
    private static class Pair {
        int numer;
        int denom;
        public Pair(int numer, int denom) {
            this.numer = numer;
            this.denom = denom;
        }
        @Override public String toString() {
            return "" + numer + "/" + denom;
        }
        @Override public boolean equals(Object other) {
            if (!(other instanceof Pair))
                return false;
            Pair ot = (Pair) other;
            return this.numer == ot.numer && this.denom == ot.denom;
        }
    }
}
