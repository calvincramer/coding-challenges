//[COMPLETED]

package p013;

import java.util.HashMap;
import java.util.Map;

public class P013 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("I" + "\t" + s.romanToInt("I"));
        System.out.println("III" + "\t" + s.romanToInt("III"));
        System.out.println("VI" + "\t" + s.romanToInt("VI"));
        System.out.println("DL" + "\t" + s.romanToInt("DL"));
        System.out.println("XIX" + "\t" + s.romanToInt("XIX"));
        System.out.println("IX" + "\t" + s.romanToInt("IX"));
    }
    
private static class Solution {
    
    public int romanToInt(String s) {
        
        if (s == null || s.length() == 0)
            return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int n = map.get(s.charAt(0));
        int lastValue = n;
        
        for (int i = 1; i < s.length(); i++) {
            int curValue = map.get(s.charAt(i));
            if (curValue > lastValue)
                n += curValue - (lastValue * 2);
            else
                n += curValue;
            
            lastValue = curValue;
        }
        return n;
    }
}

}
