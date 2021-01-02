//[COMPLETED]

package p014;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P014 {

    public static void main(String[] args) {
        String[] strings = new String[] {"aac", "aacd", "bcde", "bbb", "bcdeghtadsf"};
        Solution s = new Solution();
        System.out.println(s.longestCommonPrefix(strings));
        System.out.println(s.longestCommonPrefix(new String[] {"a", "a", "b"}));
        System.out.println(s.longestCommonPrefix(new String[] {""}));
        System.out.println(s.longestCommonPrefix(new String[] {}));
    }
    
private static class Solution {
    
    public String longestCommonPrefix(String[] strs) {
        //break on first non common character
        if (strs.length == 0)
            return "";
        
        int i = 0;
        while (true) {
            if (i >= strs[0].length())
                return strs[0].substring(0, i);
            
            char toMatch = strs[0].charAt(i);
            
            
            for (int k = 1; k < strs.length; k++) {
                if (i >= strs[k].length())
                    return strs[k].substring(0, i);
                if (strs[k].charAt(i) != toMatch)
                    return strs[k].substring(0, i);

            }
            
            i++;
        }
    
    }
    
    
    /* this solution is finds the longest prefix between any two strings
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 1)
            return strs[0];
        
        List<String> strings = Arrays.asList(strs);
        int length = 0;
        while (true) {
            boolean[] havePair = new boolean[strings.size()];
            int numFound = 0;
            
            for (int a = 0; a < strings.size(); a++) {
            for (int b = 0; b < strings.size(); b++) {
                if (a == b) 
                    continue;
                if (strings.get(a).length() < length || strings.get(b).length() < length) 
                    continue;
                if (strings.get(a).substring(0, length).equals(strings.get(b).substring(0, length))) {
                    havePair[a] = true;
                    havePair[b] = true;
                    numFound++;
                }
            }}
            
            if (numFound == 0)
                break;
            
            List<String> newStrings = new ArrayList<>();
            for (int i = 0; i < havePair.length; i++)
                if (havePair[i])
                    newStrings.add(strings.get(i));
            
            strings = newStrings;
            
            length++;
            
            
            
        }
        
        length--;
        
        if (strings.size() == 0 || length <= 0)
            return "";
        return strings.get(0).substring(0, length);
    }
    */
    
    /*
    public int longestCommonPrefix(String[] strs) {
        ArrayList<ArrayList<String>> commonStrings = new ArrayList<ArrayList<String>>();
        //collect strings with same first character
        for (String s : strs) {
            if (s.length() == 0)
                continue;
            
            boolean foundMatch = false;
            for (int i = 0; i < commonStrings.size(); i++) {
                if (commonStrings.get(i).get(0).charAt(0) == s.charAt(0)) {
                    commonStrings.get(i).add(s);
                    foundMatch = true;
                    break;
                }
            }
            
            if (!foundMatch) {
                commonStrings.add(new ArrayList<>());
                commonStrings.get(commonStrings.size()-1).add(s);
            }
        }
        
        //trim first character off
        for (int y = 0; y < commonStrings.size(); y++) {
        for (int x = 0; x < commonStrings.get(y).size(); x++) {
            String s = commonStrings.get(y).get(x);
            commonStrings.get(y).set(x, s.substring(1));
        }}
        
        //recursion
        int maxLength = 0;
        for (int y = 0; y < commonStrings.size(); y++) {
            int temp = 1;
            String[] arg = commonStrings.get(y).toArray(new String[commonStrings.get(y).size()]);
            temp += longestCommonPrefix(arg);
            if (temp > maxLength)
                maxLength = temp;
        }
        
        return maxLength;
        
    }
    */
}

}
