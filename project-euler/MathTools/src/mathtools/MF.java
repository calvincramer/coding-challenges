package mathtools;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.lang.UnsupportedOperationException;

public class MF {
    
    
    // <editor-fold defaultstate="collapsed" desc="absolute value">
    /**
     * Calculates the absolute value of a byte
     * @param num - the input number
     * @return The absolute value of the number
     */
    public static byte abs(byte num) {
        if (num < 0)
            return (byte) (num * -1);
        return num;
    }
    
    
    /**
     * Calculates the absolute value of a short
     * @param num - the input number
     * @return The absolute value of the number
     */
    public static short abs(short num) {
        if (num < 0)
            return (short) (num * -1);
        return num;
    }
    
    
    /**
     * Calculates the absolute value of an integer
     * @param num - the input number
     * @return The absolute value of the number
     */
    public static int abs(int num) {
        if (num < 0)
            return num * -1;
        return num;
    }
    
    
    /**
     * Calculates the absolute value of a long
     * @param num - the input number
     * @return The absolute value of the number
     */
    public static long abs(long num) {
        if (num < 0)
            return num * -1;
        return num;
    }
    
    
    /**
     * Calculates the absolute value of a float
     * @param num - the input number
     * @return The absolute value of the number
     */
    public static float abs(float num) {
        if (num < 0)
            return num * -1;
        return num;
    }
    
    
    /**
     * Calculates the absolute value of a double
     * @param num - the input number
     * @return The absolute value of the number
     */
    public static double abs(double num) {
        if (num < 0)
            return num * -1;
        return num;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="are relatively prime">
    /**
     * Determines if two numbers are relatively prime
     * @param n1 the first number
     * @param n2 the second number
     * @return whether or not any number greater than one can evenly divide both numbers
     */
    public static boolean areRelativelyPrime(byte n1, byte n2) {
        return gcf(n1,n2) == 1;
    }
    
    
    /**
     * Determines if two numbers are relatively prime
     * @param n1 the first number
     * @param n2 the second number
     * @return whether or not any number greater than one can evenly divide both numbers
     */
    public static boolean areRelativelyPrime(char n1, char n2) {
        return gcf(n1,n2) == 1;
    }
    
    
    /**
     * Determines if two numbers are relatively prime
     * @param n1 the first number
     * @param n2 the second number
     * @return whether or not any number greater than one can evenly divide both numbers
     */
    public static boolean areRelativelyPrime(short n1, short n2) {
        return gcf(n1,n2) == 1;
    }
    
    
    /**
     * Determines if two numbers are relatively prime
     * @param n1 the first number
     * @param n2 the second number
     * @return whether or not any number greater than one can evenly divide both numbers
     */
    public static boolean areRelativelyPrime(int n1, int n2) {
        return gcf(n1,n2) == 1;
    }
    
    
    /**
     * Determines if two numbers are relatively prime
     * @param n1 the first number
     * @param n2 the second number
     * @return whether or not any number greater than one can evenly divide both numbers
     */
    public static boolean areRelativelyPrime(long n1, long n2) {
        return gcf(n1,n2) == 1;
    }
    
    
    /**
     * Determines if two numbers are relatively prime
     * @param n1 the first number
     * @param n2 the second number
     * @return whether or not any number greater than one can evenly divide both numbers
     */
    public static boolean areRelativelyPrime(BigInteger n1, BigInteger n2) {
        return gcf(n1,n2).equals(BI_1);
    }
    // </editor-fold>
    
    
    /**
     * Calculates square root of a big integer
     * Equivalent to MF.sqrt()
     * Credit: <a href="http://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger">http://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger</a>
     * @param x the number to take the square root of
     * @return sqrt(x) floor
     * @throws IllegalArgumentException if x &lt; 0
     */
    public static BigInteger bigIntSqRootFloor(BigInteger x) throws IllegalArgumentException {
        if (x.compareTo(BI_0) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x.equals(BI_0) || x.equals(BI_1)) {
            return x;
        }
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
                y.compareTo(x.divide(y)) > 0;
                y = ((x.divide(y)).add(y)).divide(two));
        return y;
    } // end bigIntSqRootFloor

    
    /**
     * Calculates square root of a big integer
     * Credit: http://stackoverflow.com/questions/4407839/how-can-i-find-the-square-root-of-a-java-biginteger
     * @param x the number to take the square root of
     * @return sqrt(x) ceiling
     * @throws IllegalArgumentException if x &lt; 0
     */
    public static BigInteger bigIntSqRootCeil(BigInteger x) throws IllegalArgumentException {
        if (x.compareTo(BI_0) < 0) {
            throw new IllegalArgumentException("Negative argument.");
        }
        // square roots of 0 and 1 are trivial and
        // y == 0 will cause a divide-by-zero exception
        if (x == BI_0 || x == BI_1) {
            return x;
        }
        BigInteger two = BigInteger.valueOf(2L);
        BigInteger y;
        // starting with y = x / 2 avoids magnitude issues with x squared
        for (y = x.divide(two);
                y.compareTo(x.divide(y)) > 0;
                y = ((x.divide(y)).add(y)).divide(two));
        if (x.compareTo(y.multiply(y)) == 0) {
            return y;
        } else {
            return y.add(BI_1);
        }
    }
    

    /**
     * Calculates the distance between a points and a collection of points
     * @param p the base point
     * @param points the collection of points
     * @return The closest distance between p and the other point
     */
    public static double closestPoint(Point p, Point[] points) {
        if (points.length == 0)
            return Double.NaN;
        double min = p.squaredDistanceTo(points[0]);    //store the min distance squared
        for (int i = 1; i < points.length; i++) {
            double tempDist = p.squaredDistanceTo(points[i]);
            if (tempDist < min)
                min = tempDist;
        }
        
        return Math.sqrt(min);
    }
    
    
    /**
     * Calculates the distance between a points and a collection of points
     * @param p the base point
     * @param points the collection of points
     * @return The closest distance between p and the other point
     */
    public static double closestPoint(Point p, List<Point> points) {
        Point[] pnts = points.toArray(new Point[points.size()]);
        return MF.closestPoint(p, pnts);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="commifyNumber">
    /**
     * Inserts commas into numbers to make them more readable
     * @param n the number
     * @return a better version of the number
     */
    public static String commifyNumber(long n) {
        String s = "";
        String nStr = "" + n;
        int c = 0;
        int i = nStr.length() - 1;
        while (i >= 0) {
            s = nStr.charAt(i) + s;
            if (c >= 2 && i > 0) {
                s = "," + s;
                c = 0;
            }
            else
                c++;
            i--;
        }
        return s;
    }
    
    
    /**
     * Inserts commas into numbers to make them more readable
     * @param n the number
     * @return a better version of the number
     */
    public static String commifyNumber(byte n) {
        return commifyNumber((long) n);
    }
    
    
    /**
     * Inserts commas into numbers to make them more readable
     * @param n the number
     * @return a better version of the number
     */
    public static String commifyNumber(short n) {
        return commifyNumber((long) n);
    }
    
    
    /**
     * Inserts commas into numbers to make them more readable
     * @param n the number
     * @return a better version of the number
     */
    public static String commifyNumber(int n) {
        return commifyNumber((long) n);
    }
    
    
    /**
     * Inserts commas into numbers to make them more readable
     * @param n the number
     * @return a better version of the number
     */
    public static String commifyNumber(float n) {
        return commifyNumber((double) n);
    }
    
    
    /**
     * Inserts commas into numbers to make them more readable
     * @param n the number
     * @return a better version of the number
     */
    public static String commifyNumber(double n) {
        String leftSide = commifyNumber((long) n);
        double rightSide = (n - ((long) n)); 
        return leftSide + ("" + rightSide).substring(1);    //takes off the leading zero
    }
    // </editor-fold>
    
    
    /**
     * Calculated the digital root sum of a number, which is the sum of the digits of the number if the sum is less than 10
     * If the sum is greater or equal to 10, the sum becomes the new number until the new sum is less than 10
     * About 2.5x factor than digitalRootOld()
     * @param number input number
     * @return digital root sum
     */
    public static long digitalRoot(long number) {
        long res = number % 9;
        if (res == 0) return 9;
        else    return res;
    }
    
    
    /**
     * Calculated the digital root sum of a number, which is the sum of the digits of the number if the sum is less than 10
     * If the sum is greater or equal to 10, the sum becomes the new number until the new sum is less than 10
     * @param number input number
     * @return digital root sum
     */
    public static long digitalRootOld(long number) {
        long sum = MF.sumOfDigits(number);
        while (sum >= 10) {
            number = sum;
            sum = MF.sumOfDigits(number);
        }
        return sum;
    }

    
    /**
     * Estimates the square root of 2, with a given accuracy
     * @param accuracy 1 to about 50. Numbers greater than 50 may end up exceeding the bounds of long.
     * @return an estimate of sqrt2
     */
    public static Fraction estimateSqrt2(int accuracy) {
        if (accuracy < 1)
            accuracy = 1;
        
        Fraction esti = new Fraction(1, 2);
        
        for (int n = 1; n < accuracy; n++) {    //do accuracy minus 1 times
            //2 + fraction
            //invert
            esti.num += 2 * esti.dnm;
            esti.invertFraction();
        }
        //add 1
        esti.num += 1 * esti.dnm;
        
        return esti;
    }
    
    
    /**
     * Estimates the square root of 2, with a given accuracy
     * @param accuracy 1 to Integer.MAX
     * @return an estimate of sqrt2
     */
    public static BigFraction estimateSqrt2Large(int accuracy) {
        if (accuracy < 1)
            accuracy = 1;
        
        BigFraction esti = new BigFraction(BI_1, new BigInteger("2"));
        BigInteger two = new BigInteger("2");
        
        for (int n = 1; n < accuracy; n++) {    //do accuracy minus 1 times
            //2 + fraction
            //invert
            esti.num = esti.num.add(two.multiply(esti.dnm));
            esti.invertFraction();
        }
        //add 1
        esti.num = esti.num.add(esti.dnm);
        
        return esti;
    }
    
    
    /**
     * Euler's Totient function. Determines the number of numbers less than num that are co-prime to num.
     * @param num n
     * @return Euler's totient (n) 
     */
    public static long eulersTotientFunction(long num) {
        
        List<Long> distinctPrimeFactors = MF.getPrimeFactorsOfFaster(num);  //experimental, may need to go back to "fast" method
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
    
    
    // <editor-fold defaultstate="collapsed" desc="absolute value">
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(boolean n) {
        return BI_1;
    }
    
    
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(byte n) {
        if (n < 0)
            return BI_0;
        if (n == 0)
            return BI_1;

        BigInteger num = new BigInteger(n + "");
        BigInteger res = BI_1;

        for (BigInteger i = BI_1; i.compareTo(num) <= 0; i = i.add(BI_1))
            res = res.multiply(i);

        return res;
    }
    
    
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(char n) {
        if (n < 0)
            return BI_0;
        if (n == 0)
            return BI_1;

        BigInteger num = new BigInteger(n + "");
        BigInteger res = BI_1;

        for (BigInteger i = BI_1; i.compareTo(num) <= 0; i = i.add(BI_1))
            res = res.multiply(i);

        return res;
    }
    
    
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(short n) {
        if (n < 0)
            return BI_0;
        if (n == 0)
            return BI_1;

        BigInteger num = new BigInteger(n + "");
        BigInteger res = BI_1;

        for (BigInteger i = BI_1; i.compareTo(num) <= 0; i = i.add(BI_1))
            res = res.multiply(i);

        return res;
    }
    
    
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(int n) {
        if (n < 0)
            return BI_0;
        if (n == 0)
            return BI_1;

        BigInteger num = new BigInteger(n + "");
        BigInteger res = BI_1;

        for (BigInteger i = BI_1; i.compareTo(num) <= 0; i = i.add(BI_1))
            res = res.multiply(i);

        return res;
    }
    
    
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(long n) {
        if (n < 0)
            return BI_0;
        if (n == 0)
            return BI_1;

        BigInteger num = new BigInteger(n + "");
        BigInteger res = BI_1;

        for (BigInteger i = BI_1; i.compareTo(num) <= 0; i = i.add(BI_1))
            res = res.multiply(i);

        return res;
    }
    
    
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(float n) {
        System.out.println("GAMMA FUNCTION NOT IMPLEMENTED");
        return BI_0;
    }
    
    
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(double n) {
        System.out.println("GAMMA FUNCTION NOT IMPLEMENTED");
        return BI_0;
    }
    
    
    /**
     * Calculates n!
     * @param n the input number
     * @return n factorial
     */
    public static BigInteger factorial(BigInteger n) {
        if (n.compareTo(BI_0) < 0)
            return BI_0;
        if (n.equals(BI_0))
            return BI_1;

        BigInteger num = n;
        BigInteger res = BI_1;

        for (BigInteger i = BI_1; i.compareTo(num) <= 0; i = i.add(BI_1))
            res = res.multiply(i);

        return res;
    }
    // </editor-fold>
    
    
    /**
     * Returns a string of the desired length in characters, right justified.
     * @param string input string
     * @param length number of characters
     * @return a string with at least length characters
     */
    public static String fixedWidthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }
    
    
    /**
     * Returns a string of the desired length in characters, left justified.
     * @param string input string
     * @param length number of characters
     * @return a string with at least length characters
     */
    public static String fixedWidthStringLeftJustified(String string, int length) {
        return String.format("%-" + length + "s", string);
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="greatest common factor">
    /**
     * Finds the greatest common factor of two numbers
     * @param n1 the first number
     * @param n2 the second number
     * @return the greatest number that evenly divides both numbers
     */
    public static byte gcf(byte n1, byte n2) {
        if (n1 == 0)
            return n2;

        while (n2 != 0) {
            byte r = (byte) (n1 % n2);
            n1 = n2;
            n2 = r;
        }
        
        return n1;
    }
    
    
    /**
     * Finds the greatest common factor of two numbers
     * @param n1 the first number
     * @param n2 the second number
     * @return the greatest number that evenly divides both numbers
     */
    public static char gcf(char n1, char n2) {
        if (n1 == 0)
            return n2;

        while (n2 != 0) {
            char r = (char) (n1 % n2);
            n1 = n2;
            n2 = r;
        }
        
        return n1;
    }
    
    
    /**
     * Finds the greatest common factor of two numbers
     * @param n1 the first number
     * @param n2 the second number
     * @return the greatest number that evenly divides both numbers
     */
    public static short gcf(short n1, short n2) {
        if (n1 == 0)
            return n2;

        while (n2 != 0) {
            short r = (short) (n1 % n2);
            n1 = n2;
            n2 = r;
        }
        
        return n1;
    }
    
    
    /**
     * Finds the greatest common factor of two numbers
     * @param n1 the first number
     * @param n2 the second number
     * @return the greatest number that evenly divides both numbers
     */
    public static int gcf(int n1, int n2) {
        if (n1 == 0)
            return n2;

        while (n2 != 0) {
            int r = n1 % n2;
            n1 = n2;
            n2 = r;
        }
        
        return n1;
    }
    
    
    /**
     * Finds the greatest common factor of two numbers
     * @param n1 the first number
     * @param n2 the second number
     * @return the greatest number that evenly divides both numbers
     */
    public static long gcf(long n1, long n2) {
        if (n1 == 0)
            return n2;

        while (n2 != 0) {
            long r = n1 % n2;
            n1 = n2;
            n2 = r;
        }
        
        return n1;
    }
    
    
    /**
     * Finds the greatest common factor of two numbers
     * @param n1 first number
     * @param n2 second number
     * @return the gcf of both number
     */
    public static BigInteger gcf(BigInteger n1, BigInteger n2) {
        if (n1.equals(BI_0))
            return n2;

        while (!n2.equals(BI_0)) {
            BigInteger r = n1.remainder(n2);
            n1 = n2;
            n2 = r;
        }
        
        return n1;
    }
    // </editor-fold>
    
    
    /**
     * Calculates the amount of time elapsed, expressed in seconds.
     * @return The seconds that have passed.
     */
    public static double getElapsedSeconds() {
        return (System.nanoTime() - MF.startTimeNano)/ 1000000000.0;
        
        /*Figured its better to not mess with timing. Could have weird results.
        double t = (System.nanoTime() - MF.startTimeNano)/ 1000000000.0;
        
        t -= 1.60E-8;   //average time that it takes to start/ stop timer
        if (t < 0)
            return 0;
        return t;
        */
    }
    
    
    /**
     * Calculates the amount of time elapsed, expressed in seconds.
     * @param digitPrecision The number of digits after the decimal place wanted.
     * @return The seconds that have passed.
     */
    public static double getElapsedSeconds(int digitPrecision) {
        double time = (System.nanoTime() - MF.startTimeNano)/ 1000000000.0;
        if (digitPrecision < 0)
            digitPrecision = 0;
        long mult = (long) Math.pow(10, digitPrecision);
        
        return Math.floor(time * mult) / mult;
    }
    
    
    /**
     * Calculates the difference between the start time and current time
     * @return the elapsed time in milliseconds
     */
    public static long getElapsedTime() {
        return System.currentTimeMillis() - MF.startTime;
    }
    

    /**
     * Calculates the factors of a number
     * Very slow : O(n/2)
     * @param n input number
     * @return the number of factors
     */
    public static List<Integer> getFactorsOf(Integer n) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        factors.add(n); //every number is a factor of itself

        return factors;
    }
    
    
    /**
     * Calculates the factors of a number. Option for including one and the number itself. As every number is evenly divisible by one and itself.
     * Very slow : O(n/2)
     * @param n input number
     * @param includeOne whether to include 1
     * @param includeSelf whether to include n
     * @return the number of factors
     */
    public static List<Integer> getFactorsOf(Integer n, boolean includeOne, boolean includeSelf) {
        List<Integer> factors = new ArrayList<>();

        int start = includeOne ? 1 : 2;

        for (int i = start; i <= n / 2; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        if (includeSelf) {
            factors.add(n);
        }

        return factors;
    }
    
    
    /**
     * Returns the factors of an integer, O(sqrt(n))
     * @param n input number
     * @param includeOne whether to include 1
     * @param includeSelf whether to include n
     * @return the number of factors
     */
    public static List<Integer> getFactorsOfFaster(Integer n, boolean includeOne, boolean includeSelf) {
        
        if (n < 1)
            return new ArrayList<Integer>();
        if (n == 1)
            return Arrays.asList(1);
        
        List<Integer> factors = new ArrayList<>();
        for (int num = 2; num < (int) Math.ceil(Math.sqrt(n)); num++)
            if (n % num == 0)
                factors.add(num);
        
        boolean isSquare = isPerfectSquare(n);
        if (isSquare)
            factors.add((int) Math.sqrt(n));
        int start = isSquare ? factors.size() - 2: factors.size() - 1;
        
        for (int i = start; i >= 0; i--)
            factors.add( n / factors.get(i));
        
        if (includeOne)
            factors.add(0, 1);
        if (includeSelf)
            factors.add(n);
        
        return factors;
    }
    
    
    /**
     * Returns the factors of an integer, O(sqrt(n))
     * @param n input number
     * @param includeOne whether to include 1
     * @param includeSelf whether to include n
     * @return the number of factors
     */
    public static List<Long> getFactorsOfFaster(Long n, boolean includeOne, boolean includeSelf) {
        
        if (n < 1)
            return new ArrayList<Long>();
        if (n == 1)
            return Arrays.asList(1L);
        
        List<Long> factors = new ArrayList<>();
        for (long num = 2; num < (long) Math.ceil(Math.sqrt(n)); num++)
            if (n % num == 0)
                factors.add(num);
        
        boolean isSquare = isPerfectSquare(n);
        if (isSquare)
            factors.add((long) Math.sqrt(n));
        int start = isSquare ? factors.size() - 2: factors.size() - 1;
        
        for (int i = start; i >= 0; i--)
            factors.add( n / factors.get(i));
        
        if (includeOne)
            factors.add(0, 1L);
        if (includeSelf)
            factors.add(n);
        
        return factors;
    }
    
    
    /**
     * Gets the first digit of the number
     * @param number input number
     * @return the first digit (leftmost digit)
     */
    public static int getFirstDigit(int number) {
        String s = String.valueOf(Math.abs(number));
        char c = s.toCharArray()[0];
        return Integer.valueOf(c + "");
    }
    
    
    /**
     * Calculates the first factor of a given number
     * If the number is non-positive, 1 is returned
     * If the number is 1, then 1 is returned
     * If the number is larger than 17 million squared, -1 is returned
     * If the number is prime, then 1 is returned
     * If the number is not prime, then the smallest factor is returned
     * @param number the input number
     * @return Smallest factor of the number, if possible
     */
    public static long getFirstFactor(long number) {
        if (number <= 1)
            return 1;
        if (MF.isPrimeByPrimes4(number))
            return 1;
        
        if (number >= MF.PRIMES_UP_TO * MF.PRIMES_UP_TO) {
            System.out.println("NUMBER MAY BE TOO BIG (> 17,000,000 squared, COULD POSSIBLY NOT FIND FIRST FACTOR");
            return -1;
        }
        
        for (long p : MF.primes2) {
            if (number % p == 0)
                return p;
        }
        
        System.out.println("could not find first factor!");
        return -1;
    }
    
    
    /**
     * Calculates the unique prime factors of a number
     * Don't use -- O(n/2)
     * @param n input number
     * @return a list of unique prime factors
     */
    public static List<Long> getPrimeFactorsOf(Long n) {
        if (n < 2)
            return new ArrayList<Long>();
        List<Long> factors = new ArrayList<>();

        for (long i = 2; i <= n / 2; i++) {
            if (n % i == 0 && isPrimeFaster(i)) {
                factors.add(i);
            }
        }
        if (MF.isPrimeFaster(n))
            factors.add((long)n);
        
        return factors;
    }
    
    
    /** Calculates the prime factorization of a number
     *  examples: 24 == 2 * 2 * 2 * 3
     * @param n input number
     * @return List of prime factors
     */
    public static List<Long> getPrimeFactorization(Long n) {
        if (n < 2)
            return new ArrayList<Long>();
        List<Long> factors = new ArrayList<>();
        while (!MF.isPrimeByPrimes2(n) && n != 1) {
            for (long p : primes2) {
                if (n % p == 0) {
                    n = n / p;
                    factors.add(p);
                }
                else if (n == 1)
                    break;
                else if (p > n)
                    break;
            }
        }
        if (n != 1)
            factors.add(n);
        factors.sort(null);
        return factors;
    }
    
    
    /**
     * Calculates all the prime factorizations for the numbers 1 to upTo
     * @param upTo maximum, inclusive
     * @return Array of Lists, indexed by number
     */
    public static List<Integer>[] getPrimeFactorizationUnder(int upTo) {
        //System.out.print("init arrays: ");
        //MF.startTimer();
        List<Integer>[] factorTable = (ArrayList<Integer>[]) new ArrayList[upTo+1];
        int[] nums = new int[upTo+1];
        //System.out.println(MF.getElapsedSeconds());
        
        //System.out.print("more init arrays: ");
        //MF.startTimer();
        for (int i = 0; i < factorTable.length; i++) {
            factorTable[i] = new ArrayList();
            nums[i] = i;
        }
        //System.out.println(MF.getElapsedSeconds());
        
        //System.out.println("filling in values: ");
        //MF.startTimer();
        //int printAt = upTo / 10;
        //int count = 0;
        for (int n = 2; n < factorTable.length; n++) {
            //nums[n] expected to be prime (or 1)
            if (nums[n] != 1)
                factorTable[n].add(n);
            for (int r = n + n; r < factorTable.length; r += n) {
                while (nums[r] % n == 0) {
                    nums[r] /= n;
                    factorTable[r].add(n);
                }
            }
            factorTable[n].sort(null);
            /*
            if (printAt == n) {
                printAt += upTo / 10;
                count ++;
                System.out.println((count * 10) + "%");
                Thread.yield();
                System.out.flush();
            } 
            */
        }
        //System.out.println(MF.getElapsedSeconds());
        
        return factorTable;
    }
    
    
    /**
     * Calculates all the prime factorizations for the numbers 1 to upTo
     * Use getPrimeFactorizationUnder2Reconstruct() to reconstruct the actual list of prime factors for a number
     * @param upTo maximum, inclusive
     * @return 2d array, two columns, first is the first prime factor, the second is the 
     * index of the rest of the prime factors (or -1) so 24 = 2, 12 -&gt; 2, 6 -&gt; 2, 3, -1
     */
    public static int[][] getPrimeFactorizationUnder2(int upTo) {
        int[][] table = new int[upTo+1][2];
        final int FIRST_FACTOR_IND = 0;
        final int POINTER_IND = 1;
        table[0][0] = 0;
        table[0][1] = -1;
        table[1][0] = 1;
        table[1][1] = -1;
        
        for (int n = 2; n <= upTo; n++) {
            if (table[n][FIRST_FACTOR_IND] != 0)
                continue;
            
            if (MF.isPrimeByPrimes4(n)) {
                table[n][FIRST_FACTOR_IND] = n;
                table[n][POINTER_IND] = -1;
            }
            else {
                //find first factor
                int factor = -2;
                for (long l : MF.primes2) {
                    if (n % l == 0) {
                        factor = (int) l;
                        break;
                    }
                    else if (l > n)
                        break;
                }
                table[n][FIRST_FACTOR_IND] = factor;
                table[n][POINTER_IND] = n / factor;
            }
            //walk up
            for (int r = n + n; r <= upTo; r += n) {
                if (table[r][FIRST_FACTOR_IND] != 0)
                    continue;
                table[r][FIRST_FACTOR_IND] = n;
                table[r][POINTER_IND] = r / n;
            }
        }
        return table;
    }
    
    
    /**
     * Reconstructs the list of prime factors given by getPrimeFactorizationUnder2
     * @param n - the number to start with
     * @param table - returned by getPrimeFactorizationUnder2
     * @return the list of prime factors of n
     */
    public static List<Integer> getPrimeFactorizationUnder2Reconstruct(int n, int[][] table) {
        List<Integer> facs = new ArrayList<>();
        if (n < 2)
            return facs;
        
        facs.add(table[(int)n][0]);
        while (table[(int)n][1] != -1) {
            n = table[(int)n][1];
            facs.add(table[(int)n][0]);
        }
        return facs;
    }
    
    
    /**
     * Calculates the unique prime factors of a number
     * @param n input number
     * @return list of prime factors
     */
    public static List<Long> getPrimeFactorsOfFast(Long n) {
        if (n < 2)
            return new ArrayList<Long>();
        List<Long> factors = new ArrayList<>();
        if (MF.isPrime(n)) {
            factors.add((long)n);
            return factors;     //a prime number on has itself as a prime factor
        }

        for (long prime : MF.primes) {
            if (prime > n / 2)
                break;
            if (n % prime == 0)
                factors.add(prime);
        }
        
        

        return factors;
    }
    
    
    /**
     * Calculates the unique prime factors of a number
     * @param n input number
     * @return list of prime factors
     */
    public static List<Long> getPrimeFactorsOfFaster(Long n) {
        if (n < 2)
            return new ArrayList<Long>();
        List<Long> factors = PrimeFactorizationPrecomputer.getPrimeFactorization(n);
        
        int i = 0;
        while (i < factors.size()) {
            int j = i + 1;
            while (j < factors.size() && factors.get(i).equals(factors.get(j)))
                factors.remove(j);
            i++;
        }
        
        return factors;
    }
    
    
    /**
     * Calculates a list of primes under a given number. Implements with the sieve of Eratothenes.
     * @param number input number
     * @return list of primes under number
     */
    public static List<Long> getPrimesUnder(int number) {
        if (number < 2)
            return null;
        
        //sieve of eratothenes 
        long[] naturals = new long[(int) number];
        for (int i = 0; i < naturals.length; i++)
            naturals[i] = i;
        naturals[1] = 0;    //1 isn't prime
        
        for (int i = 2; i < naturals.length; i++) {
            if (naturals[i] == 0)
                continue;
            int r = i + i;
            while (r < naturals.length) {
                naturals[r] = 0;
                r += i;
            }
            
        }
        
        List<Long> primesTemp = new ArrayList<>();
        for (long num : naturals)
            if (num != 0)
                primesTemp.add(num);
        
        return primesTemp;
    }
    
    
    /**
     * Calculates a list of primes under a given number. Implements with the sieve of Eratothenes.
     * @param number input number
     * @return list of primes under number
     */
    public static List<Long> getPrimesUnder(long number) {
        if (number < 2)
            return null;
        
        //sieve of eratothenes 
        long[] naturals = new long[(int) number];
        for (int i = 0; i < naturals.length; i++)
            naturals[i] = i;
        naturals[1] = 0;    //1 isn't prime
        
        for (int i = 2; i < naturals.length; i++) {
            if (naturals[i] == 0)
                continue;
            int r = i + i;
            while (r < naturals.length) {
                naturals[r] = 0;
                r += i;
            }
            
        }
        
        List<Long> primesTemp = new ArrayList<>();
        for (long num : naturals)
            if (num != 0)
                primesTemp.add(num);
        
        return primesTemp;
    }
    
    
    /**
     * Slower method to get a list of primes under a given value.
     * See getPrimesUnder()
     * @param number input number
     * @return list of primes under number
     */
    public static List<Long> getPrimesUnderSlow(int number) {
        System.out.println("DO NOT USE ME");
        List<Long> primesTemp = new ArrayList<>();
        for (int i = 2; i < number; i++) {
            if (MF.isPrimeFast(i))
                primesTemp.add((long) i);
        }
        return primesTemp;
    }
    
    
    /**
     * Determines if a number is "bouncy".
     * A bouncy number is one that is not strictly increasing or decreasing in digits
     * ex. 1437
     * @param num input number
     * @return whether the number is bouncy or not
     */
    public static boolean isBouncy(int num) {
        return !isIncreasing(num) && !isDecreasing(num);
    }
    
    
    /**
     * Determines whether the number is circularly prime.
     * Circular prime: https://en.wikipedia.org/wiki/Circular_prime
     * @param number input number
     * @return whether the number is circularly prime
     */
    public static boolean isCircularPrime(int number) {
        if (!isPrime(number))   return false;
        if (number < 10)        return isPrime(number);

        List<Integer> digits = new ArrayList<>();
        int n = number;
        while (n > 0) {
            digits.add(0, n % 10);
            n /= 10;
        }

        //cycle the number
        for (int i = 1; i <= digits.size(); i++) {
            int dig = digits.remove(0);
            digits.add(dig);    //to end

            int cycledNum = 0;
            int scale = 1;
            for (int k = digits.size() - 1; k >= 0; k--) {
                cycledNum += scale * digits.get(k);
                scale *= 10;
            }

            if (!isPrime(cycledNum)) {
                return false;
            }

        }

        return true;
    }
    
    
    /**
     * Determines if a numbers digits are strictly decreasing from left to right.
     * ex. 965331 decreasing
     * ex. 2942 not decreasing
     * @param num input number
     * @return whether the number is decreasing is each digit
     */
    public static boolean isDecreasing(int num) {
        char[] charDigs = (""+num).toCharArray();
        int[] digs = new int[charDigs.length];
        for (int i = 0; i < digs.length; i++) {
          digs[i] = Integer.parseInt(""+charDigs[i]);
        }

        for (int i = 0; i < digs.length - 1; i++) {
          if (digs[i] < digs[i+1])
            return false;
        }

        return true;
    }
    
    
    /**
     * Determines if a numbers digits are strictly increasing from left to right.
     * ex. 146678 is increasing
     * ex. 1740 is not increasing
     * @param num input number
     * @return whether the number is increasing is each digit
     */
    public static boolean isIncreasing(int num) {
        char[] charDigs = (""+num).toCharArray();
        int[] digs = new int[charDigs.length];
        for (int i = 0; i < digs.length; i++) {
          digs[i] = Integer.parseInt(""+charDigs[i]);
        }

        for (int i = 0; i < digs.length - 1; i++) {
          if (digs[i] > digs[i+1])
            return false;
        }

        return true;
    }
    
    
    /**
     * Determines whether a number is a Lychrel number.
     * A number is a Lychrel number when it eventually forms a palindrome by adding the reverse of itself.
     * The reverse-add process is repeated fifty times.
     * @param n input number
     * @return is a Lychrel number
     */
    public static boolean isLychrelNumber(int n) {
        BigInteger number = new BigInteger((n+reverseNumber(n))+"");
        final int maxTests = 50;
        int iteration = 1;
        
        while (iteration < maxTests) {
            if(isPalindrome(number.toString()))
                return false;
            else 
                number = number.add(reverseNumber(number));
            
            iteration++;
        }
        
        
        return true;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="is palindrome">
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(boolean n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(byte n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(char n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(short n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(int n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(long n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(float n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(double n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(BigInteger n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the number is palindromic. 
     * @param n the input number
     * @return whether the number can be read forwards and backwards the same
     */
    public static boolean isPalindrome(BigDecimal n) {
        return isPalindrome(n + "");
    }
    
    
    /**
     * Determines whether the String is palindromic. 
     * @param str the input string
     * @return whether the string can be read forwards and backwards the same
     */
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Old method of determining if a number is palindromic.
     * Approximately 10x slower than MathFunctions.isPalindrome()
     * @param n input number
     * @return whether the number is a palindrome
     */
    public static boolean isPalindromeNumber(int n) {
        int maxDigits = (int) Math.log10(n) + 1;
        int r = 1;
        int l = maxDigits;

        while (r < l) {
            int digitRight = (int) ((n / Math.pow(10, r - 1)) % 10);
            int digitLeft = (int) ((n / Math.pow(10, l - 1)) % 10);

            if (digitRight != digitLeft) {
                return false;
            }
            r++;
            l--;
        }
        return true;
    }
    // </editor-fold>
    
    
    /**
     * Determines if a number is pandigital (all digits from lowNum to highNum occur exactly once)
     * @param str input string
     * @param lowNum lowest number to check for
     * @param highNum highest number to check for
     * @return whether pandigital in the given range
     */
    public static boolean isPandigital(String str, int lowNum, int highNum) {
        if (str.length() != highNum - lowNum + 1) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) //no zeroes allowed
        {
            if (Character.getNumericValue(str.charAt(i)) < lowNum
                    || Character.getNumericValue(str.charAt(i)) > highNum) {
                return false;
            }
        }

        boolean[] numOccur = new boolean[10];   //index of 1 = number 1 occurred
        for (int i = 0; i < str.length(); i++) {
            int num = Character.getNumericValue(str.charAt(i));
            if (numOccur[num] == true) //if already found the number
            {
                return false;
            } else {
                numOccur[num] = true;
            }
        }

        return true;
    }
    

    // <editor-fold defaultstate="collapsed" desc="isPerfectSquare">
    /**
     * Determines if a number is a perfect square, ie the square root of the number has remainder of zero.
     * @param n the number
     * @return whether the square of the square-root of the number is equal to the number
     */
    public static boolean isPerfectSquare(byte n) {
        byte sqrt = (byte) Math.sqrt(n);
        return n - (sqrt * sqrt) == 0;
    }
    
    
    /**
     * Determines if a number is a perfect square, ie the square root of the number has remainder of zero.
     * @param n the number
     * @return whether the square of the square-root of the number is equal to the number
     */
    public static boolean isPerfectSquare(short n) {
        short sqrt = (short) Math.sqrt(n);
        return n - (sqrt * sqrt) == 0;
    }
    
    
    /**
     * Determines if a number is a perfect square, ie the square root of the number has remainder of zero.
     * @param n the number
     * @return whether the square of the square-root of the number is equal to the number
     */
    public static boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return n - (sqrt * sqrt) == 0;
    }
    
    
    /**
     * Determines if a number is a perfect square, ie the square root of the number has remainder of zero.
     * @param n the number
     * @return whether the square of the square-root of the number is equal to the number
     */
    public static boolean isPerfectSquare(long n) {
        long sqrt = (long) Math.sqrt(n);
        return n - (sqrt * sqrt) == 0;
    }
    
    
    /**
     * Determines if a number is a perfect square, ie the square root of the number has remainder of zero.
     * @param n input number
     * @return whether the number is a perfect square
     */
    public static boolean isPerfectSquare(BigInteger n) {
        BigInteger sqrtFloor = MF.sqrt(n);
        return sqrtFloor.pow(2).equals(n);
    }
    
    
    /**
     * Determines if a number is a perfect square, ie the square root of the number has remainder of zero.
     * @param n input number
     * @return whether the number is a perfect square
     */
    public static boolean isPerfectSquare2(BigInteger n) {
        BigInteger sqrtFloor = MF.bigIntSqRootFloor(n);
        return sqrtFloor.pow(2).equals(n);
    }
    
    
    /**
     * Tries to first parse the string into a number, if it is not a number, then it returns false.
     * Determines if a number is a perfect square, ie the square root of the number has remainder of zero.
     * @param num the string hopefully representing a number
     * @return whether the square of the square-root of the number is equal to the number
     */
    public static boolean isPerfectSquare(String num) {
        long n;
        try {
            n = Long.parseLong(num); }
        catch (NumberFormatException e) {
            return false; }
        
        long sqrt = (long) Math.sqrt(n);
        return n - (sqrt * sqrt) == 0;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="isPrime">
    /**
     * Points to current fastest deterministic primality function (isPrimeByPrimes4).
     * @param num the number
     * @return whether it is prime or not
     */
    public static boolean isPrime(long num) {
        return MF.isPrimeByPrimes4(num);
    }
    
    
    /**
     * Determines if a number is prime
     * O(n/2) worst case.
     * MathFunctions.isPrimeFast() is faster.
     * @param num input number
     * @return whether prime or not
     */
    public static boolean isPrimeSLOW(int num) {
        if (num < 2) {
            return false;
        }
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Determines if a number is prime by looking if its divisible by all primes less than or equal to the square root of the number
     * Null Atwood thought of it!
     * @param num input number
     * @return whether prime or not
     */
    public static boolean isPrimeByPrimes(long num) {
        if (num < 2)
            return false;
        if (num == 2)
            return true;
        //if larger than largest precomputed prime
        //the largest precomputer prime only needs to be greater or equal to sqrt(num)
        if ((int) Math.sqrt(num) > MF.primes.get(MF.primes.size()-1))
            return MF.isPrimeFaster(num);
        
        int index = 0;  //start with 2
        do {
            if (num % MF.primes.get(index) == 0)
                return false;
            index++;
        } while (MF.primes.get(index) <= (int)Math.sqrt(num));
        
        return true;
    }
    
    
    /**
     * Determines if a number is prime by looking if its divisible by all primes less than or equal to the square root of the number
     * Null Atwood thought of it!
     * Make the list of primes an array (for faster accesses) (~40% faster than isPrimeByPrimes)
     * Currently fastest deterministic primality test in this library.
     * @param num input number
     * @return whether prime or not
     */
    public static boolean isPrimeByPrimes2(long num) {
        if (num < 2)
            return false;
        if (num == 2)
            return true;
        //if larger than largest precomputed prime
        //the largest precomputed prime only needs to be greater or equal to sqrt(num)
        if ((long) Math.sqrt(num) > MF.primes2[MF.primes2.length-1])
            return MF.isPrimeFaster(num);
        
        int index = 0;  //start with 2
        long end = (long)Math.sqrt(num);
        do {
            if (num % MF.primes2[index] == 0)
                return false;
            index++;
        } while (MF.primes2[index] <= end);
        
        return true;
    }
    
    
    /**
     * Determines if a number is prime by looking if its in list of precomputed primes
     * If num &gt;= MF.PRIMES_UP_TO, then it calls MF.isPrimesByPrimes2
     * A lot faster than isPrimeByPrimes2 for numbers under MF.PRIMES_UP_TO, since it just does binary search
     * log_2(n) for this rather than O(sqrt(n)) for isPrimeByPrimes2
     * @param num input number
     * @return whether prime or not
     */
    public static boolean isPrimeByPrimes3(long num) {
        if (num >= MF.PRIMES_UP_TO)
            return MF.isPrimeByPrimes2(num);
            
        return Arrays.binarySearch(primes2, num) >= 0;
    }
    
    
    /**
     * Determines if a number is prime by looking if its in hashtable of precomputed primes
     * About twice as fast as isPrimesByPrimes3 for numbers up to 17 million
     * @param num input number
     * @return whether prime or not
     */
    public static boolean isPrimeByPrimes4(long num) {
        if (num >= MF.PRIMES_UP_TO)
            return MF.isPrimeByPrimes2(num);
            
        return MF.primes4.containsKey(num);
    }
    
    
    /**
     * Determines if a number is prime
     * O(sqrt(n)) worse case.
     * Don't use this
     * @param num input number
     * @return whether prime or not
     */
    public static boolean isPrimeFast(long num) {
        if (num < 2) {
            return false;
        }
        for (int n = 2; n <= (int) Math.sqrt(num); n++) {
            if (num % n == 0) {
                return false;
            }
        }

        return true;
    }
    
    
    /**
     * Determines is a number is prime
     * Faster than isPrimeFast
     * Credit: http://stackoverflow.com/questions/1801391/what-is-the-best-algorithm-for-checking-if-a-number-is-prime
     * @param num input number
     * @return whether prime or not
     */
    public static boolean isPrimeFaster(long num) {
        if (num < 2) 
            return false;
        if (num == 2)
            return true;
        if (num == 3)
            return true;
        if (num % 2 == 0)
            return false;
        if (num % 3 == 0)
            return false;

        long i = 5;
        long w = 2;

        while (i * i <= num) {
            if (num % i == 0)
                return false;

            i += w;
            w = 6 - w;
        }

        return true;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="listToString">
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(int[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i] + ", ";
        if (list.length >= 1)
            s += list[list.length-1];
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(double[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i] + ", ";
        if (list.length >= 1)
            s += list[list.length-1];
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(float[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i] + ", ";
        if (list.length >= 1)
            s += list[list.length-1];
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(long[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i] + ", ";
        if (list.length >= 1)
            s += list[list.length-1];
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(short[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i] + ", ";
        if (list.length >= 1)
            s += list[list.length-1];
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(byte[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i] + ", ";
        if (list.length >= 1)
            s += list[list.length-1];
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(boolean[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i] + ", ";
        if (list.length >= 1)
            s += list[list.length-1];
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(char[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i] + ", ";
        if (list.length >= 1)
            s += list[list.length-1];
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param list input list
     * @return a string representation of the list
     */
    public static String listToString(Object[] list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.length- 1; i++)
            s += list[i].toString() + ", ";
        if (list.length >= 1)
            s += list[list.length-1].toString();
        s += "]";
        return s;
    }
    
    
    /**
     * Return a string representation of a list in the form: [ , , , ]
     * @param <T> type of object in list
     * @param list input list
     * @return a string representation of the list
     */
    public static <T> String listToString(List<T> list) {
        if (list == null)
            return "[]";
        String s = "[";
        for (int i = 0; i < list.size() - 1; i++)
            s += list.get(i).toString() + ", ";
        if (list.size() >= 1)
            s += list.get(list.size()-1).toString();
        s += "]";
        return s;
    }
    // </editor-fold>
    
    
    /**
     * Just for testing
     * @param args Not doing anything with args
     */
    public static void main(String[] args) {

        /*
        for (int max = 10; max <= 1000000000; max *= 10) {
            System.out.println("max = " + max);
            

            MF.startTimer();
            List<Integer>[] tablePre1 = PrimeFactorizationPrecomputer.getPrimeFactorizationsUnder(max);
            double timePrecompute1 = MF.getElapsedSeconds();
            System.out.println("Precompute1: " + timePrecompute1);
            
            
            MF.startTimer();
            List<Integer>[] tablePre2 = PrimeFactorizationPrecomputer.getPrimeFactorizationsUnder(max);
            double timePrecompute2 = MF.getElapsedSeconds();
            System.out.println("Precompute2: " + timePrecompute2);
            
            
            
            MF.startTimer();
            List<Integer>[] table1 = MF.getPrimeFactorizationUnder(max);
            double timeCompute = MF.getElapsedSeconds();
            
            System.out.println("Compute: " + timeCompute);
            System.out.println("Compute/Precompute1: " + (timeCompute / timePrecompute1));
            System.out.print("equal? : " );
            
            boolean equal = true;
            for (int i = 2; i < tablePre1.length; i++) {
                if (!tablePre1[i].equals(table1[i])) {
                    equal = false;
                    System.out.println(i + " : table1: " + tablePre1[i] + "\t : table2: " + table1[i]);
                }
            }
            System.out.println(equal);
            System.out.println("");
            System.out.flush();
        }
        */
        
        
        /*
        for (long max = 2; max <= 100000000; max *= 2) {
            System.out.println("max = " + max);
            MF.startTimer();
            for (long n = 0; n <= max; n++)
                MF.getPrimeFactorsOfFast(n);
            System.out.println("old: " + MF.getElapsedSeconds());
            
            MF.startTimer();
            for (long n = 0; n <= max; n++)
                MF.getPrimeFactorsOfFaster(n);
            System.out.println("new: " + MF.getElapsedSeconds());
            System.out.println();
        }
        */
        long n = 6126120;
        System.out.println(MF.getFactorsOf((int)n) + " -- " + MF.getFactorsOf((int)n).size() + " many");
        System.out.println(MF.getPrimeFactorization(n));
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="max">
    /**
     * Finds the maximum element in a given array, compared using the given type's compareTo() method.
     * Accepts variable number of arguments, as well as arrays that have objects that implement Comparable
     * Does not work with primitive arrays.
     * @param <E> type of objects, must be comparable
     * @param elements list of elements
     * @return The maximum element
     */
    public static <E extends Comparable<E>> E max(E... elements) {
        if (elements == null || elements.length == 0)
            return null;
        E max = elements[0];
        for (E temp : elements)
            if (temp.compareTo(max) > 0)
                max = temp;
        
        return max;
    }
    
    
    /**
     * Find the maximum element in a array of bytes.
     * Returns Byte.MIN_VALUE if there are no elements
     * @param elements list of elements
     * @return the maximum element
     */
    public static byte max(byte... elements) {
        if (elements == null || elements.length == 0)
            return Byte.MIN_VALUE;
        byte max = elements[0];
        for (byte temp : elements)
            if (temp > max)
                max = temp;
        
        return max;
    }
    
    
    /**
     * Find the maximum element in a array of shorts.
     * Returns Short.MIN_VALUE if there are no elements
     * @param elements list of elements
     * @return the maximum element
     */
    public static short max(short... elements) {
        if (elements == null || elements.length == 0)
            return Short.MIN_VALUE;
        short max = elements[0];
        for (short temp : elements)
            if (temp > max)
                max = temp;
        
        return max;
    }
    
    
    /**
     * Find the maximum element in a array of integers.
     * Returns Integer.MIN_VALUE if there are no elements
     * @param elements list of elements
     * @return the maximum element
     */
    public static int max(int... elements) {
        if (elements == null || elements.length == 0)
            return Integer.MIN_VALUE;
        int max = elements[0];
        for (int temp : elements)
            if (temp > max)
                max = temp;
        
        return max;
    }
    
    
    /**
     * Find the maximum element in a array of longs.
     * Returns Long.MIN_VALUE if there are no elements
     * @param elements list of elements
     * @return the maximum element
     */
    public static long max(long... elements) {
        if (elements == null || elements.length == 0)
            return Long.MIN_VALUE;
        long max = elements[0];
        for (long temp : elements)
            if (temp > max)
                max = temp;
        
        return max;
    }
    
    
    /**
     * Find the maximum element in a array of floats.
     * Returns Float.NaN if there are no elements
     * @param elements list of elements
     * @return the maximum element
     */
    public static float max(float... elements) {
        if (elements == null || elements.length == 0)
            return Float.NaN;
        float max = elements[0];
        for (float temp : elements)
            if (temp > max)
                max = temp;
        
        return max;
    }
    
    
    /**
     * Find the maximum element in a array of doubles.
     * Returns Double.NaN if there are no elements
     * @param elements list of elements
     * @return the maximum element
     */
    public static double max(double... elements) {
        if (elements == null || elements.length == 0)
            return Double.NaN;
        double max = elements[0];
        for (double temp : elements)
            if (temp > max)
                max = temp;
        
        return max;
    }
    
    
    
    /**
     * Find the maximum element in a array of chars.
     * Returns Character.MIN_VALUE if there are no elements
     * @param elements list of elements
     * @return the maximum element
     */
    public static char max(char... elements) {
        if (elements == null || elements.length == 0)
            return Character.MIN_VALUE;
        char max = elements[0];
        for (char temp : elements)
            if (temp > max)
                max = temp;
        
        return max;
    }
    
    
    /**
     * Finds the maximum element in a given list, compared using the given type's compareTo() method.
     * Only accepts types that implement Comparable interface
     * Does not work with primitive arrays.
     * @param <T> must implement  Comparable
     * @param elements list of elements
     * @return The maximum element
     */
    public static <T extends Comparable> T max(List<T> elements) {
        if (elements == null)
            return null;
        T max = elements.get(0);
        for (T temp : elements)
            if (temp.compareTo(max) > 0)
                max = temp;
        return max;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="min">
    /**
     * Finds the minimum element in a given list, compared using the given type's compareTo() method.
     * Accepts variable number of arguments, as well as arrays that have objects that implement Comparable
     * Does not work with primitive arrays.
     * @param <E> the type of object in the array, must be comparable
     * @param elements list of elements
     * @return The minimum element
     */
    public static <E extends Comparable<E>> E min(E... elements) {
        if (elements == null || elements.length == 0)
            return null;
        E min = elements[0];
        for (E temp : elements)
            if (temp.compareTo(min) < 0)
                min = temp;
        
        return min;
    }
    
    
    /**
     * Finds the minimum element in a array of bytes.
     * Returns Byte.MAX_VALUE if there are no elements
     * @param elements list of elements
     * @return The minimum element
     */
    public static byte min(byte... elements) {
        if (elements == null || elements.length == 0)
            return Byte.MAX_VALUE;
        byte min = elements[0];
        for (byte temp : elements)
            if (temp < min)
                min = temp;
        
        return min;
    }
    
    
    /**
     * Finds the minimum element in a array of shorts.
     * Returns Short.MAX_VALUE if there are no elements
     * @param elements list of elements
     * @return The minimum element
     */
    public static short min(short... elements) {
        if (elements == null || elements.length == 0)
            return Short.MAX_VALUE;
        short min = elements[0];
        for (short temp : elements)
            if (temp < min)
                min = temp;
        
        return min;
    }
    
    
    /**
     * Finds the minimum element in a array of ints.
     * Returns Integer.MAX_VALUE if there are no elements
     * @param elements list of elements
     * @return The minimum element
     */
    public static int min(int... elements) {
        if (elements == null || elements.length == 0)
            return Integer.MAX_VALUE;
        int min = elements[0];
        for (int temp : elements)
            if (temp < min)
                min = temp;
        
        return min;
    }
    
    
    /**
     * Finds the minimum element in a array of longs.
     * Returns Double.NaN if there are no elements
     * @param elements list of elements
     * @return The minimum element
     */
    public static long min(long... elements) {
        if (elements == null || elements.length == 0)
            return Long.MAX_VALUE;
        long min = elements[0];
        for (long temp : elements)
            if (temp < min)
                min = temp;
        
        return min;
    }
    
    
    /**
     * Finds the minimum element in a array of floats.
     * Returns Float.NaN if there are no elements
     * @param elements list of elements
     * @return The minimum element
     */
    public static float min(float... elements) {
        if (elements == null || elements.length == 0)
            return Float.NaN;
        float min = elements[0];
        for (float temp : elements)
            if (temp < min)
                min = temp;
        
        return min;
    }
    
    
    /**
     * Finds the minimum element in a array of doubles.
     * Returns Double.NaN if there are no elements
     * @param elements list of elements
     * @return The minimum element
     */
    public static double min(double... elements) {
        if (elements == null || elements.length == 0)
            return Double.NaN;
        double min = elements[0];
        for (double temp : elements)
            if (temp < min)
                min = temp;
        
        return min;
    }
    
    
    
    /**
     * Finds the minimum element in a array of chars.
     * Returns Character.MAX_VALUE if there are no elements
     * @param elements list of elements
     * @return The minimum element
     */
    public static char min(char... elements) {
        if (elements == null || elements.length == 0)
            return Character.MAX_VALUE;
        char min = elements[0];
        for (char temp : elements)
            if (temp < min)
                min = temp;
        
        return min;
    }
    
    
    /**
     * Finds the minimum element in a given list, compared using the given type's compareTo() method.
     * Only accepts types that implements Comparable
     * Does not work with primitive arrays.
     * @param <T> type of elements, must be comparable
     * @param elements list of elements
     * @return The minimum element
     */
    public static <T extends Comparable> T min(List<T> elements) {
        if (elements == null)
            return null;
        T min = elements.get(0);
        for (T temp : elements)
            if (temp.compareTo(min) < 0)
                min = temp;
        return min;
    }
    // </editor-fold>
    
    
    /**
     * Calculates nCr = n!/(r!(n−r)!)
     * @param n n
     * @param r r
     * @return nCr
     */
    public static BigInteger nCr(int n, int r) {

        if (r > n) {
            return BI_0;
        }

        BigInteger nfact = factorial(n);
        BigInteger rfact = factorial(r);
        BigInteger nMinusrfact = factorial(n - r);

        BigInteger res = rfact.multiply(nMinusrfact);
        res = nfact.divide(res);

        return res;
    }

    
    /**
     * Returns the number of proper divisors.
     * A proper divisor is an integer that even divides n, besides n itself.
     * If n is prime then 1 should be returned.
     * @param n the number
     * @return the number of proper divisors of a number.
     */
    public static long numDivisors(long n) {
        long divs = 1;          //includes 1
        for (int i = 2; i <= (int) Math.sqrt(n); i++) {  
            if (n % i == 0)
                divs++;
        }
        divs *= 2;
        
        if (MF.isPerfectSquare(n))      //don't double up on perfect squares
            divs--;
        
        return divs;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="numberToString">
    /**
     * Converts an integer to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(String n) {
        //since long only goes up to +- 9 quintillion, we don't need any more after that, but they're good to keep if I make a BigInteger version
        String[] endings = new String[]{ "", "thousand", "million", "billion", 
            "trillion", "quadrillion", "quintillion", "sextillion", "septillion", 
            "octillion", "nonillion", "decillion", "undecillion", "duodecillion", 
            "tredecillion", "quattuordecillion", "quindecillion", "sexdecillion", 
            "septendecillion", "octodecillion", "novemdecillion", "vigintillion"}; 
        int endingIndex = 0;
        
        if (n == null || n.isEmpty())
            return "";
        
        boolean negative = n.charAt(0) == '-';
        if (negative)
            n = n.substring(1);
        
        String s = "";
        do {
            long threeDigits = (n.length() >= 3) ? Long.parseLong(n.substring(n.length() - 3)) : Long.parseLong(n);
            if (threeDigits == 0) {}
            else
                s = numberToStringThousands(threeDigits) + " " + endings[endingIndex] + " " + s;
            
            endingIndex++;
            
            n = (n.length() >= 3) ? n.substring(0, n.length() - 3) : "";
            
        } while (n.length() > 0 && endingIndex < endings.length);
        
        if (n.length() > 0)
            s = "SOME BIGGER NUMBERS and " + s;
        
        if (s.equals(""))
            return "zero";
        
        if (negative)
            return "negative " + s.trim();
        else 
            return s.trim();
    }
    
    
    /**
     * Converts an integer to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(long n) {
        return numberToString(""+n);
    }
    
    
    /**
     * Converts an integer to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(byte n) {
        return numberToString((long) n);
    }
    
    
    /**
     * Converts an integer to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(short n) {
        return numberToString((long) n);
    }
    
    
    /**
     * Converts an integer to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(int n) {
        return numberToString((long) n);
    }
    
    
    /**
     * Converts an floating point number to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(float n) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!!!! (truncate to integer)");
    }
    
    
    /**
     * Converts an floating point number to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(double n) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!!!! (truncate to integer)");
    }
    
    
    /**
     * Converts an floating point number to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(BigDecimal n) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED YET!!!! (truncate to BigInteger)");
    }
    
    
    /**
     * Converts an integer to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(char n) {
        return numberToString((long) n);
    }
    
    
    /**
     * Converts an integer to its string representation, of how one would say the number out loud.
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(BigInteger n) {
        return numberToString(n.toString());
    }
    
    
    /**
     * Used in numberToString()
     * @param n the number
     * @return a string representing that number in English
     */
    private static String numberToStringDigit(long n) {
        String s;
        switch ((int) n) {
            case 0: s = "zero";     break;
            case 1: s = "one";      break;
            case 2: s = "two";      break;
            case 3: s = "three";    break;
            case 4: s = "four";     break;
            case 5: s = "five";     break;
            case 6: s = "six";      break;
            case 7: s = "seven";    break;
            case 8: s = "eight";    break;
            case 9: s = "nine";     break;
            default: s = null;      break;
        }
        return s;
    }
    
    
    /**
     * Used in numberToString()
     * @param n the number
     * @return a string representing that number in English
     */
    private static String numberToStringTens(long n) {
        String s;
        switch ((int) n) {
            case 10: s = "ten";         break;
            case 11: s = "eleven";      break;
            case 12: s = "twelve";      break;
            case 13: s = "thirteen";    break;
            case 14: s = "fourteen";    break;
            case 15: s = "fifteen";     break;
            case 16: s = "sixteen";     break;
            case 17: s = "seventeen";   break;
            case 18: s = "eighteen";    break;
            case 19: s = "nineteen";    break;
            default: s = null;          break;
        }
        return s;
    }
    
    
    /**
     * Used in numberToString()
     * @param n the number
     * @return a string representing that number in English
     */
    private static String numberToStringHundreds(long n) {
        long onesDigit = n % 10;
        long tensDigit = n / 10;

        String s = "";
        switch ((int) tensDigit) {
            case 0: return numberToStringDigit(onesDigit);
            case 1: return numberToStringTens(n);
            case 2: s = "twenty";   break;
            case 3: s = "thirty";   break;
            case 4: s = "forty";    break;
            case 5: s = "fifty";    break;
            case 6: s = "sixty";    break;
            case 7: s = "seventy";  break;
            case 8: s = "eighty";   break;
            case 9: s = "ninety";   break;
            default:  s = "ERROR";  break;
        }
        switch ((int) onesDigit) {
            case 0: s += "";        break;
            case 1: s += "-one";    break;
            case 2: s += "-two";    break;
            case 3: s += "-three";  break;
            case 4: s += "-four";   break;
            case 5: s += "-five";   break;
            case 6: s += "-six";    break;
            case 7: s += "-seven";  break;
            case 8: s += "-eight";  break;
            case 9: s += "-nine";   break;
            default: s += "-errrrrrrror";   break;
        }
        return s.trim();
    }
    
    
    /**
     * Used in numberToString()
     * @param n the number
     * @return a string representing that number in English
     */
    private static String numberToStringThousands(long n) {
        if (n < 10)
            return numberToStringDigit(n);
        else if (n < 20)
            return numberToStringTens(n);
        else if (n < 100)
            return numberToStringHundreds(n);
        
        long hundredDigit = n / 100;
        String s;
        switch ((int) hundredDigit) {
            case 1: s = "one hundred";      break;
            case 2: s = "two hundred";      break;
            case 3: s = "three hundred";    break;
            case 4: s = "four hundred";     break;
            case 5: s = "five hundred";     break;
            case 6: s = "six hundred";      break;
            case 7: s = "seven hundred";    break;
            case 8: s = "eight hundred";    break;
            case 9: s = "nine hundred";     break;
            default:s = "ERROR HUNDRED";    break;
        }
        if (n % 100 != 0) //one hundred and something (removing the and because it's annoying)
        {
            //s += " and ";
            s += " " + numberToStringHundreds(n % 100);
        }
        return s;
    }
    // </editor-fold>
    
    
    /**
     * Number of partitions: ways you can write n as a sum of k integers
     * Credit:
     * https://math.stackexchange.com/questions/217597/number-of-ways-to-write-n-as-a-sum-of-k-nonnegative-integers
     * @param k k 
     * @param n n
     * @return the number of partitions
     */
    public static long partition(long k, long n) {
        if (k > n)
            return 0;
        if (k == n)
            return 1;
        return partition(k+1, n) + partition(k, n-k);
    }
    
    
    /**
     * Prints a grid to the standard out stream.
     * Formats width of individual elements to the largest element
     * @param grid input array
     */
    public static void printGrid(int[][] grid) {
        int greatest = 0;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++)
                if (grid[y][x] > greatest)
                    greatest = grid[y][x];
        int length = (greatest+"").length() + 1;
        
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++)
                System.out.print(fixedWidthStringLeftJustified(""+grid[y][x], length));
            System.out.println();
        }
        
    }
    
    
    /**
     * Prints a grid to the standard out stream.
     * Formats width of individual elements to the largest element
     * @param grid input array
     */
    public static void printGrid(long[][] grid) {
        long greatest = 0;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++)
                if (grid[y][x] > greatest)
                    greatest = grid[y][x];
        int length = (greatest+"").length() + 1;
        
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++)
                System.out.print(fixedWidthStringLeftJustified(""+grid[y][x], length));
            System.out.println();
        }
        
    }
    
    
    /**
     * Prints a grid to the standard out stream.
     * Formats width of individual elements to the largest element
     * @param grid input array
     */
    public static void printGrid(double[][] grid) {
        double greatest = 0;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++)
                if (grid[y][x] > greatest)
                    greatest = grid[y][x];
        int length = (greatest+"").length() + 1;
        
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if ((long)grid[y][x] - grid[y][x] == 0.0)
                    System.out.print(fixedWidthStringLeftJustified(""+((long)grid[y][x]), length));
                else
                    System.out.print(fixedWidthStringLeftJustified(""+grid[y][x], length));
            }
            System.out.println();
        }
        
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="printList">
    /**
     * Prints a list to standard print stream in the form according to MF.listToString()
     * @param arr input list
     */
    public static void printList(Object[] arr) {
        System.out.println(MF.listToString(arr));
    }
    
    
    /**
     * Prints a list to standard print stream in the form according to MF.listToString()
     * @param arr input list
     */
    public static void printList(int[] arr) {
        System.out.println(MF.listToString(arr));
    }
    
    
    /**
     * Prints a list to standard print stream in the form according to MF.listToString()
     * @param arr input list
     */
    public static void printList(long[] arr) {
        System.out.println(MF.listToString(arr));
    }
    
    
    /**
     * Prints a list to standard print stream in the form according to MF.listToString()
     * @param arr input list
     */
    public static void printList(double[] arr) {
        System.out.println(MF.listToString(arr));
    }
    
    
    /**
     * Prints a list to standard print stream in the form according to MF.listToString()
     * @param arr input list
     */
    public static void printList(byte[] arr) {
        System.out.println(MF.listToString(arr));
    }
    
    
    /**
     * Prints a list to standard print stream in the form according to MF.listToString()
     * @param arr input list
     */
    public static void printList(char[] arr) {
        System.out.println(MF.listToString(arr));
    }
    
    
    /**
     * Prints a list to standard print stream in the form according to MF.listToString()
     * @param <E> any type
     * @param arr input list
     */
    public static <E> void printList(List<E> arr) {
        System.out.println(MF.listToString(arr));
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="quickSort">
    /**
     * Sorts a list of Comparable type
     * Arrays.sort() is faster!
     * @param <E> a type the implements comparable
     * @param arr input list
     */
    public static <E extends Comparable> void quickSort(List<E> arr) {
        MF.quickSort(arr, 0, arr.size()-1);
    }
    
    
    /**
     * Sorts a list of Comparable type
     * Arrays.sort() is faster!
     * @param <E> a type the implements comparable
     * @param arr The array to be sorted
     * @param low The start index to sort from
     * @param high The end index to sort from
     */
    public static <E extends Comparable> void quickSort(List<E> arr, int low, int high) {
        if (arr == null || arr.size() == 0)
                return;

        if (low >= high)
                return;

        int middle = low + (high - low) / 2;    // pick the pivot
        E pivot = arr.get(middle);

        int i = low, j = high;                  // make left < pivot and right > pivot
        while (i <= j) {
                while (arr.get(i).compareTo(pivot) < 0)
                        i++;

                while (arr.get(j).compareTo(pivot) > 0)
                        j--;

                if (i <= j) {
                        E temp = arr.get(i);
                        arr.set(i, arr.get(j));
                        arr.set(j, temp);
                        i++;
                        j--;
                }
        }

        // recursively sort two sub parts
        if (low < j)
                quickSort(arr, low, j);

        if (high > i)
                quickSort(arr, i, high);
    }
    
    
    /**
     * Sorts an array of a Comparable type
     * Arrays.sort() is faster!
     * @param <E> a type the implements comparable
     * @param arr The array to be sorted
     */
    public static <E extends Comparable<E>> void quickSort(E[] arr) {
        quickSort(arr, 0, arr.length-1);
    }
    
    
    /**
     * Sorts an array of a Comparable type
     * Arrays.sort() is faster!
     * @param <E> a type the implements comparable
     * @param arr The array to be sorted
     * @param low The start index to sort from
     * @param high The end index to sort from
     */
    public static <E extends Comparable<E>> void quickSort(E[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
                return;

        if (low >= high)
                return;

        int middle = low + (high - low) / 2;    // pick the pivot
        E pivot = arr[middle];

        int i = low, j = high;                  // make left < pivot and right > pivot
        while (i <= j) {
                while (arr[i].compareTo(pivot) < 0)
                        i++;

                while (arr[j].compareTo(pivot) > 0)
                        j--;

                if (i <= j) {
                        E temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        i++;
                        j--;
                }
        }

        // recursively sort two sub parts
        if (low < j)
                quickSort(arr, low, j);

        if (high > i)
                quickSort(arr, i, high);
    }
    
    
    /**
     * Sorts the array using quicksort
     * @param arr input array
     */
    public static void quickSort(char[] arr) {
        MF.quickSort(arr, 0, arr.length - 1);
    }
    
    
    /**
     * Sorts the array using quicksort
     * @param arr input array
     * @param low low index for range
     * @param high high index for range
     */
    public static void quickSort(char[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
                return;

        if (low >= high)
                return;

        int middle = low + (high - low) / 2;    // pick the pivot
        char pivot = arr[middle];

        int i = low, j = high;                  // make left < pivot and right > pivot
        while (i <= j) {
                while (arr[i] < pivot)
                        i++;

                while (arr[j] > pivot)
                        j--;

                if (i <= j) {
                        char temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        i++;
                        j--;
                }
        }

        // recursively sort two sub parts
        if (low < j)
                quickSort(arr, low, j);

        if (high > i)
                quickSort(arr, i, high);
    }
    
    
    /**
     * Sorts the array using quicksort
     * @param arr input array
     */
    public static void quickSort(int[] arr) {
        MF.quickSort(arr, 0, arr.length - 1);
    }
    
    
    /**
     * Sorts the array using quicksort
     * @param arr input array
     * @param low low index for range
     * @param high high index for range
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
                return;

        if (low >= high)
                return;

        int middle = low + (high - low) / 2;    // pick the pivot
        int pivot = arr[middle];

        int i = low, j = high;                  // make left < pivot and right > pivot
        while (i <= j) {
                while (arr[i] < pivot)
                        i++;

                while (arr[j] > pivot)
                        j--;

                if (i <= j) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        i++;
                        j--;
                }
        }

        // recursively sort two sub parts
        if (low < j)
                quickSort(arr, low, j);

        if (high > i)
                quickSort(arr, i, high);
    }
    // </editor-fold>
    
    
    /**
     * Returns a random long between the specified values inclusive exclusive like normal
     * Credit: https://www.baeldung.com/java-generate-random-long-float-integer-double
     * @param leftBound -- lowest number
     * @param rightBound -- highest number
     * @return a random long number
     */
    public static long randomLong(long leftBound, long rightBound) {
        return leftBound + (long) (Math.random() * (rightBound - leftBound));
    }
    
    
    /**
     * Reverses an array.
     * @param <E> any type
     * @param arr input array
     */
    public static <E> void reverseArray(E[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            E temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }
    }
    
    
    /**
     * Reverses the digits of a number. Just like a horizontal reflection.
     * @param n input number
     * @return the reversed number
     */
    public static int reverseNumber(int n) {
        String str = n+"";
        char[] reverse = new char[str.length()];
        for (int i = 0; i < str.length(); i++) 
            reverse[str.length()-1-i] = str.charAt(i);
        String revString = new String(reverse);
        return Integer.parseInt(revString);
    }
    
    
    /**
     * Reverses the digits of a number. Just like a horizontal reflection.
     * @param n input number
     * @return the reversed number
     */
    public static BigInteger reverseNumber(BigInteger n) {
        String str = n.toString()+"";
        char[] reverse = new char[str.length()];
        for (int i = 0; i < str.length(); i++) 
            reverse[str.length()-1-i] = str.charAt(i);
        String revString = new String(reverse);
        return new BigInteger(revString);
    }

    
    /**
     * Rounds a double to a certain number of digits after the decimal point
     * @param num input number
     * @param decimalPlaces number of decimal places to round to
     * @return rounded number
     */
    public static double round(double num, int decimalPlaces) {
        BigDecimal n = new BigDecimal(num);
        n = n.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return n.doubleValue();
    }
    
    
    /**
     * Rounds the number to a certain precision, and fills in any space with zeros to the right of the decimal place
     * @param num input number
     * @param decimalPlaces number of decimal places to round to
     * @return rounded number string
     */
    public static String roundString(double num, int decimalPlaces) {
        double n = round(num, decimalPlaces);
        String str = n+"";
        String[] temp = (""+n).split("\\.");
        int roundedPlaces = (""+n).split("\\.")[1].length();  //the number of digits after decimal point
        int diff = decimalPlaces - roundedPlaces;
        for (int i = 1; i <= diff; i++)
            str = str + "0";
        return str;
    }
     
    
    /**
     * Takes the square root of a BigInteger 
     * Equivalent to MF.bigIntSqRootFloor()
     * Credit: http://faruk.akgul.org/blog/javas-missing-algorithm-biginteger-sqrt/
     * @param n input number
     * @return sqrt(n)
     */
    public static BigInteger sqrt(BigInteger n) {
        BigInteger a = BI_1;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        
        while(b.compareTo(a) >= 0) {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if(mid.multiply(mid).compareTo(n) > 0) 
                b = mid.subtract(BI_1);
            else a = mid.add(BI_1);
        }
        return a.subtract(BI_1);
    }
    
    
    /**
     * Compute the square root of x to a given scale, x &gt;= 0.
     * Credit: https://www.java-forums.org/advanced-java/44345-square-rooting-bigdecimal.html
     * Use Newton's algorithm.
     * @param x the value of x
     * @param scale the desired scale of the result
     * @return the result value
     */
    public static BigDecimal sqrt(BigDecimal x, int scale) {
        // Check that x >= 0.
        if (x.signum() < 0) {
            throw new IllegalArgumentException("x < 0");
        }
 
        // n = x*(10^(2*scale))
        BigInteger n = x.movePointRight(scale << 1).toBigInteger();
 
        // The first approximation is the upper half of n.
        int bits = (n.bitLength() + 1) >> 1;
        BigInteger ix = n.shiftRight(bits);
        BigInteger ixPrev;
 
        // Loop until the approximations converge
        // (two successive approximations are equal after rounding).
        do {
            ixPrev = ix;
 
            // x = (x + n/x)/2
            ix = ix.add(n.divide(ix)).shiftRight(1);
 
            //Thread.yield();
        } while (ix.compareTo(ixPrev) != 0);
 
        return new BigDecimal(ix, scale);
    }
    
    
    /**
     * Sets the start time for the timer
     */
    public static void startTimer() {
        MF.startTime = System.currentTimeMillis();
        MF.startTimeNano = System.nanoTime();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="sum of arrays">
    /**
     * Returns the number of true values in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static long sum(boolean[] arr) {
        long sum = 0;
        for (boolean b : arr)
            if (b)
                sum += 1;
        return sum;
    }
    
    
    /**
     * Returns the sum of the numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static byte sum(byte[] arr) {
        byte sum = 0;
        for (byte num : arr)
            sum += num;
        return sum;
    }
    
    
    /**
     * Returns the sum of the numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static char sum(char[] arr) {
        char sum = 0;
        for (char num : arr)
            sum += num;
        return sum;
    }
    
    
    /**
     * Returns the sum of the numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static short sum(short[] arr) {
        short sum = 0;
        for (short num : arr)
            sum += num;
        return sum;
    }
    
    
    /**
     * Returns the sum of the numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static int sum(int[] arr) {
        int sum = 0;
        for (int num : arr)
            sum += num;
        return sum;
    }
    
    
    /**
     * Returns the sum of the numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static long sum(long[] arr) {
        long sum = 0;
        for (long num : arr)
            sum += num;
        return sum;
    }
    
    
    /**
     * Returns the sum of the numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static float sum(float[] arr) {
        float sum = 0;
        for (float num : arr)
            sum += num;
        return sum;
    }
    
    
    /**
     * Returns the sum of the numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static double sum(double[] arr) {
        double sum = 0;
        for (double num : arr)
            sum += num;
        return sum;
    }
    
    
    /**
     * Returns the sum of the big numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static BigInteger sum(BigInteger[] arr) {
        BigInteger sum = BI_0;
        for (BigInteger num : arr)
            sum = sum.add(num);
        return sum;
    }
    
    
    /**
     * Returns the sum of the big numbers in the list
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static BigInteger sum(List<BigInteger> arr) {
        BigInteger sum = BI_0;
        for (BigInteger num : arr)
            sum = sum.add(num);
        return sum;
    }
    
    
    /**
     * Returns the sum of the big numbers in the array
     * @param arr input array
     * @return the sum of the numbers in the array
     */
    public static BigDecimal sum(BigDecimal[] arr) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal num : arr)
            sum = sum.add(num);
        return sum;
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="sum of digits">
    /**
     * Calculates the sum of digits of a number.
     * @param n the number
     * @return the sum of each digit
     */
    public static long sumOfDigits(byte n) {
        if (n < 0)
            n *= -1;
        
        long sum = 0;
        
        do {
            sum += n % 10;
            n /= 10;
        } while (n >= 10);
        sum += n;
        
        return sum;
    }
    
    
    /**
     * Calculates the sum of digits of a number.
     * @param n the number
     * @return the sum of each digit
     */
    public static long sumOfDigits(short n) {
        if (n < 0)
            n *= -1;
        
        long sum = 0;
        
        do {
            sum += n % 10;
            n /= 10;
        } while (n >= 10);
        sum += n;
        
        return sum;
    }
    
    
    /**
     * Calculates the sum of digits of a number.
     * @param n the number
     * @return the sum of each digit
     */
    public static long sumOfDigits(int n) {
        if (n < 0)
            n *= -1;
        
        long sum = 0;
        
        do {
            sum += n % 10;
            n /= 10;
        } while (n >= 10);
        sum += n;
        
        return sum;
    }
    
    
    /**
     * Calculates the sum of digits of a number.
     * @param n the number
     * @return the sum of each digit
     */
    public static long sumOfDigits(long n) {
        if (n < 0)
            n *= -1;
        
        long sum = 0;
        
        do {
            sum += n % 10;
            n /= 10;
        } while (n >= 10);
        sum += n;
        
        return sum;
    }
    
    
    /**
     * Calculates the sum of digits of a number.
     * Rounds down and gets sum of digits
     * @param f the number
     * @return the sum of each digit
     */
    public static long sumOfDigits(float f) {
        long n = (long) f;
        return MF.sumOfDigits(n);
    }
    
    
    /**
     * Calculates the sum of digits of a number.
     * Rounds down and gets sum of digits
     * @param d the number
     * @return the sum of each digit
     */
    public static long sumOfDigits(double d) {
        long n = (long) d;
        return MF.sumOfDigits(n);
    }
    
    
    /**
     * Tries to parse string into long, then returns sum of digits
     * If cannot parse, will return -1
     * @param num the string that represents a number
     * @return the sum of each digit
     */
    public static long sumOfDigits(String num) {
        long n;
        try {
            n = Long.parseLong(num);  }
        catch (NumberFormatException e) {
            return -1;   }
        
        return MF.sumOfDigits(n);
    }
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="swap">
    /**
     * Swaps the contents of an boolean array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (boolean[] arr, int i, int j) {
        boolean temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an byte array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (byte[] arr, int i, int j) {
        byte temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an char array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an short array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (short[] arr, int i, int j) {
        short temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an integer array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an long array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (long[] arr, int i, int j) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an float array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (float[] arr, int i, int j) {
        float temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an double array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an BigInteger array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (BigInteger[] arr, int i, int j) {
        BigInteger temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an BigDecimal array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (BigDecimal[] arr, int i, int j) {
        BigDecimal temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    
    /**
     * Swaps the contents of an Object array at two indices
     * @param arr - the array with the selected indices 
     * @param i - the first index
     * @param j - the second index
     */
    public static void swap (Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //</editor-fold>
    
    
    //////////////////////////////
    //////////////////////////////
    ////////STATIC VARIABLES//////
    //////////////////////////////
    //////////////////////////////
    private static long startTime = System.currentTimeMillis();
    private static long startTimeNano = System.nanoTime();
    
    public static final long PRIMES_UP_TO = 17000000;   //17 million, because this covers primality testing up to 289 trillion
    public static final long MAX_PRIME;
    public static final List<Long> primes;
    public static final long[] primes2;
    public static final HashMap<Long, Long> primes4;
    
    private static final BigInteger BI_0 = BigInteger.ZERO; //for convienence so I don't have to write BigInteger.ZERO
    private static final BigInteger BI_1 = BigInteger.ONE;
    
    private static final Random rng = new Random(System.currentTimeMillis());
    //private static AKS aks = new AKS();
        
    //////////////////////////////
    ////////INITIALIZATION////////
    //////////////////////////////
    static {
        System.out.print("Initializing MF class... ");
        long start = System.currentTimeMillis();
        
        MAX_PRIME = PrimePrecomputer2.getLastPrimeComputed();
        //primes = MF.getPrimesUnder(PRIMES_UP_TO);       //this recalculates every time with sive of erathotenses 
        primes = PrimePrecomputer2.getPrimesUnder(PRIMES_UP_TO);
        primes2 = primes.stream().mapToLong(l -> l).toArray();
        
        primes4 = new HashMap<>();
        for (long i = 0; i < primes2.length; i++) 
            primes4.put(primes2[(int)i], i);
        
        long totalTime = System.currentTimeMillis() - start;
        System.out.print("Last prime stored: " + MF.commifyNumber(PrimePrecomputer2.getLastPrimeComputed()));
        System.out.println("\t done (" + totalTime + "ms)");
        
    }
    
    //NO
    public static class Coordinate {
        public int y = 0;
        public int x = 0;
        public Coordinate(int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override public String toString() {
            return "["+y+","+x+"]";
        }
    }
    
    
}
//to add:
//shuffle array, list method
//make generic testing method