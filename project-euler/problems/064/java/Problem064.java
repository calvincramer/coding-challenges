package problem064;

import mathtools.MF;

public class Problem064 {
    
    static int n = 2;

    public static void main(String[] args) {
        
        MF.startTimer();
        
        int greatestPeriod = 0;
        int oddPeriods = 0;
        
        for (; n <= 10000; n++) {
            if (isPerfectSquare(n))
                continue;
            
            Term[] contFrac = new Term[10000];
            Term t = new Term( (int)Math.sqrt(n), n, 1, -(int)Math.sqrt(n));
            contFrac[0] = Term.copyOf(t);
            for (int i = 1; i < contFrac.length; i++) {
                t.iterate();
                contFrac[i] = Term.copyOf(t);
            }
            
            int[] fronts = new int[contFrac.length];
            for (int i = 0; i < contFrac.length; i++)
                fronts[i] = contFrac[i].front;
            
            int repeat = findRepeat(fronts);
            
            /*
            System.out.print("\u221A" + n + ":");
            System.out.print(" [" + fronts[0] + ";(");
            for (int i = 1; i <= repeat; i++) {
                System.out.print(fronts[i]);
                if (i < repeat)
                    System.out.print(",");
            }
            System.out.println(")], period = " + repeat);
            */
            
            if (repeat > greatestPeriod)
                greatestPeriod = repeat;
            if (repeat % 2 == 1)
                oddPeriods++;
            
        }
        
        System.out.println("GP:\t" + greatestPeriod);
        System.out.println("odd periods:\t" + oddPeriods);
        System.out.println(MF.getElapsedSeconds());
    }
    
    public static int findRepeat(int[] nums) {
        int l = 1;
        for (; l < nums.length / 2; l++) {
            boolean allGood = true;
            for (int i = 1 + l; i < nums.length - 1 - l; i += l) {
                boolean good = true;
                for (int r = 0; r < l; r++) {
                    if (nums[i + r] != nums[i - l + r]) {
                        allGood = false;
                        break;
                    }
                }
                if (!allGood)
                    break;
                
            }
            if (allGood)
                    return l;
        }
        return 0;
    }
    
    public static boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return n - (sqrt * sqrt) == 0;
    }
    
    public static class Term {
        public int front;
        public int sqrt;
        public int numerator;
        public int bottom;
        
        public Term (int front, int sqrt, int numerator, int bottom) {
            this.front = front;
            this.sqrt = sqrt;
            this.numerator = numerator;
            this.bottom = bottom;
        }
        
        public static Term copyOf(Term other) {
            return new Term(other.front, other.sqrt, other.numerator, other.bottom);
        }
        
        public void iterate() {
            this.numerator = (sqrt-(bottom*bottom))/numerator;
            this.front = (int) ( (Math.sqrt(sqrt) + Math.abs(bottom) ) / numerator);
            this.bottom = Math.abs(bottom) - (front * numerator);
        }
        
        @Override public String toString() {
            return "" + front + " + " + numerator + " / (\u221A" + sqrt + " " + (bottom >= 0 ? "+ " : "- ") + Math.abs(bottom) + ")";
        }
    }

}
//answer: 1322