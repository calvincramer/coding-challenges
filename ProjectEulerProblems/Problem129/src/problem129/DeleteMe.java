
package problem129;

import java.util.Arrays;
import java.util.Random;
import mathtools.MF;

public class DeleteMe {
    
    public static void main(String[] args) {
        Random rng = new Random(System.currentTimeMillis());
        
        final int times = 300;
        
        for (int size = 100; size <= 1000000; size *= 10) {
            System.out.println("size: " + size);
            int[] arr1 = new int[size];
            int[] arr2 = new int[size];

            double arr1Total = 0.0;
            double arr2Total = 0.0;

            for (int run = 1; run <= times; run++) {
                for (int i = 0; i < size; i++) {
                    int temp = rng.nextInt();
                    arr1[i] = temp;
                    arr2[i] = temp;
                }

                MF.startTimer();
                MF.quickSort(arr1);
                arr1Total += MF.getElapsedSeconds();

                MF.startTimer();
                Arrays.sort(arr2);
                arr2Total += MF.getElapsedSeconds();

            }
            
            System.out.println("\tarr1: " + arr1Total);
            System.out.println("\tarr2: " + arr2Total);
        }
    }
}
