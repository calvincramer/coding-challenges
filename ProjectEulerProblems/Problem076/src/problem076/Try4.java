package problem076;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mathtools.MF;


public class Try4 {
    
    final static int TARGET = 100;
    static long ways = 0;
    static Random r = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
        System.out.println("slower?");
        MF.startTimer();
        permute(0, 100, new ArrayList<Integer>());
        System.out.println();
        System.out.println(ways - 1 + " ways" + "\t" + MF.getElapsedSeconds());
        
        ways = 0;
        System.out.println("faster?");
        MF.startTimer();
        permuteWithoutList(0, 100);
        System.out.println();
        System.out.println(ways - 1 + " ways" + "\t" + MF.getElapsedSeconds());
        
    }
    
    public static void permute(int sum, int lastNumber, List<Integer> list) {
        
        for (int i = 1; i <= lastNumber; i++) {
            if (sum + i > TARGET)
                return;
            
            list.add(i);
            
            if (sum + i == TARGET) {
                ways++;
                //if (r.nextInt(1000) == 0)
                //    printList(list);
            }

            permute(sum+i, i, list);
            
            list.remove(list.size()-1);
        }
    }
    
    public static void permuteWithoutList(int sum, int lastNumber) {
        
        for (int i = 1; i <= lastNumber; i++) {
            if (sum + i > TARGET)
                return;
            
            if (sum + i == TARGET)
                ways++;

            permuteWithoutList(sum+i, i);
        }
    }
    
    public static void printList(List<Integer> list) {
        for (Integer i : list)
            System.out.print(i + " ");
        System.out.println();
    }
}
//wrong:  1642992567
//correct: 190569291