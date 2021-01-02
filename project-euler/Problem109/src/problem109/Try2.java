package problem109;

import java.util.Random;
import javafx.util.Pair;
import mathtools.MF;

public class Try2 {

    public static Random rng = new Random(System.currentTimeMillis());
    public static int ways = 0;
    public static int conflicts = 0;
    public static final String MISS_STR = "XX";
    
    public static void main(String[] args) {
        MF.startTimer();
        
        long checkoutCount = 0;
        long doublesUp = 0;
        
        for (int thro1 = 0; thro1 <= 62; thro1++) {
        for (int thro2 = 0; thro2 <= 62; thro2++) {
        for (int thro3 = 0; thro3 <= 62; thro3++) {
            Pair<String, Integer> t1 = getThrow(thro1);
            Pair<String, Integer> t2 = getThrow(thro2);
            Pair<String, Integer> t3 = getThrow(thro3);
            String[] thros = new String[]{ t1.getKey(), t2.getKey(), t3.getKey() };
            
            if (throwActuallyChecksOut(thros) && validThrows(thros) && tallyScore(thros) < 100) {
                //printThrows(thros);
                if (doublesUp(t1,t2,t3)) {
                    doublesUp++;
                    //System.out.print("\t()");
                }
                //System.out.println("");
                checkoutCount++;
                
                
            }
        }}}    
        System.out.println("Total:    " + checkoutCount);
        System.out.println("Doubles:  " + doublesUp);
        if (doublesUp % 2 != 0)
            System.out.println("doubles up not even, miscounted");
        System.out.println("Disctict: " + (checkoutCount - (doublesUp / 2)));
        
        System.out.println(MF.getElapsedSeconds());
        
        
    }
    
    
    
    public static Pair<String, Integer> getThrow(int throwNumber) {
        int n = throwNumber;
        if (n < 0)
            return new Pair("error", -3);
        else if (n > 62)
            return new Pair("error", -4);
        
        if (n <= 59) {
            int sdt = n % 3;
            int num = (n / 3) + 1;
            int actual = num * (sdt + 1);
            switch (sdt) {
                case 0: return new Pair("S" + num, actual);
                case 1: return new Pair("D" + num, actual);
                case 2: return new Pair("T" + num, actual);
                default: return new Pair("error", -1);
            }
        }
        switch (n) {
            case 60: return new Pair("OB", 25);
            case 61: return new Pair("IB", 50);
            case 62: return new Pair(MISS_STR, 0);  //miss
            default: return new Pair("ERROR", -2);
        }
    }
    
    
    
    public static boolean throwActuallyChecksOut(String[] thros) {
        //last non-miss must be a double or inner bulls eye
        int j = thros.length - 1;
        while (j >= 0 && thros[j].equals(MISS_STR))
            j--;
        if (j < 0)
            return false;
        else
            return thros[j].charAt(0) == 'D' || thros[j].equals("IB");
    }
    
    
    
    public static boolean validThrows(String[] thros) {
        if (thros[0].equals(MISS_STR) && !thros[1].equals(MISS_STR))
            return false;
        else if (thros[1].equals(MISS_STR) && !thros[2].equals(MISS_STR))
            return false;
        return true;
    }
    public static boolean validThrowsBad(String[] thros) {
        boolean[] misses = new boolean[thros.length];
        for (int i = 0; i < thros.length; i++)
            if (thros[i].equals("XX"))
                misses[i] = true;
        
        for (int i = 0; i < misses.length - 1; i++)
            if (misses[i] == true && misses[i+1] == false)
                return false;
        
        return true;
    }
    
    
    public static boolean doublesUp(Pair<String, Integer> t1, Pair<String, Integer> t2, Pair<String, Integer> t3) {
        //can double up if there are three throws, first two throws are not the same
        
        /*
        if (t1.getKey().equals("OB") || t1.getKey().equals("IB") || t1.getKey().equals(MISS_STR)
          ||t2.getKey().equals("OB") || t2.getKey().equals("IB") || t2.getKey().equals(MISS_STR)) {
            return false;
        }
        int section1 = Integer.parseInt(t1.getKey().substring(1));
        int section2 = Integer.parseInt(t2.getKey().substring(1));
        
        if (!t3.getKey().equals(MISS_STR) && section1 == section2) {
            return !t1.getKey().equals(t2.getKey());
        }
        return false;
        */
        
        if (t3.getKey().equals(MISS_STR) || t2.getKey().equals(MISS_STR))
            return false;
        
        return !t1.getKey().equals(t2.getKey());
    }
    
    
    public static int tallyScore(String[] thros) {
        int score = 0;
        for (String thro : thros) {
            if (thro.equals(MISS_STR))
                continue;
            else if (thro.equals("OB")) {
                score += 25;
            }
            else if (thro.equals("IB")) {
                score += 50;
            }
            else {
                int temp = Integer.parseInt(thro.substring(1));
                if (thro.charAt(0) == 'S')
                    score += temp;
                else if (thro.charAt(0) == 'D')
                    score += temp * 2;
                else if (thro.charAt(0) == 'T')
                    score += temp * 3;
            }
        }
        
        
        return score;
    }
    
    public static void printThrows(String[] thro) {
        for (int i = 0; i < thro.length; i++) {
            if (thro[i].equals(""))
                continue;
            if (thro[i].equals(MISS_STR))
                System.out.print("  ");
            else
                System.out.print(thro[i]);
            if (i < thro.length - 1)
                System.out.print(" | ");
        }
    }
    

    
}
