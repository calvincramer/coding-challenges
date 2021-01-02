package problem112;

public class Problem112 {

    /*
    Working from left-to-right if no digit is exceeded by the digit to its left it is called an increasing number; for example, 134468.

    Similarly if no digit is exceeded by the digit to its right it is called a decreasing number; for example, 66420.

    We shall call a positive integer that is neither increasing nor decreasing a "bouncy" number; for example, 155349.

    Clearly there cannot be any bouncy numbers below one-hundred, but just over half of the numbers below one-thousand (525) are bouncy. In fact, the least number for which the proportion of bouncy numbers first reaches 50% is 538.

    Surprisingly, bouncy numbers become more and more common and by the time we reach 21780 the proportion of bouncy numbers is equal to 90%.

    Find the least number for which the proportion of bouncy numbers is exactly 99%.
    */
    public static void main(String[] args) {

        int numBouncy = 0;
        double stopPer = 0.99;
        int n = 1;

        do {
            if (Problem112.isBouncy(n)) {
              numBouncy++;
              //System.out.println(n);
            }
            n++;
        } while (numBouncy * 1.0 / (n-1) < stopPer);
        
        n--;
        System.out.println("Total: " + numBouncy);
        System.out.println(n);
    }

    public static boolean isIncreasing(int num) {
        char[] charDigs = (""+num).toCharArray();
        int[] digs = new int[charDigs.length];
        for (int i = 0; i < digs.length; i++) {
          digs[i] = Integer.parseInt(""+charDigs[i]);
        }

        for (int i = 0; i < digs.length - 1; i++) {
          if (digs[i] > digs[i+1])
            return false;
        }

        return true;
    }

    public static boolean isDecreasing(int num) {
        char[] charDigs = (""+num).toCharArray();
        int[] digs = new int[charDigs.length];
        for (int i = 0; i < digs.length; i++) {
          digs[i] = Integer.parseInt(""+charDigs[i]);
        }

        for (int i = 0; i < digs.length - 1; i++) {
          if (digs[i] < digs[i+1])
            return false;
        }

        return true;
    }

    public static boolean isBouncy(int num) {
        return !Problem112.isIncreasing(num) && !Problem112.isDecreasing(num);
    }

}