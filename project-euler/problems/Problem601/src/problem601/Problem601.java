package problem601;

public class Problem601 {

    /*
    For every positive number n we define the function streak(n)=k as the smallest positive integer kk such that n+k is not divisible by k+1.
    E.g:
    
    13 is divisible by 1 
    14 is divisible by 2 
    15 is divisible by 3 
    16 is divisible by 4 
    17 is NOT divisible by 5 
    So streak(13)=4
    
    Similarly:
    120 is divisible by 1 
    121 is NOT divisible by 2 
    So streak(120)=1

    Define P(s,N) to be the number of integers nn, 1<n<N for which streak(n)=s
    So P(3,14)=1 and P(6,106)=14286

    Find the sum, as i ranges from 1 to 31, of P(i,4^i)
    */
    public static void main(String[] args) {
        
        long totalAll = 0;
        
        for (long i = 1; i <= 31; i++) {
            System.out.print("P(" + i + ", " + (long)Math.pow(4, i) + ") = ");
            long res = P(i, (long)Math.pow(4, i));
            totalAll += res;
            System.out.println(res);
        }
        
        System.out.println("total: " + totalAll);
        
    }
    
    public static long P(long s, long n) {
        long total = 0;
        for (long num = 2; num < n; num++) {
            if (streak4(num) == s)
                total++;
        }
        return total;
    }
    
    public static long streak(long n) {
        if (n < 2)
            return -1;
        long divBy = 1;
        while (n % divBy == 0) {
            divBy++;
            n++;
        }
        
        return divBy - 1;
    }
    
    /**
     * A little more than 2x faster than streak() on average
     * @param n
     * @return 
     */
    public static long streak2(long n) {
        if (n < 2)
            return -1;
        
        if (n % 2 == 0)
            return 1;
        if ( (n-1) % 6 != 0)
            return 2;
        
        long divBy = 1;
        while (n % divBy == 0) {
            divBy++;
            n++;
        }
        
        return divBy - 1;
    }

    /**
     * About 2.3x faster than streak2() on average
     * @param n
     * @return 
     */
    public static long streak3(long n) {
        if (n < 2)
            return -1;
        
        if (n % 2 == 0)
            return 1;
        if ( (n-1) % 6 != 0)
            return 2;
        if ( (n-1) % 60 != 0) {
            if ( (n-1) % 12 != 0)
                return 3;
            return 4;
        }
        
        long divBy = 1;
        while (n % divBy == 0) {
            divBy++;
            n++;
        }
        
        return divBy - 1;
    }
    
    /**
     * About 1.25x faster than streak3() on average
     * @param n
     * @return 
     */
    public static long streak4(long n) {
        if (n < 2)
            return -1;
        
        if (n % 2 == 0)
            return 1;
        if ( (n-1) % 6 != 0)
            return 2;
        if ( (n-1) % 60 != 0) {
            if ( (n-1) % 12 != 0)
                return 3;
            return 4;
        }
        if ( (n-1) % 420 != 0)
            return 6;
        
        long divBy = 1;
        while (n % divBy == 0) {
            divBy++;
            n++;
        }
        
        return divBy - 1;
    }
}
/*
P(1, 4) = 1
P(2, 16) = 5
P(3, 64) = 5
P(4, 256) = 17
P(5, 1024) = 0
P(6, 4096) = 59
P(7, 16384) = 20
P(8, 65536) = 52
P(9, 262144) = 0
P(10, 1048576) = 379
P(11, 4194304) = 0
P(12, 16777216) = 559
P(13, 67108864) = 0
P(14, 268435456) = 0
P(15, 1073741824) = 1490
P(16, 4294967296) = 5609
P(17, 17179869184) = 0
P(18, 68719476736) = 5313
P(19, 274877906944) = 0
P(20, 1099511627776) = 0
*/