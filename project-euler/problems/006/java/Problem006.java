package problem006;

public class Problem006 {

    /*
    The sum of the squares of the first ten natural numbers is,

    12 + 22 + ... + 102 = 385
    The square of the sum of the first ten natural numbers is,

    (1 + 2 + ... + 10)2 = 552 = 3025
    Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

    Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
    */
    public static void main(String[] args) {
        
        int sumSquares = 0;
        for (int i = 1; i <= 100; i++)
            sumSquares += i*i;
        System.out.println("Sum squares: " + sumSquares);
        
        int squaredSum;
        int sumOf100 = 0;
        for (int i = 1; i <= 100; i++)
            sumOf100 += i;
        squaredSum = sumOf100 * sumOf100;
        System.out.println("Squared sum: " + squaredSum);
        System.out.println("Difference: " + (squaredSum - sumSquares));
    }

    /*
    Sum squares: 338350
    Squared sum: 25502500
    Difference: 25164150
    */
}
