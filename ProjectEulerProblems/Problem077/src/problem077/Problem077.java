package problem077;

import mathtools.MF;

public class Problem077 {
    
    static int target = 1;
    static long ways = 0;

    public static void main(String[] args) {

        while (ways < 5000) {
            ways = 0;
            permute(0, target);
            System.out.println(target + "\t" + ways);
            target++;
        }
    }
    
    public static void permute(int sum, int lastNumber) {
        
        for (int i = 1; i <= lastNumber; i++) {
            if (sum + i > target)
                return;
            
            if (!MF.isPrimeFaster(i))
                continue;
            
            if (sum + i == target)
                ways++;

            permute(sum+i, i);
        }
    }

}
/*
69	4268
70	4624
71	5007
*/