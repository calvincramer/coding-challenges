package problem076;

import java.util.ArrayList;
import java.util.List;

public class Problem076 {


    public static void main(String[] args) {
        
        final int start = 100;
        List<Integer> breakUp = new ArrayList<>();
        breakUp.add(start-1);
        breakUp.add(1);
        int numWays = 1;
        
        while (true) {
            
            //print array
            for (int i = 0; i < breakUp.size(); i++) {
                System.out.print(breakUp.get(i));
                if (i < breakUp.size()-1)
                    System.out.print(" + ");
            }
            System.out.println("");
            
            //test if done 
            boolean allOnes = true;
            for (int n : breakUp) {
                if (n != 1) {
                    allOnes = false;
                    break;
                }
            }
            if (allOnes)
                break;
            
            
            //next interation
            int stop = breakUp.size();  //stop at first non-one number
            for (int i = breakUp.size()-1; i >= 0; i--) {
                if (breakUp.get(i) != 1) {
                    stop = i;
                    break;
                }
            }
            
            if (breakUp.get(stop) == 2) {
                breakUp.set(stop, 1);
                breakUp.add(1);
            }
            else
            {
                breakUp.set(stop, breakUp.get(stop)-1);
                breakUp.set(stop+1, breakUp.get(stop+1)+1);
            }
            
            numWays++;
            
        }
        
        
        System.out.println("\nNumber of ways: " + numWays);
        
    }
    
}
//Number of ways: 196