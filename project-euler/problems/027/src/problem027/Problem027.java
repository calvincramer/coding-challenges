package problem027;

public class Problem027 {

    /*
    Euler discovered the remarkable quadratic formula:

n2+n+41n2+n+41
It turns out that the formula will produce 40 primes for the consecutive integer values 0≤n≤390≤n≤39. However, when n=40,402+40+41=40(40+1)+41n=40,402+40+41=40(40+1)+41 is divisible by 41, and certainly when n=41,412+41+41n=41,412+41+41 is clearly divisible by 41.

The incredible formula n2−79n+1601n2−79n+1601 was discovered, which produces 80 primes for the consecutive values 0≤n≤790≤n≤79. The product of the coefficients, −79 and 1601, is −126479.

Considering quadratics of the form:

n^2+an+b, where |a|<1000|a|<1000 and |b|≤1000|b|≤1000

where |n||n| is the modulus/absolute value of nn
e.g. |11|=11|11|=11 and |−4|=4|−4|=4
Find the product of the coefficients, aa and bb, for the quadratic expression that produces the maximum number of primes for consecutive values of nn, starting with n=0n=0.
    */
    public static void main(String[] args) {
        int greatestA = Integer.MIN_VALUE;
        int greatestB = Integer.MIN_VALUE;
        int mostPrimes = Integer.MIN_VALUE;
        
        for (int a = -999; a <= 999; a++) {
        for (int b = -999; b <= 1000; b++) {
            int tempPrimes = numPrimesIn(a, b);
            if (tempPrimes > mostPrimes) {
                mostPrimes = tempPrimes;
                greatestA = a;
                greatestB = b;
            }
        }}
        
        System.out.println("Most primes: " + mostPrimes);
        System.out.println("A: " + greatestA);
        System.out.println("B: " + greatestB);
        System.out.println("A*B: " + (greatestA*greatestB));
    }
    
    public static int numPrimesIn(int a, int b) {
        int numPrimes = 0;
        int n = 0;
        while (true) {
            int fx = (n*n)+(a*n)+b;
            if (isPrime(fx)) {
                n++;
                numPrimes++;
            } else {
                return numPrimes;
            }
        }
    }
    
    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) return false;
        }
        return true;
    }

}
