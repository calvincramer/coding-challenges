package problem101;

import java.util.Arrays;
import mathtools.MF;

public class Problem101 {

    /*
    If we are presented with the first k terms of a sequence it is impossible to say with certainty the value of the next term, 
    as there are infinitely many polynomial functions that can model the sequence.

    As an example, let us consider the sequence of cube numbers. This is defined by the generating function, 
    un = n^3: 1, 8, 27, 64, 125, 216, ...

    Suppose we were only given the first two terms of this sequence. Working on the principle that "simple is best" we should assume a 
    linear relationship and predict the next term to be 15 (common difference 7). Even if we were presented with the first three terms, 
    by the same principle of simplicity, a quadratic relationship should be assumed.

    We shall define OP(k, n) to be the nth term of the optimum polynomial generating function for the first k terms of a sequence. 
    It should be clear that OP(k, n) will accurately generate the terms of the sequence for n ≤ k, and potentially the first incorrect term (FIT) will be OP(k, k+1); 
    in which case we shall call it a bad OP (BOP).

    As a basis, if we were only given the first term of sequence, it would be most sensible to assume constancy; that is, for n ≥ 2, OP(1, n) = u1.

    Hence we obtain the following OPs for the cubic sequence:

    OP(1, n) = 1	1, 1, 1, 1, ...
    OP(2, n) = 7n−6	1, 8, 15, ...
    OP(3, n) = 6n2−11n+6     	1, 8, 27, 58, ...
    OP(4, n) = n3	1, 8, 27, 64, 125, ...
    Clearly no BOPs exist for k ≥ 4.

    By considering the sum of FITs generated by the BOPs (indicated in red above), we obtain 1 + 15 + 58 = 74.

    Consider the following tenth degree polynomial generating function:

    un = 1 − n + n^2 − n^3 + n^4 − n^5 + n^6 − n^7 + n^8 − n^9 + n^10

    Find the sum of FITs for the BOPs.
    */
    public static void main(String[] args) {
        
        Polynomial poly = new Polynomial(new long[]{1,-1,1,-1,1,-1,1,-1,1,-1,1});
        //Polynomial poly = new Polynomial(new long[]{0,0,0,1});  //x^3
        
        long[] correctSequence = new long[15];
        for (int i = 0; i < correctSequence.length; i++)
            correctSequence[i] = poly.calculate(i+1);
        
        long sumFITs = 0;
        boolean isDone = false;
        int lengthToMatch = 1;
        
        while (!isDone) {
            Long FIT = op(lengthToMatch, correctSequence);
            if (FIT == null)
                isDone = true;
            else {
                sumFITs += FIT;
            }
            lengthToMatch++;
        }
        
        System.out.println("total: " + sumFITs);
        
        
    }
    
    //returns first mismatching element, or null if everything matches
    public static Long op(int termsToMatch, long[] matchFrom) {
        
        //make system
        double[][] system = new double[termsToMatch][termsToMatch+1];
        for (int y = 0; y < system.length; y++)
            for (int x = 0; x < system.length; x++)
                system[y][x] = Math.pow(y+1, x);
        for (int y = 0; y < system.length; y++)
            system[y][system[0].length-1] = matchFrom[y];
        
        //get polynomial
        double[] guessPolyDouble = LinearSystemSolver.solveSystem(system);
        //MF.printList(guessPolyDouble);
        long[] guessPoly = new long[guessPolyDouble.length];
        for (int i = 0; i < guessPoly.length; i++) {
            guessPoly[i] = (long) Math.round(guessPolyDouble[i]);
        }
        Polynomial guess = new Polynomial(guessPoly);

        
        //generate guess sequence
        long[] guessSequence = new long[matchFrom.length];
        for (int i = 0; i < guessSequence.length; i++)
            guessSequence[i] = guess.calculate(i+1);
        
        
        //find first mismatch
        int firstMismatch = -1;
        for (int i = 0; i < guessSequence.length; i++) {
            if (matchFrom[i] != guessSequence[i]) {
                firstMismatch = i;
                break;
            }
        }
        
        if (firstMismatch == -1)
            return null;
        
        else
            MF.printList(Arrays.copyOf(guessSequence, firstMismatch));
        
        
        return guessSequence[firstMismatch];

    }

}
//answer: 37076114526