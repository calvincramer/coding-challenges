//[COMPLETED]

package p028;

public class P028 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.strStr("a", "a"));
        System.out.println(s.strStr("hello", "ll"));
        System.out.println(s.strStr("aaaaa", "bba"));
        System.out.println(s.strStr("abcd", ""));
        System.out.println(s.strStr("", "a"));
        System.out.println(s.strStr("", ""));
        
    }
    
    
private static class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack.equals("") && needle.equals(""))
            return 0;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle) )
                return i;
        }
        return -1;
    }
}

}
