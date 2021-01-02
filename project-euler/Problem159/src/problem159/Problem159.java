package problem159;

import java.util.List;
import java.util.Random;
import mathtools.MF;
import mathtools.PrimeFactorizationPrecomputer;

public class Problem159 {
    
    static boolean[] filledIn = new boolean[1000020];
    static long[] digitalRoot = new long[1000020];

    public static void main(String[] args) {
        /*
        MF.startTimer();
        List<Integer>[] factorizations = MF.getPrimeFactorizationUnder(1000000);
        long sum = 0;
        for (int i = 0; i < factorizations.length; i++) {
            if (i % 1000 == 0)
                System.out.println(i);
            sum += factorPermute(factorizations[i].stream().mapToInt(j->j).toArray());
        }
        System.out.println(sum);
        System.out.println("time = " + MF.getElapsedSeconds());
        */
        
        Random rng = new Random(System.currentTimeMillis());
        
        for (int n = 0; n < 10000; n++) {
            
            int num = rng.nextInt(1000) + 311000;
            
            List<Long> factors = PrimeFactorizationPrecomputer.getPrimeFactorization(num);
            int[] factorsArr = new int[factors.size()];
            for (int i = 0; i < factors.size(); i++)
                factorsArr[i] = factors.get(i).intValue();
            MF.startTimer();
            long mdrs = factorPermute(factorsArr);
            double time = MF.getElapsedSeconds();
            if (time > 0.5)
                System.out.println(" \t" + num + "\t" + MF.getElapsedSeconds() + "\t wow \t" + factors + "\t num factors: " + factors.size() + "\t mdrs = " + mdrs);
            else
                System.out.println(num + "\t" + MF.getElapsedSeconds() + "\t num factors: " + factors.size());
        }
        
    }
    /*
    public static long factorPermute(List<Integer> factors) {
        
        //System.out.println(factors);    //visit
        if (factors.size() <= 1)
            return digitalRootSum(factors);
        
        long max = digitalRootSum(factors);
        
        for (int i = 0; i < factors.size();) {
            for (int j = i + 1; j < factors.size();) {
                if (j == i)
                    continue;

                List<Integer> copy = new ArrayList<>();
                for (Integer n : factors) copy.add(n);


                int el1 = factors.get(i);
                int el2 = factors.get(j);
                copy.remove(i);
                copy.remove(j - 1);
                copy = addInOrder(copy, el1 * el2);

                long res = factorPermute(copy);
                if (res > max)
                    max = res;
                
                int newJ = j+1;
                while (newJ < factors.size() && factors.get(newJ) == factors.get(j))
                    newJ++;
                j = newJ;
            }
            int newI = i+1;
            while (newI < factors.size() && factors.get(newI) == factors.get(i))
                newI++;
            i = newI;
        }
        
        return max;
    }
    */
    
    public static long factorPermute(int[] factors) {
        
        //System.out.println(factors);    //visit
        if (factors.length <= 1)
            return digitalRootSum(factors);
        
        long max = digitalRootSum(factors);
        
        for (int i = 0; i < factors.length;) {
            for (int j = i + 1; j < factors.length;) {
                if (j == i)
                    continue;

                int prod = factors[i] * factors[j];
                int[] copy = new int[factors.length - 1];
                int index = 0;
                for (int r = 0; r < factors.length; r++) {
                    if (r == i || r == j) continue;
                    copy[index] = factors[r];
                    index++;
                }
                copy[index] = prod;
                //shuffle prod back
                while (index > 0 && copy[index] < copy[index-1]) {
                    int temp = copy[index-1];
                    copy[index-1] = copy[index];
                    copy[index] = temp;
                    index--;
                }
                
                //copy[index] = prod;
                //Arrays.sort(copy);

                long res = factorPermute(copy);
                if (res > max)
                    max = res;
                
                int newJ = j+1;
                while (newJ < factors.length && factors[newJ] == factors[j])
                    newJ++;
                j = newJ;
            }
            int newI = i+1;
            while (newI < factors.length && factors[newI] == factors[i])
                newI++;
            i = newI;
        }
        
        return max;
    }
    
    
    
    
    
    public static long digitalRootSum(List<Integer> list) {
        long sum = 0;
        for (int n : list) {
            if (filledIn[n])
                sum += digitalRoot[n];
            else {
                long temp = MF.digitalRoot(n);
                filledIn[n] = true;
                digitalRoot[n] = temp;
                sum += temp;
            }
            
        }
        
        return sum;
    }
    public static long digitalRootSum(int[] list) {
        long sum = 0;
        for (int n : list) {
            if (filledIn[n])
                sum += digitalRoot[n];
            else {
                long temp = MF.digitalRoot(n);
                filledIn[n] = true;
                digitalRoot[n] = temp;
                sum += temp;
            }
            
        }
        
        return sum;
    }
    /*
    public static long digitalRootSum(List<Integer> list) {
        long sum = 0;
        for (int n : list)
            sum += MF.digitalRoot(n);
        return sum;
    }
    */
    
    
    
    
    
    public static List<Integer> addInOrder(List<Integer> list, Integer add) {
        if (list == null)
            return null;
        if (list.size() == 0) {
            list.add(add);
            return list;
        }
        if (list.get(0) > add) {
            list.add(0, add);
            return list;
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1) <= add && list.get(i) >= add) {
                list.add(i, add);
                return list;
            }
        }
        list.add(add);
        return list;
    }
    
    //approach
    //prime factorize all numbers less than one million (store beforehand)
    //for each prime factorization, generate all other ways to factorize
        //(combine different factors together until getting to the number)
        //at each step combine every possible pair
}
//20000 - answer: 256454  time = 30.670798579