package problem549;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mathtools.MF;

public class Try2 {
    static int[][] primeFacTable;
    static final int tenPow8 = 100000000;
    static final int MAX = tenPow8 * 10;
    static Map<Integer, Integer> primeMap;  //prime to index
    
    public static void main(String[] args) {
        System.out.print("Calculating prime inverses... ");
        MF.startTimer();
        primeMap = new HashMap<>();
        int index = 0;
        for (long prime : MF.primes2) {
            if (prime > MAX)
                break;
            
            primeMap.put((int)prime, index);
            index++;
        }
        System.out.println("time = " + MF.getElapsedSeconds() + "\n");
        
        for (int size = tenPow8 / 1000; size <= tenPow8; size *= 10) {
            System.out.println("size: " + size);
        
            System.out.print("Calculating table... ");
            MF.startTimer();
            primeFacTable = MF.getPrimeFactorizationUnder2(size);
            System.out.println("time = " + MF.getElapsedSeconds());

            System.out.println("Calculating S()");
            //MF.startTimer();
            System.out.println("S4(" + size + ") = " + S4(size));
            //System.out.println("time = " + MF.getElapsedSeconds());
            
            System.out.println("");
        }
    }
    
    
    
    
    public static long S4(long max) {
        /*
        System.out.print("Time to get all prime factorizations... ");
        MF.startTimer();
        for (int n = 2; n <= max; n++) {
            List<Integer> facs = MF.getPrimeFactorizationUnder2Reconstruct(n, primeFacTable);
        }
        System.out.println("time: " + MF.getElapsedSeconds());
        */
        System.out.print("Calculating firsts... ");
        MF.startTimer();
        List<Integer> rollingTable = new ArrayList<>();
        int[][] firsts = new int[1230][27];     //1230 prime is 10009, 10009^2 > 10^8
                                //at most we will need 26 twos (floor log_2(10^8) = 26)
                                //for primes above 10000, they only appear at the prime number
        boolean[] increased = new boolean[1230];
        
        
        rollingTable.add(1);    //for n = 2
        increased[0] = true;
        
        for (int n = 3; n <= max; n++) {
            //visit
            for (int i = 0; i < 1230; i++) {
                if (increased[i] == true) {
                    increased[i] = false;
                    int j = rollingTable.get(i);
                    if (j >= 27)     //dont need
                        continue;
                    do {
                        firsts[i][j] = n-1;
                        j--;
                    } while (firsts[i][j] == 0 && j >= 1);
                }
            }
        
            //update rolling table
            List<Integer> primeFacs = MF.getPrimeFactorizationUnder2Reconstruct(n, primeFacTable);
            for (int prime: primeFacs) {
                if (prime > 10007)
                    continue;
                int index = primeMap.get(prime);
                //if (index >= 1230)      //dont need primes after 1230th prime
                //    continue;
                if (index >= rollingTable.size()) {
                    rollingTable.add(0);
                }
                rollingTable.set(index, rollingTable.get(index) + 1);
                increased[index] = true;
            }
                
            
        }
        
        
        //print firsts
        /*
        for (int y = 0; y < 10; y++) {
            System.out.printf("first%-6s: ", MF.primes2[y]);
            for (int x = 0; x < 27; x++) {
                System.out.printf("%-4d", firsts[y][x]);
            }
            System.out.println();
        }
        System.out.println("");
        */
        
        
        System.out.println("time: " + MF.getElapsedSeconds());
        System.out.print("Made firsts, calculating sn's... ");
        long timeToGetFacs = 0;
        long timeMakingFacCount = 0;
        long timeSN = 0;
        MF.startTimer();
        
        //calculate S(max)
        long total = 0;
        int[] facCount = new int[1230];
        for (int n = 2; n <= max; n++) {
            
            long startTime = System.currentTimeMillis();
            List<Integer> facs = MF.getPrimeFactorizationUnder2Reconstruct(n, primeFacTable);
            timeToGetFacs += System.currentTimeMillis() - startTime;
            
            startTime = System.currentTimeMillis();
            long sn = 0;
            for (int fac : facs) {
                //if (index >= facCount.length && MF.primes2[index] > sn)
                if (fac > 10007 && fac > sn) {
                    sn = fac;
                    continue;
                }
                int index = primeMap.get(fac);
                facCount[index]++;
                
            }
            timeMakingFacCount += System.currentTimeMillis() - startTime;
            
            //get firsts for all factors
            startTime = System.currentTimeMillis();
            
            for (int i = 0; i < facCount.length; i++) {
                if (facCount[i] == 0)
                    continue;
                //if (i >= firsts.length && MF.primes2[i] > sn)
                //    sn = MF.primes2[i];
                else if (firsts[i][facCount[i]] > sn)
                    sn = firsts[i][facCount[i]];
            }
            timeSN += System.currentTimeMillis() - startTime;
            
            total += sn;
            
            for (int i = 0; i < facCount.length; i++)
                facCount[i] = 0;
        }
    
        System.out.println("time : " + MF.getElapsedSeconds());
        System.out.println("time gettng facs: " + timeToGetFacs);
        System.out.println("time making facCount: " + timeMakingFacCount);
        System.out.println("time sn: " + timeSN);
        
        return total;
    }
    
    
    
    
    public static List<Integer[]> buildWholeTable(int max) {
        List<Integer[]> table = new ArrayList<>();
        for (int n = 2; n <= max; n++) {
            //copy from last row
            for (int i = 0; i < table.size(); i++) {
                table.get(i)[n] = table.get(i)[n-1];
            }
            //add new facs
            List<Integer> primeFacs = MF.getPrimeFactorizationUnder2Reconstruct(n, primeFacTable);
            
            for (int fac : primeFacs) {
                int indexOfFac = primeMap.get(fac);
                while (indexOfFac >= table.size()) {
                    Integer[] toAdd = new Integer[max+1];
                    Arrays.fill(toAdd, 0);
                    table.add(toAdd);
                }
                table.get(indexOfFac)[n]++;
            }
            
        }
        
        
        return table;
    }
}
/*
S(100)       = 2012
S(1000)      = 136817
S(10000)     = 10125843
S(100000)    = 793183093
S(1000000)   = 64938007616
S(10000000)  = 5494373412573
S(100000000) = 476001479068717   13 minutes
*/
/*
size: 1600000
Calculating table... time = 0.256907735
Calculating S()
Time to get all prime factorizations... time: 0.114255906
Calculating firsts... time: 1.395621407
Made firsts, calculating sn's... time : 10.440127445
time gettng facs: 300
time making facCount: 3109
time sn: 7004
S4(1600000) = 160304227994

size: 3200000
Calculating table... time = 0.600732275
Calculating S()
Time to get all prime factorizations... time: 1.205667439
Calculating firsts... time: 2.925357856
Made firsts, calculating sn's... time : 34.980837783
time gettng facs: 732
time making facCount: 9118
time sn: 25090
S4(3200000) = 609144919994
*/