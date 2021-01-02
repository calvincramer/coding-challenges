package problem005;

public class Problem005 {

    /*
    2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
    What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
    */
    public static void main(String[] args) {
        int testNum = 1;
        while (true) {
            boolean good = true;
            for (int n = 1; n <= 20; n++) {
                if (testNum % n != 0)
                    good = false;
            }
            if (good) {
                System.out.println("Found good number: " + testNum);
                break;
            }
            testNum++;
        }
        System.out.println("Done");
    } //answer : 232792560

}
