package problem493;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem493 {
    
    /*
    70 colored balls are placed in an urn, 10 for each of the seven rainbow colors.
    What is the expected number of distinct colors in 20 randomly picked balls?
    Give your answer with nine digits after the decimal point (a.bcdefghij).
    */
    //balls placed back in urn?
    
    public static void main(String[] args) {
        long total = 0;
        int n = 1;
        int count = 0;
        while (true) {
            Urn urn = new Urn();
            Color[] pulled = new Color[20];
            for (int i = 0; i <= 19; i++) {
                pulled[i] = urn.pullBall();
            }
            total += countDistinct(pulled);
            double val = total * 1.0 / n;
            
            if (count > 100000) {
                System.out.println(val + "\t" + total + "\t" + n);
                count = 0;
            }
            count++;
            
            n++;
            
        }
    }
    
    public static int countDistinct(Color[] arr) {
        if (arr.length < 1)
            return 0;
        if (arr.length == 1)
            return 1;
        List<Color> distinct = new ArrayList<>();
        for (Color c : arr) {
            if (!distinct.contains(c))
                distinct.add(c);
        }
        return distinct.size();
    }
    
    
    public static class Urn {
        public List<Color> balls;
        private final static Random rng = new Random(System.currentTimeMillis());
        
        public Urn() {
            this.balls = new ArrayList<>();
            for (Color c : Color.values()) {
                for (int i = 1; i <= 10; i++) {
                    this.balls.add(c);
                }
            }
        }
        
        public Color pullBall() {
            return this.balls.remove(rng.nextInt(this.balls.size()));
        }
        
    }
    

    
    public static enum Color {
        RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;
    }
}
//6.81873####
//734400 iters per second