package problem159;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import mathtools.MF;
import mathtools.PrimeFactorizationPrecomputer;

public class Try2 {



    static boolean[] filledIn = new boolean[1000020];
    static long[] digitalRoot = new long[1000020];

    public static void main(String[] args) {

        
        Random rng = new Random(System.currentTimeMillis());
        
        for (int n = 0; n < 100; n++) {
            
            int num = rng.nextInt(30000) + 211000;
            
            List<Long> factors = PrimeFactorizationPrecomputer.getPrimeFactorization(num);
            int[] factorsArr = new int[factors.size()];
            for (int i = 0; i < factors.size(); i++)
                factorsArr[i] = factors.get(i).intValue();
            MF.startTimer();
            
            //long mdrs = factorPermute(factorsArr);
            int[] mdrsFactors = getMDRSans(factorsArr);
            long mdrs = digitalRootSum(mdrsFactors);
            
            double time = MF.getElapsedSeconds();
            if (time > 1.0)
                System.out.println("--------LONG----------");
            
            int[] mdrsGuessArr = guessMDRS(factorsArr);
            
            System.out.print(num + "\t" + factors + "\tmdrs = " + mdrs + " : " + MF.listToString(mdrsFactors) + "\t guess " + MF.listToString(mdrsGuessArr));
            int[] mdrsGuessBest = getMDRSans(mdrsGuessArr);
            long mdrsGuess = digitalRootSum(mdrsGuessBest);
            System.out.print("\tmdrs = " + mdrsGuess + " : " + MF.listToString(mdrsGuessBest));
            if (mdrs != mdrsGuess) {
                System.out.println("BAAAAAAAAAAAAAAAAAAAAAAD");
            }
            else
                System.out.println("");
            
            
            
            
        }
        
        
        
        
    }

    
    public static long getMDRS(int[] factors) {
        
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

                long res = getMDRS(copy);
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
    public static int[] getMDRSans(int[] factors) {
        
        //System.out.println(factors);    //visit
        if (factors.length <= 1)
            return factors;
        
        int[] max = factors;
        long maxMDRS = digitalRootSum(max);
        
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

                int res[] = getMDRSans(copy);
                if (digitalRootSum(res) > maxMDRS) {
                    max = res;
                    maxMDRS = digitalRootSum(max);
                }
                
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
    
    
    /**
     * [2,2,2] -> [8] = 8
     * [2,2,2,2,3] -> [6,8] = 13
     * [2,3,3] -> [18] = 9
     * trade off with each combo
     * you cant get better than a digital root of 9 though
     * 311112
     * @param factors
     * @return 
     */
    public static int[] guessMDRS(int[] factors) {
        //combine 3 twos
        int numberOfTwos = 0;
        int numberOfThrees = 0;
        for (int i = 0; i < factors.length; i++) {
            if (factors[i] == 2) {
                numberOfTwos++;
                factors[i] = 0;
            }
            else if (factors[i] == 3) {
                numberOfThrees++;
                factors[i] = 0;
            }
        }
        int otherNumbers = factors.length - (numberOfTwos + numberOfThrees);
        
        int number8s = numberOfTwos / 3;
        numberOfTwos -= number8s * 3;
        
        int number9s = numberOfThrees / 2;
        numberOfThrees -= number9s * 2;
        
        int number6s = Math.min(numberOfTwos, numberOfThrees);
        numberOfTwos -= number6s;
        numberOfThrees -= number6s;
        
        int[] reducedGuess = new int[otherNumbers + numberOfTwos + numberOfThrees + number6s + number8s + number9s];
        int index = 0;
        for (int i = 0; i < factors.length; i++) {
            if (factors[i] != 0) {
                reducedGuess[index] = factors[i];
                index++;
            }
        }
        
        for (int n = 1; n <= numberOfTwos; n++) {
            reducedGuess[index] = 2;
            index++;
        }
        for (int n = 1; n <= numberOfThrees; n++) {
            reducedGuess[index] = 3;
            index++;
        }
        for (int n = 1; n <= number6s; n++) {
            reducedGuess[index] = 6;
            index++;
        }
        for (int n = 1; n <= number8s; n++) {
            reducedGuess[index] = 8;
            index++;
        }
        for (int n = 1; n <= number9s; n++) {
            reducedGuess[index] = 9;
            index++;
        }
        Arrays.sort(reducedGuess);
        
        return reducedGuess;
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