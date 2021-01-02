//[COMPLETED]

package p038;

import mathtools.MF;

public class P038 {

    public static void main(String[] args) {
        MF.startTimer();
        Solution s = new Solution();
        for (int i = 1; i < 20; i++)
            System.out.println(i + "\t " + s.countAndSay(i));
        System.out.println(MF.getElapsedSeconds());
        System.out.println(MF.getElapsedTime());
    }
    
    /*
1.     1
2.     11
3.     21
4.     1211
5.     111221
6.     312211
    */
    
private static class Solution {
    public String countAndSay(int n) {
        if (n <= 1)
            return "1";
        
        String prev = countAndSay(n-1);
        String s = "" + prev;
        StringBuilder result = new StringBuilder();
        
        while (!s.isEmpty()) {
            int count = 1;
            char c = s.charAt(0);
            
            s = s.substring(1);
            
            while (!s.isEmpty() && s.charAt(0) == c) {
                s = s.substring(1);
                count++;
            }
            
            result.append("" + count + c);
        }
        
        return result.toString();
    }
}


}
