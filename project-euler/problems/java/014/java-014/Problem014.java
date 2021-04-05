package problem014;

public class Problem014 {

    /*
    The following iterative sequence is defined for the set of positive integers:

    n → n/2 (n is even)
    n → 3n + 1 (n is odd)

    Using the rule above and starting with 13, we generate the following sequence:

    13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
    It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
    Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

    Which starting number, under one million, produces the longest chain?
    */
    public static void main(String[] args) {

        long lcNum = 0;
        long lcSteps = 0;

        for (int i = 1; i < 1000000; i++) {
            long steps = 1;
            long number = i;

            while (number != 1) {
                steps++;
                if (number % 2 == 0) //even
                    number /= 2;
                else
                    number = (3*number)+1;
            }

            if (steps > lcSteps) {
                System.out.println("Longest chain so far: " + i + "  : steps = " + steps);
                lcNum = i;
                lcSteps = steps;
            }

        }
        System.out.println();
        System.out.println("Longest chain: " + lcNum + "  : steps = " + lcSteps);
        long temp = lcNum;
        int count = 0;
        while (temp != 1) {
            System.out.print(temp + " -> ");
            count++;
            if (count > 10) {
                System.out.println();
                count = 0;
            }
            if (temp % 2 == 0) //even
                temp /= 2;
            else
                temp = (3*temp)+1;
        }
        System.out.println();
    }
    //answer: 837799 steps: 525

}