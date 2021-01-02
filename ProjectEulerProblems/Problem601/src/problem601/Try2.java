package problem601;

import mathtools.MF;

public class Try2 {

    public static void main(String[] args) {
        //timing
        
        for (long max = 10; max <= 100000000000L; max *= 10) {
            System.out.println(max);
            /*
            MF.startTimer();
            for (long n = 2; n < max; n++) {
                long res = streak(n);
            }
            System.out.println("streak: " + MF.getElapsedSeconds());

            MF.startTimer();
            for (long n = 2; n < max; n++) {
                long res = streak2(n);
            }
            System.out.println("streak2: " + MF.getElapsedSeconds());
            */
            MF.startTimer();
            for (long n = 2; n < max; n++) {
                long res = streak3(n);
            }
            System.out.println("streak3: " + MF.getElapsedSeconds());
            
            MF.startTimer();
            for (long n = 2; n < max; n++) {
                long res = streak4(n);
            }
            System.out.println("streak4: " + MF.getElapsedSeconds());
            
            MF.startTimer();
            for (long n = 2; n < max; n++) {
                long res = streak5(n);
            }
            System.out.println("streak5: " + MF.getElapsedSeconds());
            
            MF.startTimer();
            for (long n = 2; n < max; n++) {
                long res = streak6(n);
            }
            System.out.println("streak6: " + MF.getElapsedSeconds());
            
            
            System.out.println("");
        
        }
        System.out.println("done");
        
        
        
        //equality
        /*
        for (long n = 2; n < 50000000; n++) {
            if (streak(n) != streak2(n) || streak2(n) != streak3(n) || streak(n) != streak4(n) || streak4(n) != streak5(n) || streak5(n) != streak6(n))
                System.out.println(n + ": " + streak(n) + "\t" + streak(n) + "\t" + streak3(n) + "\t" + streak4(n) + "\t" + streak5(n) + "\t" + streak6(n));
        }
        System.out.println("done testing for equality");
        */

        
        //seeing pattern of streak for numbers n==1mod6 (7,13,19,25,31,...)
        //for (long n = 7; n < 10000; n+=6) {
        //    System.out.println(n + ": " + streak(n));
        //}
        
        //seeing pattern of streak for numbers n==1mod60 (61,121,181,241,...)
        //for (long n = 61; n < 20000; n+=60) {
        //    System.out.println(n + ": " + streak(n));
        //}
        
        //seeing pattern of streak for numbers n==1mod420 (421,841,1261,1681,...)
        //for (long n = 421; n < 80000; n+=420) {
        //    System.out.println(n + ": " + streak(n));
        //}
        
        //seeing pattern of streak for numbers n==1mod2520 (2521,5041,7561,10081,...)
        //for (long n = 2521; n < 1000000; n+=2520) {
        //    System.out.println(n + ": " + streak(n));
        //}
    }
    
    public static long P(long s, long n) {
        long total = 0;
        for (long num = 2; num < n; num++) {
            if (streak6(num) == s)
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
    
    public static long streak4(long n) {
        if (n < 2)
            return -1;
        
        if (n % 2 == 0)     //even numbers
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
        
        //actual calculation
        long divBy = 1;
        while (n % divBy == 0) {
            divBy++;
            n++;
        }
        
        return divBy - 1;
    }
    
    public static long streak5(long n) {
        if (n < 2)
            return -1;
        
        if (n % 2 == 0)     //even numbers
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
        
        if ( (n-1) % 2520 != 0) {
            if ( (n-1) % 840 != 0)
                return 7;
            return 8;
        }
        
        //actual calculation
        long divBy = 1;
        while (n % divBy == 0) {
            divBy++;
            n++;
        }
        
        return divBy - 1;
    }
    
    public static long streak6(long n) {
        if (n < 2)
            return -1;
        
        long nm1 = n-1;
        
        if (n % 2 == 0)     //even numbers
            return 1;
        
        if ( nm1 % 6 != 0)
            return 2;
        
        if ( nm1 % 60 != 0) {
            if ( nm1 % 12 != 0)
                return 3;
            return 4;
        }
        
        if ( nm1 % 420 != 0)
            return 6;
        
        if ( nm1 % 2520 != 0) {
            if ( nm1 % 840 != 0)
                return 7;
            return 8;
        }
        
        if ( nm1 % 27720 != 0)
            return 10;
        
        
        //actual calculation
        long divBy = 1;
        while (n % divBy == 0) {
            divBy++;
            n++;
        }
        
        return divBy - 1;
    }
}
/*
Lengthening the test cases for streak gives diminishing results
*/