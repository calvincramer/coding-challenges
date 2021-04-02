package problem351;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;
import mathtools.MF.LFraction;


public class Attempt3 {
    
    private static ArrayList<ArrayList<LFraction>> bar;

    public static void main(String[] args) {
        int[] sizes = {10, 1000, 2000, 5000, 10000, 20000, 500000};
        
        for (int size : sizes) {
            System.out.println(size + "\t" + buildBar(size));
            
            /* print bar
            for (int row = 0; row < bar.size(); row++) {
                for (LFraction f : bar.get(row)) {
                    System.out.print(f.toString() + "  ");
                }
                System.out.println();
            }
            */
            
            
        }
    }
    
    
    public static double buildBar(int size) {
        MF.startTimer();
        
        bar = new ArrayList<ArrayList<LFraction>>(size);
        for (int i = 0; i < size; i++) {
            bar.add(new ArrayList<LFraction>());
            //add fractions
            int denom = i + 1;
            for (int numer = 0; numer <= i + 1; numer++) {
                bar.get(i).add(new LFraction(numer, denom));
            }
        }
        
        System.out.println(bar.size());
        
        
        return MF.getElapsedSeconds();
    }
}
/*
What we learned:
Building very large arrays doesn't work
throws out of memory error
which makes sense, because there are around 3E16 fractions, and a gigabyte is 1E9
would need a million gigabytes

one day...
*/