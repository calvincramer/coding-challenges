package problem351;

import mathtools.MF;

public class Attempt6 {
    
    public static void main(String[] args) {
        MF.startTimer();
        int hmil = 100000000;
        long count = 0;
        
        for (int level = 1; level <= hmil; level++) {
            for (int x = 0; x <= level; x++) {
                count++;
            }
        }
        
        System.out.println(count);
        System.out.println(MF.getElapsedSeconds());
        
        
        //attack strategies
        //keep a line of already seen points
        //when visiting new point, if not on line, add to line, increment total can see
        //if collision, do nothing
        //this counts everything you can see
        
        
        //total cant see = total - can see
        
        //how many collisions are there for any one point?
        //knowing this, we can count all collisions going from (0...100000000) inclusive
        
        
        //alternate:
        //iterate through all reduced fraction betwen [0, 1] with denomonators no greater than 100000000
        //count all of these
        //total = number we can see
    }
}
