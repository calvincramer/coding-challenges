package problem095;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem095 {

    public static List<Integer>[] factors = new ArrayList[1000001];
    public static int[] sumOfFactors = new int[1000001];
            
    public static void main(String[] args) {
        
        MF.startTimer();
        for (int n = 0; n < factors.length; n++) {
            factors[n] = MF.getFactorsOfFaster(n, true, false);
        }
        System.out.println("factored in: " + MF.getElapsedSeconds());
        
        for (int i = 0; i < factors.length; i++) {
            int sum = 0;
            for (int fact : factors[i])
                sum += fact;
            sumOfFactors[i] = sum;
        }
        
        //work

        List<Integer> largestChain = new ArrayList<>();
        for (int i = 2; i <= 1000000; i++) {
            List<Integer> chain = amicableChain(i);
            
            //System.out.println(i + "\t" + MF.listToString(chain));
            
            if (chain.size() > largestChain.size() && sumOfFactors[chain.get(chain.size()-1)] == chain.get(0) ) {
                
                largestChain = chain;
                System.out.println(i);
                System.out.println("length: " + largestChain.size() + "\n" + MF.listToString(largestChain) + "\n");
                
            } else if (chain.size() == largestChain.size()) {
                
                //System.out.println(i);
                //System.out.println("contender: length: " + largestChain.size() + "\n" + MF.listToString(largestChain) + "\n");
                
            }
            
        }
        
        System.out.println("max chain");
        System.out.println("length: " + largestChain.size() + "\n" + MF.listToString(largestChain) + "\n");
        
        int min = Integer.MAX_VALUE;
        for (int chainLink : largestChain)
            if (chainLink < min)
                min = chainLink;
        
        System.out.println("\n\nmin num in max chain: " + min);
        
    }
    
    public static List<Integer> amicableChain(int start) {
        List<Integer> chain = new ArrayList<>();
        chain.add(start);
        
        int current = start;
        
        while (sumOfFactors[current] < factors.length && !chain.contains(sumOfFactors[current]) ) {
            current = sumOfFactors[current];
            chain.add(current);
            if (current == 0)
                break;
        }
            
        
        return chain;
    }
    


}
/*
max chain
length: 28
[14316, 19116, 31704, 47616, 83328, 177792, 295488, 629072, 589786, 294896, 358336, 418904, 366556, 274924, 275444, 243760, 376736, 381028, 285778, 152990, 122410, 97946, 48976, 45946, 22976, 22744, 19916, 17716]

min num in max chain: 14316
*/
