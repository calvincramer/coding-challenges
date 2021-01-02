/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem493;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import mathtools.MF;

/**
 *
 * @author Calvin
 */
public class Try2 {
    
    public static void main(String[] args) {
        System.out.println("attempt 2");
        long total = 0;
        long n = 1;
        long count = 0;
        while (true) {
            Urn urn = new Urn();
            int[] pulled = new int[20];
            for (int i = 0; i <= 19; i++) {
                pulled[i] = urn.pullBall();
            }
            total += countDistinct(pulled);
            double val = total * 1.0 / n;
            
            if (count > 937700) {
                System.out.println(val + "\t" + total + "\t" + n);
                count = 0;
            }
            count++;
            
            n++;
            
        }
    }
    
    public static int countDistinct(int[] arr) {
        boolean[] distinct = new boolean[8];
        for (int n : arr) {
            distinct[n] = true;
        }
        int dist = 0;
        for (boolean b : distinct)
            if (b)
                dist++;
        return dist;
    }
    
    public static class Urn {
        public List<Integer> balls;
        private final Random rng;
        
        public Urn() {
            this.rng = new Random(System.currentTimeMillis());
            this.balls = new ArrayList<>();
            for (int i = 1; i <= 7; i++) {
                for (int n = 1; n <= 10; n++) {
                    balls.add(i);
                }
            }
        }
        
        public int pullBall() {
            return this.balls.remove(rng.nextInt(this.balls.size()));
        }
        
    }
}
//937700 iters per second