package problem009;

public class Problem009 {

    /*
    A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

    a2 + b2 = c2
    For example, 32 + 42 = 9 + 16 = 25 = 52.
    There exists exactly one Pythagorean triplet for which a + b + c = 1000.
    Find the product abc.
    */
    public static void main(String[] args) {
        int targetA = -1;
        int targetB = -1;
        int targetC = -1;
        for (int a = 1; a < 1000; a++) {
            for (int b = 1; b < 1000; b++) {
                for (int c = 1; c < 1000; c++) {
                    //test for pythag triplet
                    if ((a*a) + (b*b) == (c*c) ) {
                        System.out.println(a + " " + b + " " + c + "  sum= " + (a+b+c));
                        if (a+b+c == 1000) {
                            targetA = a;
                            targetB = b;
                            targetC = c;
                        }
                    }

        }}}
        
        System.out.println("\nAnswer: " + targetA + " " + targetB + " " + targetC + "  prod: " + (targetA*targetB*targetC));
    }
    //answer: 375, 200, 425   prod= 31875000
}
