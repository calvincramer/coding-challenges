//[COMPLETED]

package p020;

import java.util.List;
import java.util.Stack;

public class P020 {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isValid(""));
        System.out.println(s.isValid("()"));
        System.out.println(s.isValid("()[]{}") + "\t()[]{}");
        System.out.println(s.isValid("(]"));
        System.out.println(s.isValid("([)]"));
    }
    
    
private static class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray() ) {
            if (c == '(' || c == '{' || c == '[')
                stack.add(0, c);
            if (c == ')') {
                if (stack.isEmpty()) return false;
                if (stack.remove(0) != '(') return false;
            }
            if (c == ']') {
                if (stack.isEmpty()) return false;
                if (stack.remove(0) != '[') return false;
            }
            if (c == '}') {
                if (stack.isEmpty()) return false;
                if (stack.remove(0) != '{') return false;
            }
        }
        
        return stack.isEmpty();
    }
}

}
