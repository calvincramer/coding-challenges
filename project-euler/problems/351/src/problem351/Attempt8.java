package problem351;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Attempt8 {
    
    public static ArrayList<ArrayList<Long>> primeFactors;
    
    public static void main(String[] args) {
        //Faley's sequence
        
        //two ways:
        //generate the whole list
        //or use this idendity: |Fn| = |F(n-1)| + phi(n)        <-- phi = Eulers  totient function
        
        //for (int i = 0; i <= 100; i++)
        //    System.out.println("i="+i+"\tphi("+i+") = " + MF.eulersTotientFunction(i));
        //secondWay(false);
        
        /*
        long[] sizes = {5,10,1000,10000,100000,1000000,10000000,100000000};
        for (long size : sizes) {
            MF.startTimer();
            System.out.print(size + ": ");
            ArrayList<ArrayList<Long>> res = getAllPrimeFactorsUnder(size);
            //for (ArrayList<Long> num : res)
            //    System.out.println(num);
            System.out.println(MF.getElapsedSeconds());
        }
        */
        secondWay(false);
    }
    
    
    public static void secondWay(boolean printAlot) {
        long[] sizes = {5,10,1000,10000,100000,1000000,10000000,100000000};
        for (long size : sizes) {
            MF.startTimer();
            System.out.print("building prime factors... ");
            Attempt8.primeFactors = getAllPrimeFactorsUnder(size);
            System.out.println(MF.getElapsedSeconds());
            
            MF.startTimer();
        
            long n = 1;
            long fn = 1;    //(0/1),(1/1)
            while (n <= size) {
                fn += eulersTotientFunction(n);
                n++;
            }


            //calculate hidden
            if (printAlot)
                System.out.println("triangle can see: " + fn);
            long canSee = fn;
            canSee -= size + 1;
            canSee *= 6;
            canSee++;
            if (printAlot)
                System.out.println("can see: " + canSee);

            long total = (3 * size * (size - 1)) + 1;
            if (printAlot)
                System.out.println("total: " + total);
            //total += 17;
            long hn = total - canSee;

            System.out.println("H("+size+") = " + hn + "\t\t" + MF.getElapsedSeconds());
            System.out.println("");
        }
    }
    
    public static void fareyTest() {
        int n = 1;
        long fn = 1;    //(0/1),(1/1)
        System.out.println("n = " + n + "\tfn = " + fn);
        while (n <= 20) {
            fn += MF.eulersTotientFunction(n);
            n++;
            System.out.println("n = " + n + "\tfn = " + fn);
        }
    }
    
    public static ArrayList<ArrayList<Long>> getAllPrimeFactorsUnder(long max) {
        ArrayList<ArrayList<Long>> nums = new ArrayList<ArrayList<Long>>();
        nums.add(new ArrayList<Long>());    //0th elements
        for (int i = 1; i <= max; i++) {
            nums.add(new ArrayList<Long>());
        }
        
        List<Long> primes = MF.getPrimesUnder(max+1);
        for (long prime : primes) {
            nums.get((int)prime).add(prime);
            long n = prime * 2;
            while (n <= max) {
                nums.get((int)n).add(prime);
                n += prime;
            }
        }
        
        
        return nums;
        
    }
    
    public static long eulersTotientFunction(long num) {
        
        List<Long> distinctPrimeFactors = Attempt8.primeFactors.get((int)num);
        long prodTop = 1;
        long prodBottom = 1;
        if (distinctPrimeFactors == null)
            return num;
        for (long n : distinctPrimeFactors) {
            prodTop *= n-1;
            prodBottom *= n;
        }
        
        return num * prodTop / prodBottom;
    }
}

/*
building prime factors... 7.32922E-4
H(5) = 30		4.3852E-5

building prime factors... 2.9527E-5
H(10) = 138		1.1401E-5

building prime factors... 0.001243074
H(1000) = 1177848		8.02209E-4

building prime factors... 0.003703203
H(10000) = 117645084		0.001468769

building prime factors... 0.028657656
H(100000) = 11762395476		0.009153201

building prime factors... 1.092999025
H(1000000) = 1176221685648		0.064757384

building prime factors... 17.330222432
H(10000000) = 117621891436548		0.508018004

building prime factors... 3857.329404143
H(100000000) = 11762187201804552		121.28321803

*/