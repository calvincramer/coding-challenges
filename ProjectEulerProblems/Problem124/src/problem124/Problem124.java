package problem124;

import java.util.List;
import mathtools.MF;

public class Problem124 {


    /*
    The radical of n, rad(n), is the product of the distinct prime factors of n. For example, 504 = 23 × 32 × 7, so rad(504) = 2 × 3 × 7 = 42.

    If we calculate rad(n) for 1 ≤ n ≤ 10, then sort them on rad(n), and sorting on n if the radical values are equal, we get:

    Let E(k) be the kth element in the sorted n column; for example, E(4) = 8 and E(6) = 9.

    If rad(n) is sorted for 1 ≤ n ≤ 100000, find E(10000).
    */
    public static void main(String[] args) {
        NPair[] list = new NPair[100001];    //1 indexed
        
        list[0] = new NPair(0,0);
        
        for (int i = 1; i < list.length; i++) {
            NPair temp = new NPair(i, rad(i));
            list[i] = temp;
        }
        
        MF.quickSort(list);
        NPair.sortRadN = false;
        //sort same rad(n)s
        for (int i = 0; i < list.length; i++) {
            int low = i;
            int high = low;
            while (list[high].radn == list[low].radn) {
                high++;
                if (high >= list.length)
                    break;
            }
            high--;
            MF.quickSort(list, low, high);
        }
        
        
        System.out.println("i\tn\trad(n)");
        for (int i = 0; i < list.length; i++) {
            System.out.println(i + "\t" + list[i]);
        }
        
        
    }
    
    
    
    public static long rad(long n) {
        if (n < 2)
            return 1;
        List<Long> pfs = MF.getPrimeFactorsOfFast(n);
        long res = 1;
        for (long pf : pfs)
            res *= pf;
        return res;
    }
    
    public static class NPair 
        implements Comparable<NPair> {
        public long n;
        public long radn;
        
        public static boolean sortRadN = true;
        
        public NPair(long n, long radn) {
            this.n = n;
            this.radn = radn;
        }
        
        @Override
        public String toString() {
            return "" + n + "\t" + radn;
        }

        @Override
        public int compareTo(NPair o) {
            if (sortRadN) {
                if (this.radn > o.radn)
                    return 1;
                else if (this.radn < o.radn)
                    return -1;
                else
                    return 0;
            } else {
                if (this.n > o.n)
                    return 1;
                else if (this.n < o.n)
                    return -1;
                else
                    return 0;
            }
        }
    }
}
/*
9998	5841	1947
9999	17523	1947
10000	21417	1947
10001	52569	1947
10002	64251	1947
*/