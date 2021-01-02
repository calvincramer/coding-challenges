package problem110;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Try2 {

    public static void main(String[] args) {
        
        long numErrors = 0;
        
        for (long n = 2; n < 100; n++) {
            System.out.print("n="+n + "\t");
            List<Pair> solutions = someDiophantineSolutions(n);
            MF.printList(solutions);
            //check solutions
            for (Pair p : solutions) {
                if (!check(p.x, p.y, n)) {
                    System.out.println(p.toString() + "\t" + n + "\tERROR");
                    numErrors++;
                }
            }
        }
        System.out.println("num solutions n=1260: " + someDiophantineSolutions(1260).size());
        System.out.println("num errors : " + numErrors);
    }
    
    public static List<Pair> someDiophantineSolutions(long n) {
        List<Long> factors = MF.getFactorsOfFaster(n, true, true);
        List<Pair> solutions = new ArrayList<>();
        for (long factor : factors) {
            Pair temp = new Pair( (factor+1)*n, (factor +1)*n / factor);
            solutions.add(temp);
        }
        
        //symetry
        return solutions;
    }
    
    public static boolean check(long x, long y, long n) {
        if (1.0 / x + 1.0 / y - 1.0 / n < 0.00000000001)
            return true;
        return false;
    }
    
}
/* correct answers
n=2	num solutions: 2
[(3, 6), (4, 4), (6, 3)]
n=3	num solutions: 2
[(4, 12), (6, 6), (12, 4)]
n=4	num solutions: 3
[(5, 20), (6, 12), (8, 8), (12, 6), (20, 5)]
n=5	num solutions: 2
[(6, 30), (10, 10), (30, 6)]
n=6	num solutions: 5
[(7, 42), (8, 24), (9, 18), (10, 15), (12, 12), (15, 10), (18, 9), (24, 8), (42, 7)]
n=7	num solutions: 2
[(8, 56), (14, 14), (56, 8)]
n=8	num solutions: 4
[(9, 72), (10, 40), (12, 24), (16, 16), (24, 12), (40, 10), (72, 9)]
n=9	num solutions: 3
[(10, 90), (12, 36), (18, 18), (36, 12), (90, 10)]
n=10	num solutions: 5
[(11, 110), (12, 60), (14, 35), (15, 30), (20, 20), (30, 15), (35, 14), (60, 12), (110, 11)]
n=11	num solutions: 2
[(12, 132), (22, 22), (132, 12)]
n=12	num solutions: 8
[(13, 156), (14, 84), (15, 60), (16, 48), (18, 36), (20, 30), (21, 28), (24, 24), (28, 21), (30, 20), (36, 18), (48, 16), (60, 15), (84, 14), (156, 13)]
n=13	num solutions: 2
[(14, 182), (26, 26), (182, 14)]
n=14	num solutions: 5
[(15, 210), (16, 112), (18, 63), (21, 42), (28, 28), (42, 21), (63, 18), (112, 16), (210, 15)]
n=15	num solutions: 5
[(16, 240), (18, 90), (20, 60), (24, 40), (30, 30), (40, 24), (60, 20), (90, 18), (240, 16)]
n=16	num solutions: 5
[(17, 272), (18, 144), (20, 80), (24, 48), (32, 32), (48, 24), (80, 20), (144, 18), (272, 17)]
n=17	num solutions: 2
[(18, 306), (34, 34), (306, 18)]
n=18	num solutions: 8
[(19, 342), (20, 180), (21, 126), (22, 99), (24, 72), (27, 54), (30, 45), (36, 36), (45, 30), (54, 27), (72, 24), (99, 22), (126, 21), (180, 20), (342, 19)]
n=19	num solutions: 2
[(20, 380), (38, 38), (380, 20)]
n=20	num solutions: 8
[(21, 420), (22, 220), (24, 120), (25, 100), (28, 70), (30, 60), (36, 45), (40, 40), (45, 36), (60, 30), (70, 28), (100, 25), (120, 24), (220, 22), (420, 21)]
*/